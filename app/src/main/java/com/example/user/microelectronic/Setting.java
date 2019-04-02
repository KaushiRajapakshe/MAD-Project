package com.example.user.microelectronic;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Setting extends AppCompatActivity {
    Button signOut;
    DBConnector db;
    Button accountSettings;
    Button addressBook;
    Button CheckNewVersion;
    Button ChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        signOut = (Button) findViewById(R.id.signOut);
        accountSettings = findViewById(R.id.accountSetting);
        addressBook = findViewById(R.id.addressBook);
        CheckNewVersion = findViewById(R.id.CheckNewVersion);
        ChangePassword = findViewById(R.id.changePassword);

        db = new DBConnector(this);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent but = new Intent(Setting.this, Main2Activity.class);
                int value = 1;
                String id = "0";
                boolean ischeck = db.change(value,id);
                if(ischeck == true) {
                    startActivity(but);
                }
            }
        });

        accountSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent but = new Intent(Setting.this, UserProfile.class);
                startActivity(but);
            }
        });


        addressBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent but = new Intent(Setting.this, AddressBook.class);
                startActivity(but);
            }
        });

        CheckNewVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/"));
                startActivity(browserIntent);
            }
        });

        ChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent but = new Intent(Setting.this, UserProfile.class);
                startActivity(but);
            }
        });
    }
}

