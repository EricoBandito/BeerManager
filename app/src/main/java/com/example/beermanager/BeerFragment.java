package com.example.beermanager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beermanager.Adapters.BeerCountListAdapter;
import com.example.beermanager.Adapters.PlayerListAdapter;
import com.example.beermanager.Classes.Player;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BeerFragment extends Fragment {
    @Nullable

    private RecyclerView recyclerView;
    private Context context;
    private ArrayList<Player> playersList;
    private BeerCountListAdapter beerCountListAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View beersLayout = inflater.inflate(R.layout.fragment_beer,container,false);

        assignInstanceVariables(beersLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        getPlayers();

        return beersLayout;
    }

    private void assignInstanceVariables(View beersLayout) {
        context = this.getContext();
        playersList = new ArrayList<>();
        recyclerView = beersLayout.findViewById(R.id.recyclerView);
        beerCountListAdapter = new BeerCountListAdapter(context, playersList);
    }

    private void getPlayers(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        Query query = database.child("teams").child("jets").child("members");

        //TODO Change to addChildEventListener
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Helper.clearData(playersList, beerCountListAdapter);

                for(DataSnapshot member : snapshot.getChildren()){
                    Player player = member.getValue(Player.class);
                    playersList.add(player);
                }

                beerCountListAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(beerCountListAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
