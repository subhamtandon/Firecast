package com.example.subhamtandon.firecastapp;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class loginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewSignup;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(), profileActivity.class));
        }

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);

        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        textViewSignup = (TextView) findViewById(R.id.textViewSignup);

        progressDialog = new ProgressDialog(this);

        buttonLogin.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);

    }

    private void userLogin(){

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

        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressDialog.dismiss();
                        if (task.isSuccessful()){

                            Log.d("pass", "signInWithEmail:success");
                            finish();
                            startActivity(new Intent(getApplicationContext(), profileActivity.class));

                        } else {

                            Log.w("fail", "signInWithEmail:failure", task.getException());

                        }

                    }
                });

    }

    @Override
    public void onClick(View v) {

        if (v == buttonLogin){

            userLogin();

        }

        if (v == textViewSignup){

            finish();
            startActivity(new Intent(this, MainActivity.class));

        }

    }
}
