package com.example.daysevenexfive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Admin Console
public class MainActivity7 extends AppCompatActivity {

    Spinner spinner;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;
    EditText ed1,ed2,ed3;
    String s1,s2,s3,s4;
    FirebaseFirestore db;
    private static final String TAG="abcd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        spinner=findViewById(R.id.spinner);
        arrayList=new ArrayList();
        arrayList.add("A+");
        arrayList.add("A-");
        arrayList.add("B+");
        arrayList.add("B-");
        arrayList.add("0+");
        arrayList.add("0-");
        arrayList.add("AB+");
        arrayList.add("AB-");
        arrayAdapter=new ArrayAdapter(MainActivity7.this, android.R.layout.simple_list_item_1,arrayList);
        spinner.setAdapter(arrayAdapter);
        ed1=findViewById(R.id.editTextTextPersonName1);
        ed2=findViewById(R.id.editTextTextPersonName2);
        ed3=findViewById(R.id.editTextTextPersonName3);
        db=FirebaseFirestore.getInstance();
    }

    public void save(View view) {
        s1=ed1.getText().toString();
        s2=ed2.getText().toString();
        s3=ed3.getText().toString();
        s4=spinner.getSelectedItem().toString();
        UserDetails userDetails = new UserDetails();
        userDetails.setName(s1);
        userDetails.setMobile(s2);
        userDetails.setLocation(s3);
        userDetails.setBgroup(s4);
        userDetails.setDob(null);
        userDetails.setLast_donated(null);

        try {
            Map<String, UserDetails> user = new HashMap<>();
            user.put(s2, userDetails);

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
        Intent intent = new Intent(MainActivity7.this, MainActivity5.class);
        intent.putExtra("mobile", s2);
        intent.putExtra("b_group", s4);
        startActivity(intent);
        Toast.makeText(MainActivity7.this, "Data Inserted", Toast.LENGTH_LONG).show();
    }

    public void logout(View view) {
        Intent intent = new Intent(MainActivity7.this, MainActivity.class);
        startActivity(intent);
    }
}