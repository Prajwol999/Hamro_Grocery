package com.example.hello_k_xa

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}



//<?xml version="1.0" encoding="utf-8"?>
//<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
//xmlns:app="http://schemas.android.com/apk/res-auto"
//xmlns:tools="http://schemas.android.com/tools"
//android:id="@+id/main"
//android:layout_width="match_parent"
//android:layout_height="match_parent"
//tools:context=".MainActivity">
//
//<ImageView
//android:id="@+id/imageView"
//android:layout_width="119dp"
//android:layout_height="100dp"
//android:layout_marginTop="80dp"
//app:layout_constraintEnd_toEndOf="parent"
//app:layout_constraintHorizontal_bias="0.441"
//app:layout_constraintStart_toStartOf="parent"
//app:layout_constraintTop_toTopOf="parent"
//app:srcCompat="@drawable/baseline_person_24" />
//
//<com.google.android.material.textfield.TextInputLayout
//android:id="@+id/textInputLayout"
//android:layout_width="0dp"
//android:layout_height="wrap_content"
//android:layout_marginStart="1dp"
//android:layout_marginTop="25dp"
//android:layout_marginEnd="1dp"
//app:layout_constraintEnd_toEndOf="parent"
//app:layout_constraintStart_toStartOf="parent"
//app:layout_constraintTop_toTopOf="@+id/textInputLayout2">
//
//</com.google.android.material.textfield.TextInputLayout>
//
//<com.google.android.material.textfield.TextInputLayout
//android:id="@+id/textInputLayout2"
//android:layout_width="0dp"
//android:layout_height="wrap_content"
//android:layout_marginStart="10dp"
//android:layout_marginTop="30dp"
//android:layout_marginEnd="10dp"
//app:counterEnabled="true"
//app:counterMaxLength="20"
//app:layout_constraintEnd_toEndOf="parent"
//app:layout_constraintStart_toStartOf="parent"
//app:layout_constraintTop_toBottomOf="@+id/imageView">
//
//<com.google.android.material.textfield.TextInputEditText
//android:id="@+id/userName_text"
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//android:hint="Username" />
//</com.google.android.material.textfield.TextInputLayout>
//
//<com.google.android.material.textfield.TextInputLayout
//android:id="@+id/textInputLayout3"
//android:layout_width="0dp"
//android:layout_height="wrap_content"
//android:layout_marginStart="10dp"
//android:layout_marginEnd="10dp"
//app:counterEnabled="true"
//app:counterMaxLength="10"
//app:layout_constraintEnd_toEndOf="parent"
//app:layout_constraintStart_toStartOf="parent"
//app:layout_constraintTop_toBottomOf="@+id/textInputLayout2">
//
//<com.google.android.material.textfield.TextInputEditText
//android:id="@+id/password_text"
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//android:hint="Password" />
//</com.google.android.material.textfield.TextInputLayout>
//
//<Button
//android:id="@+id/button"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_marginStart="296dp"
//android:layout_marginTop="48dp"
//android:backgroundTint="#306844"
//android:text="Sign In"
//app:layout_constraintStart_toStartOf="parent"
//app:layout_constraintTop_toBottomOf="@+id/textInputLayout3" />
//
//<CheckBox
//android:id="@+id/checkBox"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_marginStart="12dp"
//android:layout_marginTop="14dp"
//android:text="Remember me"
//app:layout_constraintStart_toStartOf="parent"
//app:layout_constraintTop_toBottomOf="@+id/textInputLayout3" />
//
//<TextView
//android:id="@+id/textView"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_marginStart="16dp"
//android:layout_marginTop="15dp"
//android:text="Forget password?"
//android:textSize="15dp"
//app:layout_constraintStart_toStartOf="parent"
//app:layout_constraintTop_toBottomOf="@+id/checkBox" />
//
//<TextView
//android:id="@+id/textView2"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_marginStart="92dp"
//android:layout_marginTop="130dp"
//android:layout_marginEnd="92dp"
//android:layout_marginBottom="130dp"
//android:text="Don't have an acoount? Sign Up"
//android:textSize="15dp"
//app:layout_constraintBottom_toBottomOf="parent"
//app:layout_constraintEnd_toEndOf="@+id/button"
//app:layout_constraintHorizontal_bias="0.35"
//app:layout_constraintStart_toStartOf="@+id/textView"
//app:layout_constraintTop_toTopOf="@+id/textView"
//app:layout_constraintVertical_bias="0.0" />
//
//<TextView
//android:id="@+id/textView6"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_marginStart="175dp"
//android:layout_marginTop="20dp"
//android:layout_marginEnd="10dp"
//android:text="Contact Us"
//android:textSize="15dp"
//app:layout_constraintEnd_toStartOf="@+id/imageView2"
//app:layout_constraintStart_toStartOf="parent"
//app:layout_constraintTop_toBottomOf="@+id/textView2" />
//
//<ImageView
//android:id="@+id/imageView2"
//android:layout_width="42dp"
//android:layout_height="26dp"
//android:layout_marginStart="17dp"
//android:layout_marginTop="16dp"
//android:layout_marginEnd="1dp"
//app:layout_constraintEnd_toStartOf="@+id/imageView3"
//app:layout_constraintStart_toEndOf="@+id/textView6"
//app:layout_constraintTop_toBottomOf="@+id/textView2"
//app:srcCompat="@drawable/fb" />
//
//<ImageView
//android:id="@+id/imageView3"
//android:layout_width="42dp"
//android:layout_height="27dp"
//android:layout_marginTop="15dp"
//android:layout_marginEnd="102dp"
//app:layout_constraintEnd_toEndOf="parent"
//app:layout_constraintStart_toEndOf="@+id/imageView2"
//app:layout_constraintTop_toBottomOf="@+id/textView2"
//app:srcCompat="@drawable/instagram" />
//
//<LinearLayout
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//android:layout_marginTop="42dp"
//android:layout_marginBottom="49dp"
//android:gravity="center_horizontal"
//app:layout_constraintBottom_toTopOf="@+id/textView2"
//app:layout_constraintEnd_toEndOf="parent"
//app:layout_constraintStart_toStartOf="parent"
//app:layout_constraintTop_toBottomOf="@+id/textView">
//
//<View
//android:layout_width="90dp"
//android:layout_height="2dp"
//android:layout_gravity="center"
//android:background="#000" />
//
//<TextView
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_gravity="center"
//android:layout_marginStart="10dp"
//android:layout_marginEnd="10dp"
//android:text="OR" />
//
//<View
//android:layout_width="90dp"
//android:layout_height="2dp"
//android:layout_gravity="center"
//android:background="#000" />
//</LinearLayout>
//
//
//</androidx.constraintlayout.widget.ConstraintLayout>