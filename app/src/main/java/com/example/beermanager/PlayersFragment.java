package com.example.beermanager;

import android.content.Context;
import android.os.Bundle;
import org.jetbrains.annotations.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
    RecyclerView recyclerView;
    private ArrayList<Player> playersList;
    private PlayerListAdapter playerListAdapter;
    private Context context;
    private PlayersFragment playersFragment = this;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View playersLayout = inflater.inflate(R.layout.fragment_players,container,false);

        assignInstanceVariables(playersLayout);
        setOnClickListeners(playersLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        getPlayers();

        return playersLayout;
    }

    private void assignInstanceVariables(View playersLayout) {
        context = this.getContext();
        recyclerView = playersLayout.findViewById(R.id.recyclerView);
        playersList = new ArrayList<>();
        playerListAdapter = new PlayerListAdapter(context, playersList, playersFragment);
    }

    private void setOnClickListeners(View view) {
        ImageView typeInfo = view.findViewById(R.id.img_type_info);
        typeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlayerTypeInfoDialog();
            }
        });

        Button addPlayerButton = view.findViewById((R.id.btn_add_player));
        addPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddPlayerDialog();
            }
        });
    }



    private void getPlayers(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        Query query = database.child("teams").child("jets").child("members");

        //TODO Change to addChildEventListener
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Helper.clearData(playersList, playerListAdapter);

                for(DataSnapshot member : snapshot.getChildren()){
                    Player player = member.getValue(Player.class);
                    playersList.add(player);
                }

                playerListAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(playerListAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void openAddPlayerDialog() {
        AddPlayerDialog addPlayerDialog = new AddPlayerDialog();
        addPlayerDialog.show(getFragmentManager(), "Add Player Dialog");
    }

    private void openPlayerTypeInfoDialog() {
        PlayerTypeInfoDialog playerTypeInfoDialog = new PlayerTypeInfoDialog();
        playerTypeInfoDialog.show(getFragmentManager(), "Player Type Info Dialog");
    }

    private void openEditPlayerDialog(Player player) {
        EditPlayerDialog editPlayerDialog = new EditPlayerDialog(player);
        editPlayerDialog.show(getFragmentManager(), "Edit Player Dialog");
    }

    @Override
    public void onEditClick(int position) {
        openEditPlayerDialog(playersList.get(position));
    }
}
