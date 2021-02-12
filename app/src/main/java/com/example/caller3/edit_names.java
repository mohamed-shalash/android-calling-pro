package com.example.caller3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class edit_names extends AppCompatActivity {

    ImageView im;
    TextView num,name,phone,address;
    int card_id;
    names_db_helber db;
    Menu men;
    Uri imageuri =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_names);

        im =findViewById(R.id.names_edit_photo);
        num=findViewById(R.id.edit_num);
        name=findViewById(R.id.edit_name);
        phone=findViewById(R.id.edit_phone);
        address=findViewById(R.id.edit_address);
        db =names_db_helber.getpointer(this);

        Intent intent =getIntent();
        card_id=intent.getIntExtra(MainActivity.word,-1);

        if (card_id == -1){
            enable();
            db.open();
            names_class nc=db.show_last();
            if (nc ==null)
                num.setText("0");
                else num.setText(nc.getId()+1+"");
            db.close();
        }else{
            db.open();
            names_class na =db.show_some_id(card_id);
            db.close();
            num.setText(na.getId()+"");
            name.setText(na.getName());
            phone.setText(na.getPhone());
            address.setText(na.getAddress());
            if (na.getImage() !=null && !na.getImage().isEmpty())
                im.setImageURI(Uri.parse(na.getImage()));
            disable();
        }
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,122);
            }
        });
    }

    void enable(){
        num.setEnabled(false);
        name.setEnabled(true);
        phone.setEnabled(true);
        address.setEnabled(true);
        im.setEnabled(true);
    }
    void disable(){
        num.setEnabled(false);
        name.setEnabled(false);
        phone.setEnabled(false);
        address.setEnabled(false);
        im.setEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_names_menu,menu);
        men =menu;
        if (card_id == -1){
            menu.findItem(R.id.save).setVisible(true);
            menu.findItem(R.id.edit).setVisible(false);
            menu.findItem(R.id.delete).setVisible(false);
        }
        else{
            menu.findItem(R.id.save).setVisible(false);
            menu.findItem(R.id.edit).setVisible(true);
            menu.findItem(R.id.delete).setVisible(true);
        }
        return true;
    }

    boolean adding =false;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        MenuItem add =men.findItem(R.id.save);
        MenuItem edit =men.findItem(R.id.edit);
        MenuItem delete =men.findItem(R.id.delete);

        switch (item.getItemId()){
            case R.id.save:
                if (adding){
                    names_class n = new names_class(Integer.parseInt(num.getText().toString()),name.getText().toString(),phone.getText().toString(),
                            address.getText().toString(),imageuri.toString());
                    db.open();
                    db.update(n);
                    db.close();
                }
                else {
                   names_class n = new names_class(name.getText().toString(),phone.getText().toString(),
                           address.getText().toString(),imageuri.toString());
                   db.open();
                   db.add(n);
                   db.close();
                }
                finish();
                return false;
            case R.id.edit:
                add.setVisible(true);
                edit.setVisible(false);
                delete.setVisible(false);
                enable();
                adding=true;
                return false;
            case R.id.delete:
                db.open();
                db.delete(card_id);
                db.close();
                finish();
                return false;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 122){
            imageuri =data.getData();
            im.setImageURI(imageuri);
        }
    }
}
