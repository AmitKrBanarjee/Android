package com.example.daysevenexfive;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

//OTP Enter Page
public class MainActivity4 extends AppCompatActivity {
    String s,s1,b;
    String otp="123456";
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        editText=findViewById(R.id.editTextTextPersonName1);
        s=getIntent().getStringExtra("mob");
        b=getIntent().getStringExtra("bgroup");
    }

    public void enterotp(View view) {
        s1=editText.getText().toString();
        if(s1.equals(otp)) {
            Intent intent = new Intent(MainActivity4.this, MainActivity5.class);
            intent.putExtra("mobile",s);
            intent.putExtra("b_group",b);
            startActivity(intent);
        }
        else{
            AlertDialog.Builder n=new AlertDialog.Builder(MainActivity4.this);
            n.setTitle("Note !");
            n.setMessage("Invalid OTP");
            n.setPositiveButton("Ok",null);
            n.setNegativeButton("Cancel",null);
            n.show();
        }
    }
}