package com.krish.bgmiadminapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.krish.bgmiadminapp.delete.DeleteMatch_Fragment;
import com.krish.bgmiadminapp.match.AssignedMatch;
import com.krish.bgmiadminapp.match.CreateMatch_Fragment;
import com.krish.bgmiadminapp.upload.Upload_Fragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView createMatch,uploadIdPass,deleteMatch,assignMatch;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createMatch = findViewById(R.id.createMatch);
        uploadIdPass = findViewById(R.id.uploadIdPass);
        deleteMatch = findViewById(R.id.deleteMatch);
        assignMatch = findViewById(R.id.assignMatch);

        createMatch.setOnClickListener(this);
        uploadIdPass.setOnClickListener(this);
        deleteMatch.setOnClickListener(this);
        assignMatch.setOnClickListener(this);

        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.createMatch:
                CreateMatch_Fragment createMatch_fragment = new CreateMatch_Fragment();
                fragmentManager.beginTransaction().replace(R.id.frameLayout,createMatch_fragment).addToBackStack("home").commit();
                break;
            case R.id.uploadIdPass:
                Upload_Fragment upload_fragment = new Upload_Fragment();
                fragmentManager.beginTransaction().replace(R.id.frameLayout,upload_fragment).addToBackStack("home").commit();
                break;
            case R.id.deleteMatch:
                DeleteMatch_Fragment deleteMatch_fragment = new DeleteMatch_Fragment();
                fragmentManager.beginTransaction().replace(R.id.frameLayout,deleteMatch_fragment).addToBackStack("home").commit();
                break;
            case R.id.assignMatch:
                startActivity(new Intent(this, AssignedMatch.class));
                break;
        }

    }
}