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

    private String mMembers[], mCounts[];
    private RecyclerView recyclerView;
    private Context context;
    private DatabaseReference database;
    private ArrayList<Player> playersList;
    private BeerCountListAdapter beerCountListAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View beersLayout = inflater.inflate(R.layout.fragment_beer,container,false);
        context = this.getContext();

        database = FirebaseDatabase.getInstance().getReference();
        playersList = new ArrayList<>();

        recyclerView = beersLayout.findViewById(R.id.recyclerView);

        beerCountListAdapter = new BeerCountListAdapter(context, playersList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        getPlayers();

        return beersLayout;



    }

    private void getPlayers(){
        Query query = database.child("teams").child("jets").child("members");

        //TODO Change to addChildEventListener
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClearData();

                for(DataSnapshot member : snapshot.getChildren()){
                    Player player = member.getValue(Player.class);
                    playersList.add(player);
                }

                beerCountListAdapter = new BeerCountListAdapter(context, playersList);
                recyclerView.setAdapter(beerCountListAdapter);
                beerCountListAdapter.notifyDataSetChanged();
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

            if (beerCountListAdapter != null)
            {
                beerCountListAdapter.notifyDataSetChanged();
            }
        }

        playersList = new ArrayList<>();
    }
}
