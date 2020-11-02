package com.example.beermanager.Classes;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.beermanager.DeletePlayerDialog;
import com.example.beermanager.R;

import org.w3c.dom.Text;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;


public class EditPlayerDialog extends AppCompatDialogFragment {
    Player player;

    public EditPlayerDialog(Player player) {
        this.player = player;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.edit_player_dialog, null);
        EditText playerName = view.findViewById(R.id.et_name);
        playerName.setText(player.getPlayerName());


        dialogBuilder
                .setView(view)
                .setTitle("Edit Player")
                .setIcon(R.drawable.ic_edit_black_24dp)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DeletePlayerDialog deletePlayerDialog = new DeletePlayerDialog(player);
                        deletePlayerDialog.show(getFragmentManager(), "Delete Player Dialog");
                    }
                });


        return  dialogBuilder.create();


    }
}

