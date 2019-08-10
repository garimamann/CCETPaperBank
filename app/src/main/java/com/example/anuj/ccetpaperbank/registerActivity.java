package com.example.anuj.ccetpaperbank;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class registerActivity extends AppCompatActivity {

    private EditText nameReg;
    private EditText rollReg;
    private EditText email;
    private EditText passw;
    private Button regbutton;
    private ProgressBar registerProgress;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        rollReg = findViewById(R.id.rollRegID);
        nameReg = findViewById(R.id.nameRegID);
        email = findViewById(R.id.emailRegID);
        passw = findViewById(R.id.passwordRegID);
        regbutton = findViewById(R.id.buttonRegID);
        registerProgress = findViewById(R.id.registerProgressID);
        registerProgress.setVisibility(View.INVISIBLE);

        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userRegister();

            }
        });
    }


    private void userRegister() {

        final String emailid,pass,name,roll;
        emailid = email.getText().toString();
        pass = passw.getText().toString();
        name = nameReg.getText().toString();
        roll = rollReg.getText().toString();

        if (name.isEmpty()) {
            nameReg.setError("Name is Required");
            nameReg.requestFocus();
            return;
        }

        if (roll.isEmpty()) {
            rollReg.setError("Roll No is Required");
            rollReg.requestFocus();
            return;
        }

        if (emailid.isEmpty()) {
            email.setError("Email is Required");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailid).matches()) {
            email.setError("Enter a valid Email");
            email.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            passw.setError("Password is Required");
            passw.requestFocus();
            return;
        }

        if (pass.length()<6) {
            passw.setError("Password should be atleast 6 characters long");
            passw.requestFocus();
            return;
        }


        registerProgress.setVisibility(View.VISIBLE);
        regbutton.setVisibility(View.INVISIBLE);


        mAuth.createUserWithEmailAndPassword(emailid,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                registerProgress.setVisibility(View.GONE);
                regbutton.setVisibility(View.VISIBLE);


             if(task.isSuccessful()) {

                 User user = new User(name,roll,emailid);

                 FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {

                         if (task.isSuccessful()) {
                             Toast.makeText(registerActivity.this, "Registeration Successful", Toast.LENGTH_SHORT).show();
                             finish();

                         }
                         else {
                             Toast.makeText(registerActivity.this, "Registeration Error", Toast.LENGTH_SHORT).show();

                         }
                     }
                 });
             }

                else {

                 if(task.getException() instanceof FirebaseAuthUserCollisionException) {

                     Toast.makeText(registerActivity.this, "Email already Registered", Toast.LENGTH_SHORT).show();
                 }
                 else
                     Toast.makeText(registerActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

             }
            }
        });
    }
}
