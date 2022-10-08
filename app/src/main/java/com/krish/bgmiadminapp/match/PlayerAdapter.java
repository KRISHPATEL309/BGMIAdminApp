package com.krish.bgmiadminapp.match;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.krish.bgmiadminapp.R;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewAdapter> {

    private Context context;
    private List<Choose_Squad> list;

    public PlayerAdapter(Context context, List<Choose_Squad> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PlayerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.choose_squad_layout,parent,false);
        return new PlayerViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewAdapter holder, int position) {

        Choose_Squad currentItem = list.get(position);

        holder.player1Id.setText(currentItem.getP1Id());
        holder.player2Id.setText(currentItem.getP2Id());
        holder.player3Id.setText(currentItem.getP3Id());
        holder.player4Id.setText(currentItem.getP4Id());

        holder.player1Name.setText(currentItem.getP1N());
        holder.player2Name.setText(currentItem.getP2N());
        holder.player3Name.setText(currentItem.getP3N());
        holder.player4Name.setText(currentItem.getP4N());

        holder.leaderName.setText(currentItem.getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PlayerViewAdapter extends RecyclerView.ViewHolder {

        private TextView leaderName,player1Id,player2Id,player3Id,player4Id,player1Name,player2Name,player3Name,player4Name;

        public PlayerViewAdapter(@NonNull View itemView) {
            super(itemView);

            leaderName=itemView.findViewById(R.id.leaderName);
            player1Id=itemView.findViewById(R.id.player1Id);
            player2Id=itemView.findViewById(R.id.player2Id);
            player3Id=itemView.findViewById(R.id.player3Id);
            player4Id=itemView.findViewById(R.id.player4Id);
            player1Name=itemView.findViewById(R.id.player1Name);
            player2Name=itemView.findViewById(R.id.player2Name);
            player3Name=itemView.findViewById(R.id.player3Name);
            player4Name=itemView.findViewById(R.id.player4Name);

        }
    }
}
