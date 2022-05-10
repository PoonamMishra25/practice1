package com.example.firebasedemo1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText edEmail, edPass;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edEmail = findViewById(R.id.editTextTextEmailAddress);
        edPass = findViewById(R.id.editTextNumberPassword);
        progressBar = findViewById(R.id.progressBar);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(edEmail.getText().toString(), edPass.getText().toString())
                    .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressBar.setVisibility(View.INVISIBLE);
                                edEmail.getText().clear();
                                edPass.getText().clear();
                                Toast.makeText(MainActivity.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                            } else {
                                progressBar.setVisibility(View.INVISIBLE);
                                edEmail.getText().clear();
                                edPass.getText().clear();
                                Toast.makeText(MainActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });

    }
}