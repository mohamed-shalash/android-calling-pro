package com.example.caller3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RecyclerView rc;
    names_db_helber db;
    FloatingActionButton flb;
    Adabter adapter;
    int add_new_card =1;
    public static String word ="iD";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drow);
        rc=findViewById(R.id.main_act_adabter);
        navigationView=findViewById(R.id.nav);
        flb=findViewById(R.id.main_flab);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},5);
        }


        db =names_db_helber.getpointer(this);
        db.open();
        ArrayList<names_class> arr =db.show_all();
        db.close();
        adapter =new Adabter(arr, new Listener() {
            @Override
            public void onclick(int id) {
                Intent i=new Intent(getBaseContext(),edit_names.class);
                i.putExtra(word,id);
                startActivityForResult(i,0);
            }
        });
        rc.setAdapter(adapter);
        RecyclerView.LayoutManager lm =new GridLayoutManager(this,1);
        rc.setLayoutManager(lm);
        rc.setHasFixedSize(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.nav_CALL:
                        Intent i=new Intent(getBaseContext(),main_calling_activity.class);
                        startActivityForResult(i,add_new_card);
                        drawerLayout.closeDrawers();
                        return false;
                    case R.id.nav_home:
                        Toast.makeText(getBaseContext(),"home",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        return false;
                    case R.id.nav_add:
                        i=new Intent(getBaseContext(),edit_names.class);
                        startActivityForResult(i,add_new_card);
                        drawerLayout.closeDrawers();
                        return false;
                }
                drawerLayout.closeDrawers();
                return false;
            }
        });
        flb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        db.open();
        ArrayList<names_class> arr =db.show_all();
        adapter.setNames(arr);
        adapter.notifyDataSetChanged();
        db.close();

    }
}
