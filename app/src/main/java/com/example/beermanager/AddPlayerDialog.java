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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AddPlayerDialog extends AppCompatDialogFragment {

    private View view;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.add_player_dialog, null);

        return buildDialog(dialogBuilder);
    }

    private Dialog buildDialog(AlertDialog.Builder dialogBuilder) {
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
                        addPlayertoDatabase(newPlayer);
                    }
                });

        return  dialogBuilder.create();
    }

    private Player createPlayer(){
        EditText editTextName = view.findViewById(R.id.et_name);
        String playerName = editTextName.getText().toString();

        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        int selectedRadioId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = view.findViewById(selectedRadioId);
        String playerType = selectedRadioButton.getText().toString();

        String uniqueID = UUID.randomUUID().toString();
        return new Player(uniqueID, playerName, playerType);
    }

    private void addPlayertoDatabase(Player player)
    {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        //TODO Reference the correct team when entering into database.
        DatabaseReference membersRef = database.child("teams").child("jets").child("members");

        membersRef.child(player.getId()).setValue(player);
    }
}
