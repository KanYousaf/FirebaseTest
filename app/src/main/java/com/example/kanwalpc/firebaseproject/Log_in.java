package com.example.kanwalpc.firebaseproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Log_in extends AppCompatActivity {
    private EditText userName, password;
    private Button sign_in_button;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        firebaseAuth=FirebaseAuth.getInstance();
//        if(firebaseAuth.getCurrentUser()!=null){
//            finish();
//            startActivity(new Intent(getApplicationContext(), Home.class));
//        }
        userName = (EditText) this.findViewById(R.id.userNameEt);
        password = (EditText) this.findViewById(R.id.passwordEt);
        progressDialog = new ProgressDialog(Log_in.this);
    }

    public void sign_In_clicked(View view) {
        String userName_String=userName.getText().toString().trim();
        String password_string=password.getText().toString().trim();

        if(userName_String.isEmpty() || password_string.isEmpty()){
            Toast.makeText(Log_in.this,"Enter UserName and Password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Signing-in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName_String, password_string)
                .addOnCompleteListener(Log_in.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), Home.class));
                        }else{
                            Toast.makeText(Log_in.this, "Login Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
