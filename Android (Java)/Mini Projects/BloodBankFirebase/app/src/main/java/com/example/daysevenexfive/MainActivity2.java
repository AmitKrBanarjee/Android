package com.example.daysevenexfive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//This is Sign-up page
public class MainActivity2 extends AppCompatActivity {
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;
    Spinner spinner;
    EditText ed1, ed2, ed3, ed4, ed5;
    String s1;
    String s2;
    String s3;
    String s4;
    String s5;
    String s6;
    EditText ed;
    String s;
    CheckBox checkBox;
    FirebaseFirestore db;
    private static final String TAG="abcd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ed1 = findViewById(R.id.editTextTextPersonName1);
        ed2 = findViewById(R.id.editTextTextPersonName2);
        ed3 = findViewById(R.id.editTextTextPersonName3);
        ed4 = findViewById(R.id.editTextTextPersonName4);
        ed5 = findViewById(R.id.editTextTextPersonName5);
        checkBox = findViewById(R.id.checkBox);
        spinner = findViewById(R.id.spinner);
        db=FirebaseFirestore.getInstance();
        arrayList = new ArrayList();
        arrayList.add("A+");
        arrayList.add("A-");
        arrayList.add("B+");
        arrayList.add("B-");
        arrayList.add("0+");
        arrayList.add("0-");
        arrayList.add("AB+");
        arrayList.add("AB-");
        arrayAdapter = new ArrayAdapter(MainActivity2.this, android.R.layout.simple_list_item_1, arrayList);
        spinner.setAdapter(arrayAdapter);
    }

    public void signup(View view) {
        s1 = ed1.getText().toString();
        s2 = ed2.getText().toString();
        s3 = ed3.getText().toString();
        s4 = spinner.getSelectedItem().toString();
        s5 = ed4.getText().toString();
        s6 = ed5.getText().toString();
        if (checkBox.isChecked()) {
            UserDetails userDetails = new UserDetails();
            userDetails.setName(s1);
            userDetails.setLocation(s2);
            userDetails.setDob(s3);
            userDetails.setBgroup(s4);
            userDetails.setLast_donated(s5);
            userDetails.setMobile(s6);

            try {
                Map<String, UserDetails> user = new HashMap<>();
                user.put(s6, userDetails);

                // Add a new document with a generated ID
                db.collection("user")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });
            }
            catch (Exception e){
                Toast.makeText(this,".."+e,Toast.LENGTH_LONG).show();
            }
            Intent intent = new Intent(MainActivity2.this, MainActivity5.class);
            intent.putExtra("mobile", s6);
            intent.putExtra("b_group", s4);
            startActivity(intent);
        } else {
            AlertDialog.Builder n = new AlertDialog.Builder(MainActivity2.this);
            n.setTitle("Note !");
            n.setMessage("Please accept Terms & Conditons");
            n.setPositiveButton("Ok", null);
            n.setNegativeButton("Cancel", null);
            n.show();
        }
    }

    public void pickDob(View view) {
        ed = findViewById(R.id.editTextTextPersonName3);
        DatePickerDialog dp = new DatePickerDialog(MainActivity2.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        s = Integer.toString(dayOfMonth) + "/" + Integer.toString(month) + "/" + Integer.toString(year);
                        ed.setText(s);
                    }
                }
                , 2021, 7, 2);
        dp.show();
    }

    public void pickldd(View view) {
        ed = findViewById(R.id.editTextTextPersonName4);
        DatePickerDialog dp = new DatePickerDialog(MainActivity2.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        s = Integer.toString(dayOfMonth) + "/" + Integer.toString(month) + "/" + Integer.toString(year);
                        ed.setText(s);
                    }
                }
                , 2021, 7, 2);
        dp.show();
    }
}