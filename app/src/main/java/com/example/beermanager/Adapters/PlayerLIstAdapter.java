package com.example.beermanager.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beermanager.Classes.Player;
import com.example.beermanager.R;

import java.util.ArrayList;

public class PlayerLIstAdapter extends RecyclerView.Adapter<PlayerLIstAdapter.MyViewHolder> {
    ArrayList<Player> _playersList;
    private Context context;

    public PlayerLIstAdapter(Context ct, ArrayList<Player> playersList){
        context = ct;
        _playersList = playersList;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.player_row, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder._playerName.setText(_playersList.get(i).playerName);
        myViewHolder._playerType.setText(_playersList.get(i).playerType);
    }

    @Override
    public int getItemCount() {
        return  _playersList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView _playerName, _playerType;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            _playerName = itemView.findViewById(R.id.tv_playerName);
            _playerType = itemView.findViewById(R.id.tv_playerType);


        }
    }
}
