package com.example.kanwalpc.firebaseproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {
    private TextView display_name;
    private Button log_out;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        display_name=(TextView)this.findViewById(R.id.display_name);

        firebaseAuth=FirebaseAuth.getInstance();
//        if(firebaseAuth.getCurrentUser()==null){
//            finish();
//            startActivity(new Intent(getApplicationContext(), Log_in.class));
//        }

        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        display_name.setText("Hello "+firebaseUser.getEmail());
    }

    public void Log_out_clicked(View view) {
        finish();
        startActivity(new Intent(getApplicationContext(), Log_in.class));
    }
}
