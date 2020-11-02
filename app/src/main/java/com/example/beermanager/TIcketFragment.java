package com.example.beermanager;

import android.os.Bundle;
import org.jetbrains.annotations.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.beermanager.Adapters.PlayerListAdapter;
import com.example.beermanager.Classes.Player;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TIcketFragment extends Fragment {
    private ArrayList<Player> playersList;
    private DatabaseReference database;
    private Spinner playersSpinner;
    private ArrayAdapter<Player> adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View ticketsLayout = inflater.inflate(R.layout.fragment_ticket,container,false);
        playersSpinner = ticketsLayout.findViewById(R.id.spn_players);
        database = FirebaseDatabase.getInstance().getReference();
        playersList = new ArrayList<>();

        //TODO Refactor adapter, ClearData(), getPlayers() in this fragment and Players Fragment.
        adapter = new ArrayAdapter<Player>(getContext(), android.R.layout.simple_spinner_item, playersList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ClearData();
        getPlayers();

        return ticketsLayout;
    }

    private void getPlayers(){
        Query query = database.child("teams").child("jets").child("members");

        //TODO Change to addChildEventListener
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot member : snapshot.getChildren()){
                    Player player = member.getValue(Player.class);
                    playersList.add(player);
                }
                setupAdapter();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setupAdapter() {
        adapter.addAll(playersList);
        playersSpinner.setAdapter(adapter);
    }

    private void ClearData(){

        if (playersList != null) {
            playersList.clear();

            if (adapter != null)
            {
                adapter.notifyDataSetChanged();
            }
        }

        playersList = new ArrayList<>();
    }
}
