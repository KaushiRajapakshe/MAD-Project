package com.example.user.microelectronic;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateAddress extends AppCompatActivity {
    Button save;
    DBConnector db;
    EditText FName1,LName1,Address1,Zip1,ContactNo1;
    private String id2;
    private String f,l,a,z,c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_address);
        db = new DBConnector(this);

        FName1 = findViewById(R.id.FName);
        LName1 = findViewById(R.id.LName);
        Address1 = findViewById(R.id.Address);
        Zip1 = findViewById(R.id.Zip);
        ContactNo1 = findViewById(R.id.ContactNo);

        Intent in = getIntent();
        id2 = in.getExtras().getString("addID");

        save = findViewById(R.id.save1);
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
                    Toast.makeText(UpdateAddress.this, "Please fill the textbox", Toast.LENGTH_LONG).show();
                }else if(temp == 10){
                    Intent but = new Intent(UpdateAddress.this, AddressBook.class);
                    int i = Integer.parseInt(id2);
                    /*boolean up = db.updateADD(i,f,l,a,z,c);

                    if (up == true) {
                        Toast.makeText(UpdateAddress.this, "Data inserted", Toast.LENGTH_LONG).show();
                        startActivity(but);
                    }else{
                        Toast.makeText(UpdateAddress.this, "Data not inserted", Toast.LENGTH_LONG).show();
                    }*/
                    Toast.makeText(UpdateAddress.this, "Data not inserted"+i, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(UpdateAddress.this, "Enter Valid Contact Number", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
