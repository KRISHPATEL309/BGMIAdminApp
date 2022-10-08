package com.krish.bgmiadminapp.upload;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.krish.bgmiadminapp.R;

import java.util.HashMap;

public class Upload_Fragment extends Fragment {

    TextInputLayout roomId,roomPassword,referencenum_u;
    Spinner matchTime_u;
    Button upload_u;
    private DatabaseReference reference;
    private String category;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upload_, container, false);

        roomId = view.findViewById(R.id.roomId);
        roomPassword = view.findViewById(R.id.roomPassword);
        referencenum_u = view.findViewById(R.id.referencenum_u);
        matchTime_u = view.findViewById(R.id.matchTime_u);
        upload_u = view.findViewById(R.id.upload_u);

        reference = FirebaseDatabase.getInstance().getReference().child("Matches");

        String[] item = new String[]{"Select Match Duration","Morning","Afternoon", "Evening"};
        matchTime_u.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,item));

        matchTime_u.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = matchTime_u.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        upload_u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidation();
            }
        });

        return view;
    }

    private void checkValidation() {
        if(roomId.getEditText().getText().toString().isEmpty()){
            roomId.setError("Empty");
            roomId.requestFocus();
        }else if (roomPassword.getEditText().getText().toString().isEmpty()) {
            roomPassword.setError("Empty");
            roomPassword.requestFocus();
        }else if (referencenum_u.getEditText().getText().toString().isEmpty()) {
            referencenum_u.setError("Empty");
            referencenum_u.requestFocus();
        }else if (category.equals("Select Match Duration")){
            Toast.makeText(getContext(),"please select Match Duration",Toast.LENGTH_SHORT).show();
        }else {
            uploadData();
        }
    }

    private void uploadData() {
        HashMap hp = new HashMap();
        hp.put("room_Id",roomId.getEditText().getText().toString());
        hp.put("room_Pass",roomPassword.getEditText().getText().toString());
        reference.child(category).child(referencenum_u.getEditText().getText().toString()).updateChildren(hp).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                Toast.makeText(getContext(),"Data Uploaded",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(),"Something Went Worng",Toast.LENGTH_SHORT).show();
            }
        });

    }
}