package com.example.daysevenexfive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//Admin Login
public class MainActivity6 extends AppCompatActivity {
    String uname="54321";
    String pass="12345";
    EditText ed1,ed2;
    String s1,s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        ed1=findViewById(R.id.editTextTextPersonName1);
        ed2=findViewById(R.id.editTextTextPersonName2);
    }

    public void login(View view) {
        s1=ed1.getText().toString();
        s2=ed2.getText().toString();
        if(s1.equals(uname) && s2.equals(pass)) {
            Intent intent = new Intent(MainActivity6.this, MainActivity7.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(MainActivity6.this,"Invalid Credentials",Toast.LENGTH_LONG).show();
        }
    }
}