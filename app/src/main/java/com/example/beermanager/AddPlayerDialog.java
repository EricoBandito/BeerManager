package com.example.beermanager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.beermanager.Classes.Player;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AddPlayerDialog extends AppCompatDialogFragment {

    private EditText editTextName;
    private RadioGroup radioGroup;
    private DatabaseReference database;
    private View view;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.add_player_dialog, null);

        dialogBuilder
                .setView(view)
                .setTitle("Add Player")
                .setIcon(R.drawable.ic_add_player)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("Add Player", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Player newPlayer = createPlayer();
                        addPlayer(newPlayer.playerName,newPlayer.playerType);
                    }
                });

        return  dialogBuilder.create();
    }

    private Player createPlayer(){
        radioGroup = view.findViewById(R.id.radioGroup);
        int selectedRadioId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = view.findViewById(selectedRadioId);
        editTextName = view.findViewById(R.id.et_name);
        String playerName = editTextName.getText().toString();
        String playerType = selectedRadioButton.getText().toString();

        return new Player(playerName, playerType);
    }
    private void addPlayer(String playerName, String playerType)
    {
        Player newPlayer = new Player(playerName, playerType);

        database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersRef = database.child("users");
        usersRef.push().setValue(newPlayer);


    }
}
