package com.example.namenumarology;

import android.content.Intent;
import android.database.Cursor;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    Recycle_adapter recycle_adapter;
    List<Cardview_data> cardview_data;
    String numname []={"Destiny","Soul_Urge","Personality","Hidden_Passion","Karma","Cornerstone_Capstone"};
    DatabaseAssets dbs;
    String name,lang;
    TextView messagetext;
    Button addfav;
    int res[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        cardview_data=new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        messagetext=(TextView)findViewById(R.id.messagetexts);
        addfav=(Button)findViewById(R.id.add_name);

        dbs=DatabaseAssets.getInstance(getApplicationContext());
        dbs.open();
        Intent result=getIntent();
        res=result.getIntArrayExtra("result");
        name=result.getStringExtra("name").toUpperCase();
        lang=result.getStringExtra("lang");
        if(lang.compareTo("Select Language")==0){
            lang="English";
        }
        for(int i=0;i<5;i++){
            getdatas(i,numname[i]);
        }
        getdata(name.charAt(0),numname[5]);
        recycle_adapter =new Recycle_adapter(this,cardview_data);
        recyclerView.setAdapter(recycle_adapter);

        addfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cur=dbs.getdata("favnames");
                boolean check=true;
                while(cur.moveToNext()){
                    String fname = (String)cur.getString(cur.getColumnIndex("name"));
                    if((name.compareTo(fname))==0){
                        Toast toast=Toast.makeText(getApplicationContext(),"Name already added",Toast.LENGTH_SHORT);
                        toast.show();
                        check=false;
                    }
                }
                if(check){
                dbs.insertdata(name);
                Toast toast=Toast.makeText(getApplicationContext(),"name added to favs",Toast.LENGTH_SHORT);
                toast.show();
            }}
        });

    }

    public void getdatas(int x,String table) {
        Cursor cus = dbs.getdata(res[x], table+"_"+lang);
        add_data(cus,table);
    }

    public void getdata(char x,String table){
        Cursor cus = dbs.getdata(x, table+"_"+lang);
        add_data(cus,table);

    }

    public void add_data(Cursor cus,String table){
        while (cus.moveToNext()) {

            cardview_data.add(
                    new Cardview_data(
                            cus.getString(0),
                            name,
                            table+" Number",
                            cus.getString(1)
                    )
            );
        }
    }

    @Override
    protected void onDestroy() {
        if(recycle_adapter.speach!=null){
        recycle_adapter.speach.stop();
        recycle_adapter.speach.shutdown();
        }
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        if(recycle_adapter.speach!=null){
            recycle_adapter.speach.stop();
            recycle_adapter.speach.shutdown();
        }
        super.onStop();
    }
}
