package com.example.beermanager;

import android.content.Context;
import android.os.Bundle;
import org.jetbrains.annotations.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.beermanager.Adapters.PlayerListAdapter;
import com.example.beermanager.Classes.EditPlayerDialog;
import com.example.beermanager.Classes.Player;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PlayersFragment extends Fragment implements PlayerListAdapter.OnEditListener {
    private Button addPlayerButton;
    private Button tempEditPlayerButton;
    RecyclerView recyclerView;
    private ArrayList<Player> playersList;
    private DatabaseReference database;
    private   PlayerListAdapter adapter;
    private Context context;
    private PlayersFragment playersFragment = this;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View playersLayout = inflater.inflate(R.layout.fragment_players,container,false);

        context = this.getContext();

        ImageView typeInfo = playersLayout.findViewById(R.id.img_type_info);
        typeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlayerTypeInfoDialog();
            }
        });

        addPlayerButton = playersLayout.findViewById((R.id.btn_add_player));
        addPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddPlayerDialog();
            }
        });

        recyclerView = playersLayout.findViewById(R.id.recyclerView);
        adapter = new PlayerListAdapter(context, playersList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        database = FirebaseDatabase.getInstance().getReference();

        playersList = new ArrayList<>();
        ClearData();
        getPlayers();

        return playersLayout;


    }

    public void openAddPlayerDialog() {
        AddPlayerDialog addPlayerDialog = new AddPlayerDialog();
        addPlayerDialog.show(getFragmentManager(), "Add Player Dialog");
    }

    public void openPlayerTypeInfoDialog() {
        PlayerTypeInfoDialog playerTypeInfoDialog = new PlayerTypeInfoDialog();
        playerTypeInfoDialog.show(getFragmentManager(), "Player Type Info Dialog");
    }

    public void openEditPlayerDialog() {
        EditPlayerDialog editPlayerDialog = new EditPlayerDialog();
        editPlayerDialog.show(getFragmentManager(), "Edit Player Dialog");
    }

    private void getPlayers(){
        Query query = database.child("teams").child("jets").child("members");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot member : snapshot.getChildren()){
                    Player player = new Player();
                    player.playerName = member.child("playerName").getValue().toString();
                    player.playerType = member.child("playerType").getValue().toString();

                    playersList.add(player);
                }

                adapter = new PlayerListAdapter(context, playersList, playersFragment);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void ClearData(){
        if (playersList != null)
        {
            playersList.clear();

            if (adapter != null)
            {
                adapter.notifyDataSetChanged();
            }
        }

        playersList = new ArrayList<>();
    }

    @Override
    public void onEditClick(int position) {
        openEditPlayerDialog();
    }
}
