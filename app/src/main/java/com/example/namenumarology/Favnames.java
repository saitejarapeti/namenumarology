package com.example.namenumarology;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Favnames extends AppCompatActivity {

    RecyclerView recyclerView;
    Favname_Recycle_adapter favname_recycle_adapter;
    List<Favname_cardview_data> favname_cardview_data;
    TextView favname;
    DatabaseAssets dbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favnames);
        favname_cardview_data=new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.favname_recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        favname=(TextView)findViewById(R.id.favname);
        dbs=DatabaseAssets.getInstance(getApplicationContext());
        dbs.open();
        Cursor cus=dbs.getdata("favnames");
        while(cus.moveToNext()){
            favname_cardview_data.add(
                    new Favname_cardview_data(
                            cus.getString(1)
                    )
            );
        }
        favname_recycle_adapter=new Favname_Recycle_adapter(this,favname_cardview_data);
        recyclerView.setAdapter(favname_recycle_adapter);
        dbs.close();
    }
}
