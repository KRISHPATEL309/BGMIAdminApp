package com.krish.bgmiadminapp.match;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.krish.bgmiadminapp.R;

import java.util.List;

public class ReferenceAdpter extends RecyclerView.Adapter<ReferenceAdpter.ReferenceViewAdpter> {

    private Context context;
    private List<ReferenceData> list;

    public ReferenceAdpter(Context context, List<ReferenceData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ReferenceViewAdpter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.reference_layout,parent,false);

        return new ReferenceViewAdpter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReferenceViewAdpter holder, int position) {

        ReferenceData currentItem = list.get(position);
        holder.referTextview.setText(currentItem.getReferId());
        holder.referenceCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,PlayerData.class);
                intent.putExtra("ref_no",currentItem.getReferId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ReferenceViewAdpter extends RecyclerView.ViewHolder {

        private TextView  referTextview;
        private CardView referenceCard;
        public ReferenceViewAdpter(@NonNull View itemView) {
            super(itemView);
            referTextview = itemView.findViewById(R.id.referTextview);
            referenceCard = itemView.findViewById(R.id.referenceCard);
        }
    }
}
