package com.example.caller3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adabter extends RecyclerView.Adapter<Adabter.holder> {

    ArrayList<names_class> names ;
    Listener listener;
    Context con;

    public ArrayList<names_class> getNames() {
        return names;
    }

    public void setNames(ArrayList<names_class> names) {
        this.names = names;
    }

    public Adabter(ArrayList<names_class> names, Listener listener) {
        this.names = names;
        this.listener=listener;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_act_card_view,null,false);
        holder hold =new holder(v);
        con =parent.getContext();
        return hold;
    }

    @Override
    public void onBindViewHolder(@NonNull final holder holder, int position) {
        names_class na =names.get(position);
        holder.name.setText(na.getName());
        holder.phone.setText(na.getPhone());
        holder.name.setTag(na.getId());
        if (na.getImage() != null&&!na.getImage().isEmpty())
            holder.im.setImageURI(Uri.parse(na.getImage()));
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                Uri u =Uri.parse("tel:"+holder.phone.getText().toString());
                intent.setData(u);
                con.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    class holder extends RecyclerView.ViewHolder{
        ImageView im;
        TextView name,phone;
        Button btn;
        public holder(@NonNull View v) {
            super(v);
            im =v.findViewById(R.id.image_card_view);
            name =v.findViewById(R.id.name_card_view);
            phone =v.findViewById(R.id.phone_card_view);
            btn=v.findViewById(R.id.call_card_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onclick(Integer.parseInt(name.getTag()+""));
                }
            });
        }
    }

}
