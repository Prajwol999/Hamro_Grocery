package com.example.hamrogrocery.vendorActivity

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hamrogrocery.R
import com.example.hamrogrocery.databinding.ActivityVendorSignUpBinding
import com.example.hamrogrocery.model.VendorModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.util.UUID

class VendorSignUp : AppCompatActivity() {

    private lateinit var vendorSignUpBinding: ActivityVendorSignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private var imageUri: Uri? = null

    private val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val ref: DatabaseReference = firebaseDatabase.reference.child("vendors")
    private val firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
    private val storageReference = firebaseStorage.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vendorSignUpBinding = ActivityVendorSignUpBinding.inflate(layoutInflater)
        setContentView(vendorSignUpBinding.root)

        auth = FirebaseAuth.getInstance()

        registerActivityForResult()

        vendorSignUpBinding.uploadLogo.setOnClickListener {
            val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                android.Manifest.permission.READ_MEDIA_IMAGES
            } else {
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            }
            if (ContextCompat.checkSelfPermission(this, permissions) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(permissions), 1)
            } else {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                activityResultLauncher.launch(intent)
            }
        }

        vendorSignUpBinding.signUpBtn.setOnClickListener {
            if (!vendorSignUpBinding.checkBox.isChecked) {
                Toast.makeText(this, "You must agree to the terms and conditions", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val email = vendorSignUpBinding.vendoremail.text.toString().trim()
            val password = vendorSignUpBinding.vendorpassword.text.toString().trim()
            val panno = vendorSignUpBinding.vendorPan.text.toString().trim()
            val shopName = vendorSignUpBinding.vendorShop.text.toString().trim()

            if (validateInput(email, password, panno, shopName)) {
                if (imageUri != null) {
                    uploadPhotoAndCreateVendor(email, password, panno, shopName)
                } else {
                    Toast.makeText(this, "Please upload your shop documentation", Toast.LENGTH_SHORT).show()
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun validateInput(email: String, password: String, panno: String, shopName: String): Boolean {
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.isEmpty() || password.length < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
            return false
        }

        if (panno.isEmpty() || !panno.all { it.isDigit() }) {
            Toast.makeText(this, "Please enter a valid PAN number", Toast.LENGTH_SHORT).show()
            return false
        }

        if (shopName.isEmpty()) {
            Toast.makeText(this, "Please enter your shop name", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun registerActivityForResult() {
        activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback { result ->
                val resultCode = result.resultCode
                val imageData = result.data
                if (resultCode == RESULT_OK && imageData != null) {
                    imageUri = imageData.data
                    imageUri?.let {
                        Picasso.get().load(it).into(vendorSignUpBinding.uploadLogo)
                        vendorSignUpBinding.textView13.setTextColor(Color.GREEN)
                        vendorSignUpBinding.textView13.text = "Shop document uploaded successfully"
                    }
                } else {
                    Toast.makeText(this, "Image selection failed", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun uploadPhotoAndCreateVendor(email: String, password: String, panno: String, shopName: String) {
        vendorSignUpBinding.signUpBtn.isEnabled = false
        val imageName = UUID.randomUUID().toString()
        val imageReference = storageReference.child("shop_documents").child(imageName)

        imageUri?.let { url ->
            imageReference.putFile(url).addOnSuccessListener {
                imageReference.downloadUrl.addOnSuccessListener { url ->
                    val imageUrl = url.toString()
                    createVendor(email, password, panno.toInt(), shopName, imageUrl)
                }
            }.addOnFailureListener {
                vendorSignUpBinding.signUpBtn.isEnabled = true
                Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun createVendor(email: String, password: String, panno: Int, shopName: String, imageUrl: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            vendorSignUpBinding.signUpBtn.isEnabled = true
            if (task.isSuccessful) {
                val vendorId = auth.currentUser?.uid
                val vendorRef = ref.child(vendorId!!)
                val vendorData = VendorModel(vendorId, email, panno, shopName, imageUrl)
                vendorRef.setValue(vendorData).addOnCompleteListener { dbTask ->
                    if (dbTask.isSuccessful) {
                        Toast.makeText(this, "Vendor Sign-Up Successful", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, dbTask.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
