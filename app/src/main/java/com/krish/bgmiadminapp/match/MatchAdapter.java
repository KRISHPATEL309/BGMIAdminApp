package com.krish.bgmiadminapp.match;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.krish.bgmiadminapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewAdapter> {
    private Context context;
    private List<MatchData> list;
    private String matchMode;

    public MatchAdapter() {
    }

    public MatchAdapter(Context context, List<MatchData> list, String matchMode) {
        this.context = context;
        this.list = list;
        this.matchMode = matchMode;
    }


    @NonNull
    @Override
    public MatchViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.match_layout,parent,false);

        return new MatchViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewAdapter holder, @SuppressLint("RecyclerView") int position) {
        MatchData currentItem = list.get(position);
        holder.matchDate.setText("Match Date: "+currentItem.getDate());
        holder.matchTime.setText("Match Time: "+currentItem.getTime());
        holder.uploadDate.setText("Upload Date: "+currentItem.getUploaddate());
        holder.uploadTime.setText("Upload Time: "+currentItem.getUploadtime());
        holder.refId.setText("Reference Id: "+currentItem.getReferencenum());
        holder.slots.setText("Slots: "+currentItem.getMaxParticipants());
        holder.match_categories.setText("Match Categories: "+currentItem.getMatchTime());
        holder.room_id.setText("Room Id: "+currentItem.getRoom_Id());
        holder.room_password.setText("Room Password: "+currentItem.getRoom_Pass());
        holder.matchMode.setText(currentItem.getMatchCategories());
        holder.pricePool.setText("Price Pool: \n"+currentItem.getPricepool());
        holder.entryfee.setText("Entry Fee: "+currentItem.getCharge());

        Picasso.get().load(currentItem.getMatchImage()).into(holder.matchimg);

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are You sure want to delete this Match ?");
                builder.setCancelable(true);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Matches");
                        reference.child(currentItem.getMatchTime()).child(currentItem.getReferencenum()).removeValue()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(v.getContext(), "Deleted...", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(v.getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

                            }
                        });
                        notifyItemRemoved(position);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = null;
                try {
                    dialog = builder.create();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (dialog != null)
                    dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MatchViewAdapter extends RecyclerView.ViewHolder {

        private ImageView matchimg;
        private TextView uploadDate,uploadTime,refId,slots,match_categories,matchDate,matchTime,room_id,room_password,matchMode,pricePool,entryfee;
        private Button deletebtn;

        public MatchViewAdapter(@NonNull View itemView) {
            super(itemView);
            matchimg = itemView.findViewById(R.id.matchimg);
            uploadDate = itemView.findViewById(R.id.uploadDate);
            uploadTime = itemView.findViewById(R.id.uploadTime);
            refId = itemView.findViewById(R.id.refId);
            slots = itemView.findViewById(R.id.slots);
            match_categories = itemView.findViewById(R.id.match_categories);
            matchDate = itemView.findViewById(R.id.matchDate);
            matchTime = itemView.findViewById(R.id.matchTime);
            room_id = itemView.findViewById(R.id.room_id);
            room_password = itemView.findViewById(R.id.room_password);
            matchMode = itemView.findViewById(R.id.matchMode);
            pricePool = itemView.findViewById(R.id.pricePool);
            entryfee = itemView.findViewById(R.id.entryfee);
            deletebtn = itemView.findViewById(R.id.deletebtn);
        }
    }
}
