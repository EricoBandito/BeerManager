package com.example.beermanager.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.beermanager.Classes.Player;
import com.example.beermanager.R;
import java.util.ArrayList;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.MyViewHolder> {
    ArrayList<Player> _playersList;
    private Context context;
    private OnEditListener mOnEditListener;

    public PlayerListAdapter(Context ct, ArrayList<Player> playersList, OnEditListener onEditListener){
        context = ct;
        _playersList = playersList;
        this.mOnEditListener = onEditListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.player_row, viewGroup, false);
        return new MyViewHolder(view, mOnEditListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.playerName.setText(_playersList.get(i).playerName);
        myViewHolder.playerType.setText(_playersList.get(i).playerType);
    }

    @Override
    public int getItemCount() {
        return  _playersList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView playerName, playerType;
        ImageView playerEdit;
        OnEditListener onEditListener;

        public MyViewHolder(@NonNull View itemView, OnEditListener onEditListener) {
            super(itemView);
            playerName = itemView.findViewById(R.id.tv_playerName);
            playerType = itemView.findViewById(R.id.tv_playerType);
            playerEdit = itemView.findViewById(R.id.img_editPlayer);

            this.onEditListener = onEditListener;

            playerEdit.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onEditListener.onEditClick(getAdapterPosition());
        }
    }

    public interface OnEditListener{
        void onEditClick(int position);
    }
}
