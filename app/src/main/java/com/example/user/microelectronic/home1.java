package com.example.user.microelectronic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home1 extends AppCompatActivity {


    public Button button5;

    public void init(){
        button5=(Button)findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent log=new Intent(home1.this,UserProfile.class);
                startActivity(log);
            }
        });


    }












    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);
        init();
    }
}

