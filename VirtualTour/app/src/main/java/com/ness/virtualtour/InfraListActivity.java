package com.ness.virtualtour;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class InfraListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvInfra;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infra_list);
        context = this;
        InitView();
    }


    private void InitView() {

        lvInfra = (ListView) findViewById(R.id.lv_infra_list);

        ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, new String[]{"Lobby", "Reception", "Inside Office", "Canteen"});
        lvInfra.setAdapter(arrayAdapter);
        lvInfra.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("widgetPosition", position);
        startActivity(intent);

    }
}
