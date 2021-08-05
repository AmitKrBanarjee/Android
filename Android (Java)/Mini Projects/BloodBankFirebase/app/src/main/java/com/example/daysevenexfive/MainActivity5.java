package com.example.daysevenexfive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//Details Display Screen
public class MainActivity5 extends AppCompatActivity {
    String b_group, mobile, s2;
    ArrayList<String> mylist = new ArrayList<String>();
    TextView textView;
    RecyclerView recyclerView;
    FirebaseFirestore db;
    String s11 = "a";
    int c = 1;

    private static final String TAG = "abcd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        textView = findViewById(R.id.textView);
        db = FirebaseFirestore.getInstance();
        b_group = getIntent().getStringExtra("b_group");
        mobile = getIntent().getStringExtra("mobile");
        ArrayList<ArrayList<String>> arrayList = new ArrayList<ArrayList<String>>(100);
        Toast.makeText(MainActivity5.this, "bgroup:" + b_group + "mob:" + mobile, Toast.LENGTH_SHORT).show();

        try {
            db.collection("user")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            s11 += "c";
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ArrayList<String> arrayList2 = new ArrayList<String>(3);
                                    try {
                                        Map<String, Object> user = null;
                                        user = (Map<String, Object>) document.getData();
                                        Collection<Object> collectionValues = user.values();
                                        for (Object o : collectionValues) {
                                            Map<String, String> m = (Map<String, String>) o;
                                            if (m.get("bgroup").equals(b_group)) {
                                                arrayList2.add(m.get("name"));
                                                arrayList2.add(m.get("location"));
                                                arrayList2.add(m.get("mobile"));
                                                arrayList.add(arrayList2);
                                                s2 = "People needing " + b_group + " Blood Group :-";
                                                textView.setText(s2);
                                                mylist.add( "\n\t Name: " + m.get("name") + "\n\t Address: " + m.get("location") + "\n\t Mobile: " + m.get("mobile")+"\n\t\t\t\t\t("+c+")\n");
                                                c += 1;
                                            }
                                        }

                                    } catch (Exception e) {
                                        Toast.makeText(MainActivity5.this, "11" + e, Toast.LENGTH_LONG).show();
                                    }
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                }
                                recyclerView.setAdapter(new RecyclerViewAdapter(mylist, MainActivity5.this, arrayList));
                            } else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                                Toast.makeText(MainActivity5.this, "22222222222222", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

        } catch (Exception e) {
            Toast.makeText(MainActivity5.this, "ee:" + e, Toast.LENGTH_SHORT).show();
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void loggingoff(MenuItem item) {
        Intent intent = new Intent(MainActivity5.this, MainActivity.class);
        startActivity(intent);
    }
}