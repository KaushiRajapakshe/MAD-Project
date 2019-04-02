package com.example.user.microelectronic;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PlaceOrder extends AppCompatActivity {

    Button proceed2;
    TextView id2;
    TextView itemName1;
    TextView description1;
    TextView price1;
    TextView qty;
    TextView total;
    DBConnector db;
    private String id3,name,des,st,q;
    private Float pr,tt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        proceed2 = (Button) findViewById(R.id.proceedm);
        db = new DBConnector(this);

        id2 = findViewById(R.id.id1);
        itemName1 = findViewById(R.id.itemName1);
        description1 = findViewById(R.id.description1);
        qty = findViewById(R.id.qty);
        price1 = findViewById(R.id.price1);
        total = findViewById(R.id.total);

        Intent in = getIntent();
        id3 = in.getExtras().getString("id1");
        name = in.getExtras().getString("name1");
        des = in.getExtras().getString("description1");
        st = in.getExtras().getString("qty1");
        q = in.getExtras().getString("stock");
        pr = in.getExtras().getFloat("price");
        tt = in.getExtras().getFloat("total");

        id2.setText(id3);
        itemName1.setText(name);
        description1.setText(des);
        qty.setText(st);
        price1.setText(pr.toString());
        total.setText(tt.toString());

        proceed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String m = id2.getText().toString();
                int s = Integer.parseInt(q) - Integer.parseInt(st);
                int mm = Integer.parseInt(m);
                boolean update = db.updateData1(mm, s);
                if (update == true) {
                    Toast.makeText(PlaceOrder.this, "Successfully Updated" + s +"" +mm, Toast.LENGTH_LONG).show();
                    Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paypal.com/lk/home"));
                    startActivity(browserIntent);
                } else {
                    Toast.makeText(PlaceOrder.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
