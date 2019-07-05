package com.example.user.recyclertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class recyclerView extends AppCompatActivity {

    private List<items> itemlists = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecAdapter recadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        //recycler
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recadapter = new RecAdapter(itemlists, this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recadapter);

    }


    public void addClicked(View view) {
        String titleReaded = ((EditText)findViewById(R.id.titleinput)).getText().toString();
        String bodyReaded = ((EditText)findViewById(R.id.bodyinput)).getText().toString();
        items newItem = new items(titleReaded, bodyReaded);
        itemlists.add(newItem);
        recadapter.notifyDataSetChanged();
    }
}
