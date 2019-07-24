package com.example.namenumarology;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Recycle_adapter extends RecyclerView.Adapter<Recycle_adapter.recycle_viewholder> implements TextToSpeech.OnInitListener {
    private Context con;
    private List<Cardview_data> cardview_data_list;
    public TextToSpeech speach;
    public Recycle_adapter(Context con, List<Cardview_data> cardview_data_list) {
        this.con = con;
        this.cardview_data_list = cardview_data_list;

    }

    @NonNull
    @Override
    public recycle_viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(con);
        View view=layoutInflater.inflate(R.layout.cardview_num,null);
        return new recycle_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final recycle_viewholder holder, int position) {
        Cardview_data data=cardview_data_list.get(position);
        holder.name.setText(data.getName());
        holder.number.setText(data.getNum());
        holder.numname.setText(data.getNumroname());
        holder.textmessage.setText(data.getText_message());

        speach=new TextToSpeech(holder.textmessage.getContext(),this);
        holder.messbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.textmessage.getVisibility()==View.GONE){
                   holder.textmessage.setVisibility(View.VISIBLE);
                    if(speach.isSpeaking())
                        speach.stop();
                    speach.speak(holder.textmessage.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
                }
                else {
                    holder.textmessage.setVisibility(View.GONE);
                    speach.stop();
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return cardview_data_list.size();
    }



    public static class recycle_viewholder extends RecyclerView.ViewHolder {

        TextView numname,name,number,textmessage;
        Button messbtn;
        TextToSpeech speach;
        public recycle_viewholder(@NonNull View itemView) {
            super(itemView);
            numname=itemView.findViewById(R.id.numbertype);
            name=itemView.findViewById(R.id.name);
            number=itemView.findViewById(R.id.number);
            messbtn=itemView.findViewById(R.id.messbtns);
            textmessage=itemView.findViewById(R.id.messagetexts);
        }
        }

    @Override
    public void onInit(int status) {
        if(status==TextToSpeech.SUCCESS){
            int result=speach.setLanguage(Locale.ENGLISH);
            if(result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","language not supported");
            }
        }
        else {
            Log.e("TTS", "Initilization Failed!");
        }

    }





    }



