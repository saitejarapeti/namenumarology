package com.example.namenumarology;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.Math;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHelperClass databases;
    TextView youfav,favnames,info;
    EditText editname;
    Button clkbtn;
    Main2Activity main2Activity;
    String alp []={"AIJQY","BKR","CGLS","DMT","HENX","UVW","OZ","PF"};
    String vowels="AEIOU",lang;

    Spinner language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info=(TextView)findViewById(R.id.info);
        editname=(EditText) findViewById(R.id.editname);
        favnames=(TextView)findViewById(R.id.favbtn);
        clkbtn =(Button)findViewById(R.id.buttontoscore);
        youfav=(TextView)findViewById((R.id.favbtn));
        language=(Spinner)findViewById(R.id.lang);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.languages,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        language.setAdapter(adapter);
        language.setOnItemSelectedListener(this);
        databases = new DatabaseHelperClass(this);

        clkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score();
            }
        });

        favnames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent favnams=new Intent(getApplicationContext(),Favnames.class);
                startActivity(favnams);
            }
        });
    }


    public int [] result(String names){
        int total []={0,0,0,0,0};
        int mode []=new int[names.length()];
        //to get tot of a number
        String name=names.toUpperCase();
        for(int i=0;i<name.length();i++){
            for(int j=0;j<alp.length;j++){
                for(int k=0;k<alp[j].length();k++){
                    if(name.charAt(i)==alp[j].charAt(k)){
                        total[0]+=(j+1);
                        mode[i]=j+1;
                        for(int l=0;l<vowels.length();l++){
                            if(name.charAt(i)==vowels.charAt(l)){
                                total[1]+=(j+1);
                                break;
                            }
                            else if(l==vowels.length()-1){
                                total[2]+=(j+1);
                            }
                        }
                        break;
                    }
                }
            }
        }

        for(int i=0;i<3;i++){
        while(total[i]>9&&total[i]!=22&&total[i]!=11){
           total[i]=total(total[i]);
        }}

        total[3]=mode(mode,mode.length);
        total[4]=total(total[2]);
        return total;
    }

    static int mode(int a[],int n) {
        int maxvalue = 0, maxcount = 0, i, j;
        for (i = 0; i < n; ++i) {
            int count = 0;
            for (j = 0; j < n; ++j) {
                if (a[j] == a[i])
                    ++count;
            }
            if (count > maxcount) {
                maxcount = count;
                maxvalue = a[i];
            }
        }
        return maxvalue;
    }

    static int total(int total){
        int temp=total,x=0;
        while(temp!=0&&total>9){
            x+=temp%10;
            temp= (int) Math.floor(temp/10);
        }
        return x;
    }

    public void score(){
        String name;
        int res [];
        name=editname.getText().toString();
        if(!name.isEmpty()){
            Intent result= new Intent(getApplicationContext(),Main2Activity.class);
            res=result(name);
            result.putExtra("result",res);
            result.putExtra("name",name);
            result.putExtra("lang",lang);
            startActivity(result);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        lang=parent.getItemAtPosition(position).toString();
        change(lang);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void change(String lang){
        if(lang.compareTo("Telugu")==0){
            info.setText("మీ పేరు రాయండి");
            favnames.setText("ఇష్టమైన పేర్లు");
            clkbtn.setText("ఇక్కడ నొక్కండి");
        }
        else if(lang.compareTo("Hindi")==0){
            info.setText("अपना नाम दर्ज करें");
            favnames.setText("पसंदीदा नाम");
            clkbtn.setText("यहां क्लिक करे");
        }

        else if(lang.compareTo("English")==0){
            info.setText("Enter Your Name");
            favnames.setText("Your Fav names");
            clkbtn.setText("click Here..!");
        }
    }

}
