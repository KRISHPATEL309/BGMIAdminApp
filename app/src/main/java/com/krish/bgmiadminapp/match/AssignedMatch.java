package com.krish.bgmiadminapp.match;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.krish.bgmiadminapp.R;

import java.util.ArrayList;

public class AssignedMatch extends AppCompatActivity {
    private RecyclerView assignRecycler;
    private ProgressBar progressBar;
    private ArrayList<ReferenceData>list;
    private ReferenceAdpter adapter;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assigned_match);

        assignRecycler = findViewById(R.id.assignRecycler);
        progressBar = findViewById(R.id.progressBar);
        reference = FirebaseDatabase.getInstance().getReference().child("Reference Numbers");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);

        assignRecycler.setLayoutManager(gridLayoutManager);
        assignRecycler.setHasFixedSize(true);

        getData();
    }
    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    ReferenceData data = snapshot1.getValue(ReferenceData.class);
                    list.add(0,data);
                }
                adapter = new ReferenceAdpter(AssignedMatch.this,list);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                assignRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AssignedMatch.this,"No data found"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}