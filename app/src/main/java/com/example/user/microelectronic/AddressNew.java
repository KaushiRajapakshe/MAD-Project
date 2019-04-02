package com.example.user.microelectronic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddressNew extends AppCompatActivity {

    EditText id;
    DBConnector db;
    Button update;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_new);

        db = new DBConnector(this);
        id = findViewById(R.id.idnew);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int temp=id.getText().toString().length();
                if(temp>0) {
                    String addID = id.getText().toString();
                    Integer deleteRaw = db.deleteAddress(Integer.parseInt(addID));
                    if (deleteRaw > 0) {
                        Toast.makeText(AddressNew.this, "Successfully Deleted", Toast.LENGTH_LONG).show();
                        Intent but = new Intent(AddressNew.this, AddressBook.class);
                        startActivity(but);
                    } else {
                        Toast.makeText(AddressNew.this, "You must Enter correct address id", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(AddressNew.this,"Please enter a Address ID",Toast.LENGTH_LONG).show();

                }
            }
        });


    }
}
