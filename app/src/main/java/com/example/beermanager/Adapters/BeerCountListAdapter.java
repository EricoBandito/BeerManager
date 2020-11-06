package com.example.beermanager.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beermanager.Classes.Player;
import com.example.beermanager.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BeerCountListAdapter extends RecyclerView.Adapter <BeerCountListAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Player> players;

    public BeerCountListAdapter(Context context, ArrayList<Player> players)
    {
        this.players = players;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.beers_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.player.setText(players.get(position).getPlayerName());
        holder.count.setText(String.valueOf(players.get(position).getBeerCount()));
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView player, count;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            player = itemView.findViewById(R.id.txt_player_name);
            count = itemView.findViewById(R.id.txt_beerCount);
        }
    }
}
