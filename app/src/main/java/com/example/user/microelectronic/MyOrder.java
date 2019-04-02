package com.example.user.microelectronic;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyOrder extends AppCompatActivity {
    DBConnector db;
    ListView orderview;
    Button more;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        db = new DBConnector(this);

        orderview = findViewById(R.id.orderview);
        more  = findViewById(R.id.more);

        ArrayList<String> list = new ArrayList<>();
        Cursor data = db.getOrderdata();

        if(data.getCount() == 0){
            Toast.makeText(MyOrder.this, "No Order to show", Toast.LENGTH_LONG).show();
        }else {
            OrderSuper o = new OrderSuper();
            String q = o.getQty();
            String d = o.getDiscription();
            String i = o.getItemName();
            String n = o.getItemNumber();

            while(data.moveToNext()){
                list.add("Order ID          : "+ data.getString(0));
                list.add("Item ID           : " + data.getString(1));
                list.add("Item Name         : " + data.getString(2));
                list.add("Item Description  : " + data.getString(3));
                list.add("Qty               : " + data.getString(4));
                list.add("Total             : " + data.getString(5));
                list.add("\n");
                ListAdapter listA = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
                orderview.setAdapter(listA);
                OrderSuper o1 = new OrderSuper(n,i,d,q);
            }
        }
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent but = new Intent(MyOrder.this, Main2Activity.class);
                startActivity(but);
            }
        });
    }
}
