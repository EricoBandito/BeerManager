package com.example.beermanager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class AddPlayerDialog extends AppCompatDialogFragment {

    private EditText editTextName;
    private RadioGroup radioGroup;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_player_dialog, null);

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


                        int selectedRadioId = radioGroup.getCheckedRadioButtonId();
                        RadioButton selectedRadioButton = getActivity().findViewById(selectedRadioId);

                        String playerName = editTextName.getText().toString();
                        String playerType = "Manager";

                        addPlayer(playerName,playerType);
                    }
                });

        return  dialogBuilder.create();
    }


    private void addPlayer(String playerName, String playerType)
    {
        
    }
}
