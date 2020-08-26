package com.example.beermanager.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beermanager.R;

public class PlayerLIstAdapter extends RecyclerView.Adapter<PlayerLIstAdapter.MyViewHolder> {

    String _playerNames[], _playerTypes[];
    Context context;

    public PlayerLIstAdapter(Context ct, String playerNames[], String playerTypes[]){
        context = ct;
        _playerNames = playerNames;
        _playerTypes = playerTypes;

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
        myViewHolder._playerName.setText(_playerNames[i]);
        myViewHolder._playerType.setText(_playerTypes[i]);
    }

    @Override
    public int getItemCount() {
        return _playerNames.length;
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
