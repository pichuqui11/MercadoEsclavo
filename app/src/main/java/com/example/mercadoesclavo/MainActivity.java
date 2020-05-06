package com.example.mercadoesclavo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MercadoEsclavoFragment mercadoEsclavoFragment = new MercadoEsclavoFragment();
        pegarFragment(mercadoEsclavoFragment);

    }

    private void pegarFragment(MercadoEsclavoFragment mercadoEsclavoFragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activiyMainContenedorFragment, mercadoEsclavoFragment);
        fragmentTransaction.commit();
    }
}
