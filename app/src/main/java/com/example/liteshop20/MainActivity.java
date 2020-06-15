package com.example.liteshop20;


import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public static MyAppDatabase myAppDatabase; //Για να καλούμε τη Βάση
    public static FragmentManager fragmentManager; //Για την διαχείρηση των Fragments


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        System.out.println();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.nav_admin)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        fragmentManager=getSupportFragmentManager();
        myAppDatabase= Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"userDB").allowMainThreadQueries().build();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu); Δέ θελω κουμπί ρυθμίσεων προς το παρόν.
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    //Μέθοδος για να προσθέσω ένα καλάθι στο Cart απ το Fragment με τα προϊόντα.
    public static void addCart(int id){
            int amount;
            try {
                amount = MainActivity.myAppDatabase.myDao().getProdCartAmount(id);
            }catch(Exception e){
                Log.e("addCart",""+e);
                amount=0;

            }
            if(amount>0) {
                ProdCart prodCart = new ProdCart();
                prodCart.setId(id);
                prodCart.setAmount((amount + 1));
                MainActivity.myAppDatabase.myDao().updateProdCart(prodCart);
            }
       else{
            ProdCart prodCart = new ProdCart();
            prodCart.setId(id);
            prodCart.setAmount(1);
            MainActivity.myAppDatabase.myDao().addProdCart(prodCart);
            Log.d("addCartNew","id= "+id);
        }

    }
    //Μέθοδος για να διαγράψω ένα αντικείμενο από το Cart.
    public static void delCart(int id){
            int amount=MainActivity.myAppDatabase.myDao().getProdCartAmount(id);
            if(amount>0) {
                ProdCart prodCart = new ProdCart();
                prodCart.setId(id);
                prodCart.setAmount(amount - 1);
                if((amount-1)!=0)
                    MainActivity.myAppDatabase.myDao().updateProdCart(prodCart);
                else
                    MainActivity.myAppDatabase.myDao().deleteProdCart(prodCart);
            }else{
                Log.d("delCart: ","Δεν γίνεται να αφαιρέσω κάτι");
        }

    }

}
