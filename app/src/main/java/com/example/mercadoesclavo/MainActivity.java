package com.example.mercadoesclavo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mercadoesclavo.controller.ProductoController;
import com.example.mercadoesclavo.model.Productos;
import com.example.mercadoesclavo.util.ResultListener;
import com.example.mercadoesclavo.view.AboutUs;
import com.example.mercadoesclavo.view.DetailActivity;
import com.example.mercadoesclavo.view.MercadoEsclavoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements MercadoEsclavoFragment.MercadoEsclavoFragmentListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbarMainActivity;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProductoController productoController = new ProductoController();
        productoController.getProductos(new ResultListener<Object>() {
            @Override
            public void onFinish(Object result) {

            }
        });

        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);

        toolbarMainActivity = findViewById(R.id.activityMainToolBar);
        setSupportActionBar(toolbarMainActivity);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {



                switch (item.getItemId()){
                    case R.id.menuAboutUs:
                        AboutUs aboutUs = new AboutUs();
                        pegarFragment(aboutUs);
                    break;
                }
                drawerLayout.closeDrawers();
                return true;
            }


        });


        MercadoEsclavoFragment mercadoEsclavoFragment = new MercadoEsclavoFragment();
        pegarFragment(mercadoEsclavoFragment);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    private void pegarFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activiyMainContenedorFragment, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClickProductosDesdeFragment(Productos productos) {

        Intent unIntent = new Intent(this, DetailActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("productos", productos);

        unIntent.putExtras(bundle);

        startActivity(unIntent);

    }
}
