package com.example.user.microelectronic;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfile extends AppCompatActivity {

    TextView Username,Mobile,Address,Email,Password,userDelete;
    Button View,update,delete;
    DBConnector myDb;



    public void ViewData(){
        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor data=myDb.showData();
                if (data.getCount() == 0) {
                    display("Error","No Data Found");
                }
                StringBuffer buffer=new StringBuffer();
                while (data.moveToNext()){
                    buffer.append("Username  : "+data.getString(0)+"\n");
                    buffer.append("Email     : "+data.getString(1)+"\n");
                    buffer.append("Address   : "+data.getString(2)+"\n");
                    buffer.append("Mobile    : "+data.getString(3)+"\n");
                    buffer.append("Password : "+data.getString(4)+"\n");
                    buffer.append("\n");
                    display("All Logged Data",buffer.toString());

                }}
        });
    }



    public void display(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void updateData() {
        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int temp = Username.getText().toString().length();
                if (temp > 0) {
                    boolean update = myDb.updateData(Username.getText().toString(), Email.getText().toString(), Address.getText().toString(), Mobile.getText().toString(), Password.getText().toString());

                    if (update == true) {
                        Toast.makeText(UserProfile.this, "Successfully Updated", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(UserProfile.this, "Something went wrong", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(UserProfile.this, "Enter Old Username to update", Toast.LENGTH_LONG).show();

                }


            }
        });
    }



    public void DeleteData(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp=userDelete.getText().toString().length();
                if(temp>0) {
                    Integer deleteRaw = myDb.deleteData(userDelete.getText().toString());
                    if (deleteRaw > 0) {
                        Toast.makeText(UserProfile.this, "Successfully Deleted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(UserProfile.this, "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(UserProfile.this,"You must Enter an Username to Delete",Toast.LENGTH_LONG).show();

                }

            }
        });
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        View=findViewById(R.id.View);
        Username=findViewById(R.id.Username);
        Email=findViewById(R.id.Email);
        Address=findViewById(R.id.Address);
        Mobile=findViewById(R.id.Mobile);
        Password=findViewById(R.id.Password);
        update=findViewById(R.id.update);
        delete=findViewById(R.id.delete);
        userDelete=findViewById(R.id.userDelete);

        myDb=new DBConnector(this);
        ViewData();
        updateData();
        DeleteData();
    }
}
