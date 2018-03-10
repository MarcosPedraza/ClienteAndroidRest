package com.mareinc.marcospedraza.clienteandroidrest;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "Activity_nav_drawer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setFragment(1);

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
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_users_list) {
            // Handle the camera action
            setFragment(1);
            //Toast.makeText(this, "lista de usuarios", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_make_pay) {
            setFragment(4);

        } else if (id == R.id.nav_mod_user) {
            setFragment(2);
        } else if (id == R.id.nav_delete_user) {
            setFragment(3);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void setFragment(int i)
    {
        android.support.v4.app.FragmentManager fragmentManager;
        android.support.v4.app.FragmentTransaction fragmentTransaction;

        switch (i)
        {
            case 1:
                fragmentManager  = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                UserListFragment userListFragment = new UserListFragment();
                fragmentTransaction.replace(R.id.main_container,userListFragment);
                fragmentTransaction.commit();
                break;
            case 2:
                fragmentManager  = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                InsertUserFragment insertUserFragment = new InsertUserFragment();
                fragmentTransaction.replace(R.id.main_container,insertUserFragment);
                fragmentTransaction.commit();
                break;

            case 3:
                fragmentManager  = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                UserDeleteFragment userDeleteFragment  = new UserDeleteFragment();
                fragmentTransaction.replace(R.id.main_container,userDeleteFragment);
                fragmentTransaction.commit();
                break;

            case 4:
                fragmentManager  = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                paymentFragment paymentFragment  = new paymentFragment();
                fragmentTransaction.replace(R.id.main_container,paymentFragment);
                fragmentTransaction.commit();
                break;

        }


    }
}
