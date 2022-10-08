package com.krish.bgmiadminapp.match;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.krish.bgmiadminapp.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateMatch_Fragment extends Fragment {

    TextInputLayout date,time,referencenum,maxParticipants,pricepool,matchCharge;

    Spinner matchTime,matchCategories;
    ImageView matchImage;
    Button chooseImage,upload;

    private final int REQ = 1;
    private Bitmap bitmap;

    private String category1,category2;

    private ProgressDialog pd;
    private DatabaseReference reference,reference2,query;
    private StorageReference storageReference;
    String DownloadUrl = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_match_, container, false);

        date = view.findViewById(R.id.date);
        time = view.findViewById(R.id.time);
        referencenum = view.findViewById(R.id.referencenum);
        maxParticipants = view.findViewById(R.id.maxParticipants);
        matchCharge = view.findViewById(R.id.matchCharge);
        pricepool = view.findViewById(R.id.pricepool);
        matchTime = view.findViewById(R.id.matchTime);
        matchCategories = view.findViewById(R.id.matchCategories);
        matchImage = view.findViewById(R.id.matchImage);
        chooseImage = view.findViewById(R.id.chooseImage);
        upload = view.findViewById(R.id.upload);

        reference = FirebaseDatabase.getInstance().getReference().child("Matches");
        reference2 = FirebaseDatabase.getInstance().getReference().child("Reference Numbers");
        query = FirebaseDatabase.getInstance().getReference().child("Reference Numbers");
        storageReference = FirebaseStorage.getInstance().getReference().child("Matches");

        pd = new ProgressDialog(getContext());

        String[] item = new String[]{"Select Match Duration","Morning","Afternoon", "Evening"};
        matchTime.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,item));

        matchTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category1 = matchTime.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] items = new String[]{"Select Match Category","Livik","Erangle", "TDM"};
        matchCategories.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,items));

        matchCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category2 = matchCategories .getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidation();
            }
        });

        return view;
    }

    private void checkValidation() {
        String id = referencenum.getEditText().getText().toString();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(date.getEditText().getText().toString().isEmpty()){
                    date.setError("Empty");
                    date.requestFocus();
                }else if (time.getEditText().getText().toString().isEmpty()) {
                    time.setError("Empty");
                    time.requestFocus();
                }else if (maxParticipants.getEditText().getText().toString().isEmpty()){
                    maxParticipants.setError("Empty");
                    maxParticipants.requestFocus();
                }else if (matchCharge.getEditText().getText().toString().isEmpty()){
                    matchCharge.setError("Empty");
                    matchCharge.requestFocus();
                }else if (referencenum.getEditText().getText().toString().isEmpty()){
                    referencenum.setError("Empty");
                    referencenum.requestFocus();
                }else if (pricepool.getEditText().getText().toString().isEmpty()){
                    pricepool.setError("Empty");
                    pricepool.requestFocus();
                }else if (category1.equals("Select Match Duration")){
                    Toast.makeText(getContext(),"please select Match Duration",Toast.LENGTH_SHORT).show();
                }else if (category1.equals("Select Match Category")){
                    Toast.makeText(getContext(),"please select Match Category",Toast.LENGTH_SHORT).show();
                }else if (bitmap == null){
                    Toast.makeText(getContext(),"please select Image Category",Toast.LENGTH_SHORT).show();
                }else if (snapshot.hasChild(id)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("This Reference Id is Already Used...");
                    builder.setCancelable(true);
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog dialog = null;
                    try {
                        dialog=builder.create();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(dialog!=null){
                        dialog.show();
                    }
                }
                else {
                    uploadImage();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void uploadImage() {
        pd.setMessage("Uploading...");
        pd.setCancelable(false);
        pd.show();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalimg = baos.toByteArray();
        final StorageReference filePath;
        filePath = storageReference.child(referencenum.getEditText().getText().toString()+"jpg");
        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    uploadData(uri);
                                }
                            });
                        }
                    });
                }else {
                    pd.dismiss();
                    Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void uploadData(Uri uri) {
        String Mdate = date.getEditText().getText().toString();
        String Mtime = time.getEditText().getText().toString();
        String Ref_Id = referencenum.getEditText().getText().toString();
        String Slot = maxParticipants.getEditText().getText().toString();
        String Charge = matchCharge.getEditText().getText().toString();
        String Pricepool = pricepool.getEditText().getText().toString();
        String roomId = "Not Available";
        String roomPass = "Not Available";

        Calendar callforDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yy");
        String date = currentDate.format(callforDate.getTime());

        Calendar callforTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
        String time = currentTime.format(callforTime.getTime());

        MatchData matchData = new MatchData(date,time,Mdate,Mtime,Ref_Id,Charge,Slot,Pricepool,category1,category2,uri.toString(),roomId,roomPass);

        ReferenceData referenceData = new ReferenceData(Ref_Id);
        reference2.child(Ref_Id).setValue(referenceData);
        reference.child(category1).child(Ref_Id).setValue(matchData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                pd.dismiss();
                Toast.makeText(getContext(),"Data Uploaded",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(getContext(),"Something Went Worng",Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void openGallery() {
        Intent pickimage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickimage,REQ);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ &&  resultCode == getActivity().RESULT_OK){
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            matchImage.setImageBitmap(bitmap);
        }
    }
}