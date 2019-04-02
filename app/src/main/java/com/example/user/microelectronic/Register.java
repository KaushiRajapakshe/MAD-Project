package com.example.user.microelectronic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText Username,Email,Address,Password,Mobile;


    public TextView textView15;
    DBConnector myDb;




    public void init(){
        textView15=(TextView) findViewById(R.id.textView15);
        textView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent log=new Intent(Register.this,Login.class);
                startActivity(log);
            }
        });


    }



    public Button button3;

    public void init1(){
        button3=(Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                boolean isInserted = myDb.insertData(Username.getText().toString(), Email.getText().toString(), Address.getText().toString(), Mobile.getText().toString(), Password.getText().toString());
                if (isInserted = true) {

                    Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                    Intent log = new Intent(Register.this, Login.class);
                    startActivity(log);
                } else {
                    Toast.makeText(Register.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                }

            }

        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDb=new DBConnector(this);

        Username = findViewById(R.id.Username);
        Email = findViewById(R.id.Username);
        Address = findViewById(R.id.Address);
        Mobile = findViewById(R.id.Mobile);
        Password = findViewById(R.id.Password);
        init();
        init1();
    }
}
