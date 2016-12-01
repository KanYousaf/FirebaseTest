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

public class MainActivity extends AppCompatActivity {
    private EditText userName, password;
    private Button sign_up_button;
    private TextView sign_in_tv;
    private ProgressBar progressBar;
    private int progress_bar_status=0;
    private Handler handler=new Handler();
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) this.findViewById(R.id.userNameEt);
        password = (EditText) this.findViewById(R.id.passwordEt);
        sign_in_tv = (TextView) this.findViewById(R.id.sign_in_tv);
        progressBar = (ProgressBar) this.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.INVISIBLE);

        firebaseAuth = FirebaseAuth.getInstance();
        sign_in_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), Home.class));
            }
        });
    }

    public void sign_up_clicked(View view) {
        String userName_String=userName.getText().toString().trim();
        String password_string=password.getText().toString().trim();

        if(userName_String.isEmpty() || password_string.isEmpty()){
            Toast.makeText(MainActivity.this,"Enter UserName and Password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        progress_bar_status=0;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progress_bar_status < 100) {
                    progress_bar_status += 1;
                    // Try to sleep the thread for 20 milliseconds
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Update the progress bar
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progress_bar_status);
                        }
                    });
                }
            }
        }).start();
        firebaseAuth.createUserWithEmailAndPassword(userName_String, password_string)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }else{
                            Toast.makeText(MainActivity.this, "User Not Registered", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
