package com.example.daysevenexfive;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> data;
    Context context;
    ArrayList<ArrayList<String>> arrayList;

    public RecyclerViewAdapter(ArrayList<String> data,Context context, ArrayList<ArrayList<String>> arrayList) {
        this.data = data;
        this.context=context;
        this.arrayList=arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.recycler_view_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String txt=data.get(position);
        holder.txtView.setText(txt);
        holder.txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "+91"+arrayList.get(position).get(2);
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + number));
                if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.CALL_PHONE},
                            1);
                } else {
                    //You already have permission
                    try {
                        Toast.makeText(context, "Calling: " + number, Toast.LENGTH_LONG).show();
                        context.startActivity(callIntent);
                    } catch (SecurityException e) {
                        Toast.makeText(context, ":" + e, Toast.LENGTH_LONG).show();
                    }
                }
        }
    });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtView;

        public ViewHolder( View itemView) {
            super(itemView);
            txtView= itemView.findViewById(R.id.textView);
        }

    }
}

