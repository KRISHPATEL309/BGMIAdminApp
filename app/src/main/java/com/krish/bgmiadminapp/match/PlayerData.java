
package com.krish.bgmiadminapp.match;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.krish.bgmiadminapp.R;

import java.util.ArrayList;

public class PlayerData extends AppCompatActivity {

    private ProgressBar progressBarPlayer;
    private RecyclerView playerRecycler; 

    private DatabaseReference reference;
    private ArrayList<Choose_Squad> list;
    private  PlayerAdapter adapter;

    private String referId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_data);

        referId = getIntent().getStringExtra("ref_no");

        playerRecycler = findViewById(R.id.playerRecycler);
        progressBarPlayer = findViewById(R.id.progressBarPlayer);

        reference= FirebaseDatabase.getInstance().getReference().child("Total Orders").child(referId);

        playerRecycler.setLayoutManager(new LinearLayoutManager(PlayerData.this));
        playerRecycler.setHasFixedSize(true);

        getData();

    }

    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list=new ArrayList<>();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    Choose_Squad data = snapshot1.getValue(Choose_Squad.class);
                    list.add(0,data);
                }
                adapter = new PlayerAdapter(PlayerData.this,list);
                adapter.notifyDataSetChanged();
                playerRecycler.setAdapter(adapter);
                progressBarPlayer.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBarPlayer.setVisibility(View.GONE);
            }
        });
    }
}