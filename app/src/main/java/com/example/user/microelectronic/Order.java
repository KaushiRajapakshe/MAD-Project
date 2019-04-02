package com.example.user.microelectronic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Order extends AppCompatActivity {
    TextView id1;
    TextView itemName;
    TextView description;
    TextView stock;
    TextView price;
    Button proceed;
    DBConnector db;
    EditText qty;
    String id2,name,des,st;
    Float pr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        db = new DBConnector(this);

        id1 = findViewById(R.id.id1);
        itemName = findViewById(R.id.itemName);
        description = findViewById(R.id.description);
        stock = findViewById(R.id.stock);
        price = findViewById(R.id.price);
        qty = findViewById(R.id.qty);

        Intent in = getIntent();
        id2 = in.getExtras().getString("id");
        name = in.getExtras().getString("name");
        des = in.getExtras().getString("description");
        st = in.getExtras().getString("stocklevel");
        pr = in.getExtras().getFloat("price");

        id1.setText(id2);
        itemName.setText(name);
        description.setText(des);
        stock.setText(st);
        price.setText(pr.toString());


        init();

    }
    public void init(){
        proceed = (Button) findViewById(R.id.proceed);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idm = id1.getText().toString();
                String inm = itemName.getText().toString();
                String qty1 = qty.getText().toString();
                String dem = description.getText().toString();
                Intent but = new Intent(Order.this, PlaceOrder.class);

                String st = stock.getText().toString();
                int s = Integer.parseInt(st);
                int q = Integer.parseInt(qty1);
                float tot = q * pr;
                boolean isInserted = db.insertData1(idm,inm,dem,qty.getText().toString(), String.valueOf(tot));
                if(qty1.matches("")){
                    Toast.makeText(Order.this, "Please enter quantity", Toast.LENGTH_LONG).show();
                }else{
                    if(q > s){
                        Toast.makeText(Order.this, "Cannot place order. ", Toast.LENGTH_LONG).show();
                    }else {
                        if (isInserted == true) {
                            OrderSuper o = new OrderSuper();
                            o.setItemName(itemName.getText().toString());
                            o.setItemNumber(id1.getText().toString());
                            o.setDiscription(description.getText().toString());
                            o.setQty(qty1);
                            but.putExtra("id1", id1.getText().toString());
                            but.putExtra("name1", itemName.getText().toString());
                            but.putExtra("description1", description.getText().toString());
                            but.putExtra("qty1", qty.getText().toString());
                            but.putExtra("stock", stock.getText().toString());
                            but.putExtra("price", pr);
                            but.putExtra("total", tot);

                            Toast.makeText(Order.this, "Data Inserted", Toast.LENGTH_LONG).show();
                            startActivity(but);
                        } else {
                            Toast.makeText(Order.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                }
        });

    }
}
