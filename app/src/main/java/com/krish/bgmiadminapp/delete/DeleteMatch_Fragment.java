package com.krish.bgmiadminapp.delete;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.krish.bgmiadminapp.R;
import com.krish.bgmiadminapp.match.MatchAdapter;
import com.krish.bgmiadminapp.match.MatchData;

import java.util.ArrayList;
import java.util.List;

public class DeleteMatch_Fragment extends Fragment {

    private RecyclerView morningMatch,afternoonMatch,eveningMatch;
    private LinearLayout morningNoData,afternoonNoData,eveningNoData;
    private List<MatchData> list1,list2,list3;
    private DatabaseReference reference,dbRef;
    private MatchAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete_match_, container, false);
        morningMatch = view.findViewById(R.id.morningMatch);
        afternoonMatch = view.findViewById(R.id.afternoonMatch);
        eveningMatch = view.findViewById(R.id.eveningMatch);
        afternoonNoData = view.findViewById(R.id.afternoonNoData);
        eveningNoData = view.findViewById(R.id.eveningNoData);
        morningNoData = view.findViewById(R.id.morningNoData);

        reference = FirebaseDatabase.getInstance().getReference().child("Matches");

        morningMatch();
        afternoonMatch();
        eveningMatch();

        return view;
    }

    private void morningMatch() {
        dbRef = reference.child("Morning");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1 = new ArrayList<MatchData>();
                if (!snapshot.exists()){
                    morningNoData.setVisibility(View.VISIBLE);
                    morningMatch.setVisibility(View.GONE);
                }else {
                    morningNoData.setVisibility(View.GONE);
                    morningMatch.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot1: snapshot.getChildren()){
                        MatchData data = snapshot1.getValue(MatchData.class);
                        list1.add(0,data);
                    }
                    morningMatch.setHasFixedSize(true);
                    morningMatch.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new MatchAdapter(getContext(),list1,"Morning");
                    morningMatch.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void afternoonMatch() {
        dbRef = reference.child("Afternoon");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list2 = new ArrayList<MatchData>();
                if (!snapshot.exists()){
                    afternoonNoData.setVisibility(View.VISIBLE);
                    afternoonMatch.setVisibility(View.GONE);
                }else {
                    afternoonNoData.setVisibility(View.GONE);
                    afternoonMatch.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot1: snapshot.getChildren()){
                        MatchData data = snapshot1.getValue(MatchData.class);
                        list2.add(0,data);
                    }
                    afternoonMatch.setHasFixedSize(true);
                    afternoonMatch.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new MatchAdapter(getContext(),list2,"Afternoon");
                    afternoonMatch.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void eveningMatch() {
        dbRef = reference.child("Evening");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list3 = new ArrayList<MatchData>();
                if (!snapshot.exists()){
                    eveningNoData.setVisibility(View.VISIBLE);
                    eveningMatch.setVisibility(View.GONE);
                }else {
                    eveningNoData.setVisibility(View.GONE);
                    eveningMatch.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot1: snapshot.getChildren()){
                        MatchData data = snapshot1.getValue(MatchData.class);
                        list3.add(0,data);
                    }
                    eveningMatch.setHasFixedSize(true);
                    eveningMatch.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new MatchAdapter(getContext(),list3,"Evening");
                    eveningMatch.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}