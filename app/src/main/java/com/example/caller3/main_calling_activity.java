package com.example.caller3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class main_calling_activity extends AppCompatActivity {
TabLayout tabLayout;
ViewPager fragment;
you_called_helber db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calling_activity);

        db =you_called_helber.getpointer(this);
        db.open();
        you_colled y=new you_colled(2,"011","today","unknown");
        boolean x=db.insert(y);
        ArrayList<you_colled>lis=db.show_all();
        db.close();

        Toast.makeText(getBaseContext(),""+x,Toast.LENGTH_LONG).show();
        for (int i=0;i<lis.size();i++)
            Toast.makeText(getBaseContext(),lis.get(i).getId()+" "+lis.get(i).getPhone()+""+lis.get(i).getWhen()+""+lis.get(i).getName(),Toast.LENGTH_LONG).show();

        tabLayout=findViewById(R.id.tablayout_callingact);
        fragment=findViewById(R.id.viewpager_frag);

        adabter_fragment adabter =new adabter_fragment(getSupportFragmentManager(),FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adabter.add_tab(new mytabes("call",new calling()));
        adabter.add_tab(new mytabes("lists",new called_list()));
        fragment.setAdapter(adabter);
         tabLayout.setupWithViewPager(fragment);

    }
}
