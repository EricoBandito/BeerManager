package com.example.beermanager;

import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class Helper {
    static void clearData(ArrayList arrayList, RecyclerView.Adapter adapter) {
        if (arrayList != null)
        {
            arrayList.clear();

            if (adapter != null)
            {
                adapter.notifyDataSetChanged();
            }
        }

        arrayList = new ArrayList<>();
    }
}
