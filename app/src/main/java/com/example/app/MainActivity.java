package com.example.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText Firstname;
    private EditText LastName;
    private EditText emailAddress;
    private EditText phoneNumber;
    private Button NextButton;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();



    private CollectionReference collectionReference =  db.collection("Students");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firstname = findViewById(R.id.fnameEditText);
        LastName = findViewById(R.id.lNameEditText);
        emailAddress = findViewById(R.id.editTextTextEmailAddress);
        phoneNumber =findViewById(R.id.phone);
        NextButton = findViewById(R.id.button);
        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = Firstname.getText().toString().trim();
                String lastname = LastName.getText().toString().trim();
                String Email = emailAddress.getText().toString().trim();
                String phoneN = phoneNumber.getText().toString();
                Students students = new Students(firstname,lastname,Email,phoneN);
                collectionReference.add(students).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(MainActivity.this,"Successful !",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}