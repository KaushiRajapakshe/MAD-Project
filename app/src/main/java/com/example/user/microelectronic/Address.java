package com.example.user.microelectronic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Address extends AppCompatActivity {
    Button save;
    DBConnector db;
    EditText FName1,LName1,Address1,Zip1,ContactNo1;
    private String f,l,a,z,c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        db = new DBConnector(this);
        FName1 = findViewById(R.id.FName);
        LName1 = findViewById(R.id.LName);
        Address1 = findViewById(R.id.Address);
        Zip1 = findViewById(R.id.Zip);
        ContactNo1 = findViewById(R.id.ContactNo);

        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f = FName1.getText().toString();
                l = LName1.getText().toString();
                a = Address1.getText().toString();
                z = Zip1.getText().toString();
                c = ContactNo1.getText().toString();
                int temp = ContactNo1.getText().toString().length();
                if(f.matches("") || l.matches("") || a.matches("") || c.isEmpty()){
                    Toast.makeText(Address.this, "Please fill the textbox", Toast.LENGTH_LONG).show();
                }else if(temp == 10){
                    Intent but = new Intent(Address.this, AddressBook.class);
                    boolean isInserted = db.insertDataAdd(f, l, a, z, c);

                    if (isInserted == true) {
                        Toast.makeText(Address.this, "Data inserted", Toast.LENGTH_LONG).show();
                        startActivity(but);
                    }else{
                        Toast.makeText(Address.this, "Data not inserted", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(Address.this, "Enter Valid Contact Number", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
