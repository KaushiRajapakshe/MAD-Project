package com.example.user.microelectronic;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddressBook extends AppCompatActivity {
    Button add;
    ListView addressview;
    Button more;
    DBConnector db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book);

        add = findViewById(R.id.add);
        more = findViewById(R.id.MORE);
        addressview = findViewById(R.id.addressview);
        db = new DBConnector(this);

        ArrayList <String> list = new ArrayList<>();
        Cursor data = db.getAddressdata();

        if(data.getCount() == 0){
            Toast.makeText(AddressBook.this, "No Addresses to show", Toast.LENGTH_LONG).show();
        }else {
            while(data.moveToNext()){
                list.add("Address Id        : "+ data.getString(0));
                list.add("Name              : " + data.getString(1)+ "  " +data.getString(2));
                list.add("Address           : " + data.getString(3));
                list.add("Zip Code          : " + data.getString(4));
                list.add("Contact Number    : " + data.getString(5));
                list.add("\n");
                ListAdapter listA = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
                addressview.setAdapter(listA);
            }
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent but = new Intent(AddressBook.this, Address.class);
                startActivity(but);
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent but = new Intent(AddressBook.this, AddressNew.class);
                startActivity(but);
            }
        });
    }
}
