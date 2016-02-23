package com.ethereal.team.kochu;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.ethereal.team.kochu.Adapter.RecyclerViewAdapter;
import com.ethereal.team.kochu.DefinitionFetcher.Definition;
import com.ethereal.team.kochu.DefinitionFetcher.DefinitionFetcher;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdapter(getApplicationContext(), createList(4));
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<Definition> createList(int size) {

        DefinitionFetcher fetcher = new DefinitionFetcher("Name");
        ArrayList<Definition> definitions;
        definitions = fetcher.fetch();

        for (int i = 0; i < definitions.size(); i++) {
            Toast.makeText(getApplicationContext(), definitions.get(i).getDefinition(), Toast.LENGTH_SHORT).show();
            Log.v("Definition:",definitions.get(i).getDefinition());
        }
        return definitions;
    }
}
