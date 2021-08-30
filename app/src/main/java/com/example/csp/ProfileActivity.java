package com.example.csp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private Toolbar toolbars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);

        toolbars = findViewById(R.id.toolbar);
        setSupportActionBar(toolbars);

        drawer = findViewById(R.id.drawer_layout);
        //navigation
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer,
                toolbars,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();


// when activity is started this fragment is opened immediately
        //also if device is rotated saved instance state wont be null
        if (savedInstanceState == null){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                , new MessageFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_message);}


    }
    //to select one item a time on navigation bar
    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
       switch (item.getItemId()){
           //opens fragment when item on the menu is clicked
           case R.id.nav_profile:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
               , new ProfileFragment()).commit();
               break;
           case R.id.nav_message:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                       , new MessageFragment()).commit();
               break;
           case R.id.nav_signoff:
               FirebaseAuth.getInstance().signOut();//logout
               startActivity(new Intent(getApplicationContext(), MainActivity.class));
               finish();
               break;

       }

       drawer.closeDrawer(GravityCompat.START);

        return true;  //item selected
    }

    @Override
    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        switch (item.getItemId()){
//            case R.id.nav_signoff:
//                FirebaseAuth.getInstance().signOut();//logout
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                finish();
//
//                return true;
//        }
//        return false;
//    }

    //to review

    //review logout on nav drawer
    public void logout(View view){
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}