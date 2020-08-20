package com.example.beermanager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.beermanager.Adapters.PlayerLIstAdapter;
import com.example.beermanager.Classes.EditPlayerDialog;

public class PlayersFragment extends Fragment {
    private Button addPlayerButton;
    private Button tempEditPlayerButton;
    RecyclerView recyclerView;
    String playerNames[], playerTypes[];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View playersLayout = inflater.inflate(R.layout.fragment_players,container,false);
        playerNames = getResources().getStringArray(R.array.players);
        playerTypes = getResources().getStringArray(R.array.types);

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

        tempEditPlayerButton = playersLayout.findViewById(R.id.temp_edit_player_button);

        tempEditPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditPlayerDialog();
            }
        });

        recyclerView = playersLayout.findViewById(R.id.recyclerView);

        PlayerLIstAdapter adapter = new PlayerLIstAdapter(this.getContext(), playerNames, playerTypes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
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

}
