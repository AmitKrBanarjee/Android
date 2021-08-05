package com.example.daysevenexfive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

//This is Sign-In Page
public class MainActivity3 extends AppCompatActivity {
    EditText editText;
    String mobile,bgroup=null;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        editText=findViewById(R.id.editTextTextPersonName1);
    }

    public void getotp(View view) {
        db = FirebaseFirestore.getInstance();
        mobile=editText.getText().toString();
        try {
            db.collection("user")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    try {
                                        Map<String, Object> user = null;
                                        user = (Map<String, Object>) document.getData();
                                        if (user.containsKey(mobile)) {
                                            user = (Map<String, Object>) document.getData().get(mobile);
                                            bgroup = user.get("bgroup").toString();
                                        }
                                    } catch (Exception e) {
                                        Toast.makeText(MainActivity3.this, "11" + e, Toast.LENGTH_LONG).show();
                                    }
                                }

                                if(bgroup!=null) {
                                    Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                                    intent.putExtra("mob", mobile);
                                    intent.putExtra("bgroup", bgroup);
                                    startActivity(intent);
                                }
                                else{
                                    AlertDialog.Builder n=new AlertDialog.Builder(MainActivity3.this);
                                    n.setTitle("Note !");
                                    n.setMessage("This Mobile is not registered, Please Sign-Up");
                                    n.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent=new Intent(MainActivity3.this, MainActivity2.class);
                                            startActivity(intent);
                                        }
                                    });
                                    n.setNegativeButton("Cancel",null);
                                    n.show();
                                }
                            } else {
                                Log.w("abc", "Error getting documents.", task.getException());
                                Toast.makeText(MainActivity3.this, "22222222222222", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

        } catch (Exception e) {
            Toast.makeText(MainActivity3.this, "ee:" + e, Toast.LENGTH_SHORT).show();
        }
    }

}