package com.example.anuj.ccetpaperbank;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {

    private Button loginButton;
    private ProgressBar loginProgress;
    private Button registerButton;
    private EditText emailText;
    private EditText passwordText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);
        emailText = findViewById(R.id.login_mail);
        passwordText = findViewById(R.id.login_password);
        loginProgress = findViewById(R.id.login_progress);
        loginProgress.setVisibility(View.INVISIBLE);
        mAuth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginActivity.this,registerActivity.class));
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

    }

        private void userLogin(){

            String emailid,pass;
            emailid = emailText.getText().toString();
            pass = passwordText.getText().toString();


            if (emailid.isEmpty()) {
                emailText.setError("Email is Required");
                emailText.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(emailid).matches()) {
                emailText.setError("Enter a valid Email");
                emailText.requestFocus();
                return;
            }

            if (pass.isEmpty()) {
                passwordText.setError("Password is Required");
                passwordText.requestFocus();
                return;
            }

            loginProgress.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.INVISIBLE);

            mAuth.signInWithEmailAndPassword(emailid,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    loginProgress.setVisibility(View.GONE);
                    loginButton.setVisibility(View.VISIBLE);

                    if(task.isSuccessful()){
                        finish();
                        Intent intent = new Intent(loginActivity.this,mainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                }

                    else{
                        Toast.makeText(loginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }

   // @Override
    //protected void onStart() {
      //  super.onStart();
        //if(mAuth.getCurrentUser() != null){
          //  finish();
        //    startActivity(new Intent(this,mainActivity.class));
      //  }
    //}
}
