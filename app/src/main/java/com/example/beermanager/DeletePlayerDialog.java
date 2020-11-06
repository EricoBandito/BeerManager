package com.example.beermanager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.beermanager.Classes.Player;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DeletePlayerDialog extends AppCompatDialogFragment {
    private Player player;
    public DeletePlayerDialog(Player player)
    {
        this.player = player;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.delete_player_dialog, null);

        Resources res = getResources();

        TextView deletePlayer = view.findViewById(R.id.txt_delete_verification);
        deletePlayer.setText(String.format(res.getString(R.string.str_delete_verification), player.getPlayerName()));

        TextView beerCountWarning = view.findViewById(R.id.beer_remaining_warning);
        beerCountWarning.setText(res.getQuantityString(R.plurals.str_delete_beercount, player.getBeerCount(), player.getPlayerName(), player.getBeerCount()));

        dialogBuilder
                .setView(view)
                .setTitle("Delete Player")
                .setIcon(R.drawable.ic_delete)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                        DatabaseReference membersRef = database.child("teams").child("jets").child("members");
                        membersRef.child(player.getId()).removeValue();
                    }
                });

        return dialogBuilder.create();
    }


}
