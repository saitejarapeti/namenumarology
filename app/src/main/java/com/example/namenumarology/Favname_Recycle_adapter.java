package com.example.namenumarology;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Favname_Recycle_adapter extends RecyclerView.Adapter<Favname_Recycle_adapter.Fav_recycle_viewholder> {
    private List<Favname_cardview_data> favname_cardview_data;
    private Context favnamecon;
    public Favname_Recycle_adapter(Context favnamecon,List<Favname_cardview_data> favname_cardview_data) {
        this.favname_cardview_data = favname_cardview_data;
        this.favnamecon=favnamecon;
    }

    @NonNull
    @Override
    public Fav_recycle_viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(favnamecon);
        View view=layoutInflater.inflate(R.layout.favname_layout,null);
        return new Favname_Recycle_adapter.Fav_recycle_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Fav_recycle_viewholder holder, int position) {
        Favname_cardview_data data=favname_cardview_data.get(position);
        holder.favname.setText(data.getName());
    }



    @Override
    public int getItemCount() {
        return favname_cardview_data.size();
    }

    public class Fav_recycle_viewholder extends RecyclerView.ViewHolder {

        TextView favname;

        public Fav_recycle_viewholder(@NonNull View itemView) {
            super(itemView);
            favname=itemView.findViewById(R.id.favname);

        }
    }
}
