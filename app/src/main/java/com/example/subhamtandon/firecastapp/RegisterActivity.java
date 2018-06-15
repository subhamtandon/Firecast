package com.example.subhamtandon.firecastapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonRegister;
    private TextView textViewSignin;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(), profileActivity.class));
        }

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

        progressDialog = new ProgressDialog(this);

        buttonRegister.setOnClickListener(this);

        textViewSignin.setOnClickListener(this);

    }

    private void registerUser(){

        String email = editTextEmail .getText().toString().trim();

        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){

            //email is empty
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();

            //stopping further execution
            return;
        }

        if (TextUtils.isEmpty(password)){

            //password is empty
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();

            //stopping further execution
            return;
        }
        //if validations are ok

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressDialog.dismiss();
                        if (task.isSuccessful()){

                            Log.d("pass", "createUserWithEmail:success");
                            Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), profileActivity.class));

                        }

                        else {

                            Log.w("fail", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Could not register. Please try again", Toast.LENGTH_SHORT).show();

                        }
                        progressDialog.dismiss();

                    }
                });

    }

    @Override
    public void onClick(View v) {

        if (v == buttonRegister){

            registerUser();

        }

        if (v == textViewSignin){

            //will open login activity
            finish();
            startActivity(new Intent(this, MainActivity.class));

        }


    }
}

