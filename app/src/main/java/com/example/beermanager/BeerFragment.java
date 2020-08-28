package com.example.beermanager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beermanager.Adapters.BeerCountListAdapter;

import org.jetbrains.annotations.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BeerFragment extends Fragment {
    @Nullable

    private String mMembers[], mCounts[];
    private RecyclerView mRecycleView;
    private Context context;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View beersLayout = inflater.inflate(R.layout.fragment_beer,container,false);
        context = this.getContext();
        mMembers = getResources().getStringArray(R.array.members);
        mCounts = getResources().getStringArray(R.array.counts);

        mRecycleView = beersLayout.findViewById(R.id.recyclerView);

        BeerCountListAdapter beerCountListAdapter = new BeerCountListAdapter(context,mMembers,mCounts);
        mRecycleView.setAdapter(beerCountListAdapter);
        mRecycleView.setLayoutManager(new LinearLayoutManager(context));

        return beersLayout;



    }
}
