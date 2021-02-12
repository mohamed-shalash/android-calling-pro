package com.example.caller3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class calling extends Fragment {

    public calling() {
        // Required empty public constructor
    }

    private static final String ARG_PARAM2 = "p2";
    Context context;

    private String name;


   /* public static called_list newInstance(String param2) {
        called_list fragment = new called_list();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM2,param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name =getArguments().getString(ARG_PARAM2);
        }
    }*/

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_calling, container, false);

        final EditText numm =v.findViewById(R.id.fragment_calling_editText);
        Button btn =v.findViewById(R.id.fragment_calling_button);
        RecyclerView rc =v.findViewById(R.id.fragment_calling_recycleview);



       // Toast.makeText(context,c.getPhone()+""+c.getWhen()+""+x,Toast.LENGTH_SHORT).show();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                Uri u =Uri.parse("tel:"+numm.getText().toString());
                intent.setData(u);
                startActivity(intent);
            }
        });




        return v;
    }




}
