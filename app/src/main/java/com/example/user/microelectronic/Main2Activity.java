package com.example.user.microelectronic;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static int val = 0;
    DBConnector mydb;
    ViewPager viewPager;
    SearchView searchView;
    Button addButton;
    EditText itemNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
        mydb = new DBConnector(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager =(ViewPager) findViewById(R.id.viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

        itemNumber = (EditText) findViewById(R.id.itemNumber);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this,Setting.class));
            return true;
        } else if(id == R.id.action_add_cart){
            startActivity(new Intent(this,ShoppingCart.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            startActivity(new Intent(this, Main2Activity.class));
            return true;
        } else if (id == R.id.account) {
            Cursor res = mydb.check();
            if(res.getCount() == 0) {
                startActivity(new Intent(this, Login.class));
            }
            if(res.getCount() == 1) {
                ///startActivity(new Intent(this, Myaccount.class));
                startActivity(new Intent(this, UserProfile.class));
            }else{
                startActivity(new Intent(this, Login.class));
            }
            return true;
        } else if (id == R.id.order) {
            startActivity(new Intent(this, MyOrder.class));
            return true;
        } else if (id == R.id.cart) {
            startActivity(new Intent(this, ShoppingCart.class));
            return true;
        } else if (id == R.id.heart) {
            startActivity(new Intent(this, WishList.class));
            return true;
        } else if (id == R.id.about) {
            startActivity(new Intent(this, AboutUs.class));
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void init(){
        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent but = new Intent(Main2Activity.this, Order.class);
                String itemNum = itemNumber.getText().toString();
                if(itemNum.matches("")){
                    Toast.makeText(Main2Activity.this, "Please enter item number", Toast.LENGTH_LONG).show();
                }else{
                    Cursor res = mydb.getAllData(itemNum);
                    if(res.getCount() == 0) {
                        //message
                        showMessage("Error", "Nothing found");
                    }else {
                        while (res.moveToNext()) {
                            but.putExtra("id", itemNumber.getText().toString());
                            but.putExtra("name", res.getString(1));
                            but.putExtra("description", res.getString(2));
                            but.putExtra("stocklevel", res.getString(4));
                            but.putExtra("price", res.getFloat(3));
                            startActivity(but);
                        }

                    }
                }

            }
        });

    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}
