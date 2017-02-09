package com.ness.virtualtour;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class InfraListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvInfra;
    private Context context;
    private List<InfraDO> infraDOList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infra_list);
        context = this;
        InitView();
    }


    private void InitView() {

        lvInfra = (ListView) findViewById(R.id.lv_infra_list);
        infraDOList = getInfraItem();
        InfraListAdapter adapter = new InfraListAdapter(context, infraDOList);
        lvInfra.setAdapter(adapter);
        lvInfra.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        InfraDO infraDO = infraDOList.get(position);
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("widgetPosition", position);
        intent.putExtra("Information", infraDO);
        startActivity(intent);

    }

    private List<InfraDO> getInfraItem() {

        String loremStr = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin scelerisque convallis leo eget bibendum. Aenean venenatis, dui ut luctus maximus, lectus ante bibendum nibh, et faucibus sem ipsum ac lacus. Praesent tellus velit, sodales sed maximus eu, rutrum at quam. Aenean dapibus ligula quis leo gravida lobortis. Nulla fringilla bibendum lacus at laoreet. Sed placerat nunc arcu, ac auctor enim fermentum eget. Vivamus euismod urna at risus pharetra eleifend. Ut maximus turpis at pellentesque pulvinar.";
        List<InfraDO> infraDOList = new ArrayList<>();
        InfraDO infraDO1 = new InfraDO();
        infraDO1.setText("Lobby");
        infraDO1.setDescription(loremStr);
        infraDOList.add(infraDO1);

        InfraDO infraDO2 = new InfraDO();
        infraDO2.setText("Reception");
        infraDO2.setDescription(loremStr);
        infraDOList.add(infraDO2);

        InfraDO infraDO3 = new InfraDO();
        infraDO3.setText("Inside Office");
        infraDO3.setDescription(loremStr);
        infraDOList.add(infraDO3);

        InfraDO infraDO4 = new InfraDO();
        infraDO4.setText("Canteen");
        infraDO4.setDescription(loremStr);
        infraDOList.add(infraDO4);

        InfraDO infraDO5 = new InfraDO();
        infraDO5.setText("Conference");
        infraDO5.setDescription(loremStr);
        infraDOList.add(infraDO5);

        return infraDOList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.others, menu);
        return true;
    }
}
