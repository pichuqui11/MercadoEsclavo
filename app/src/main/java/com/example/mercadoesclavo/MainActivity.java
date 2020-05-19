package com.example.mercadoesclavo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.mercadoesclavo.model.Productos;
import com.example.mercadoesclavo.view.DetailActivity;
import com.example.mercadoesclavo.view.MercadoEsclavoFragment;

public class MainActivity extends AppCompatActivity implements MercadoEsclavoFragment.MercadoEsclavoFragmentListener {

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

    @Override
    public void onClickProductosDesdeFragment(Productos productos) {

        Intent unIntent = new Intent(this, DetailActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("productos", productos);

        unIntent.putExtras(bundle);

        startActivity(unIntent);

    }
}
