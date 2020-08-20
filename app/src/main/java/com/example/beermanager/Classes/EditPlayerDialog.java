package com.example.beermanager.Classes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.example.beermanager.R;

import static android.app.AlertDialog.THEME_HOLO_LIGHT;

public class EditPlayerDialog extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity(), THEME_HOLO_LIGHT);
        LayoutInflater inflater = getActivity().getLayoutInflater();
//        Change "add_player_dialog" name.
        View view = inflater.inflate(R.layout.add_player_dialog, null);

        dialogBuilder
                .setView(view)
                .setTitle("Edit Player")
                .setIcon(R.drawable.ic_edit_black_24dp)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Apply Changes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return  dialogBuilder.create();


    }
}

