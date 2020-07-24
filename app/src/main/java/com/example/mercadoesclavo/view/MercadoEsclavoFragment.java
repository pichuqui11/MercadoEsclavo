package com.example.mercadoesclavo.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mercadoesclavo.MainActivity;
import com.example.mercadoesclavo.controller.ProductoController;
import com.example.mercadoesclavo.dao.ProveedorDeProductos;
import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.model.Productos;
import com.example.mercadoesclavo.model.ProductosConteiner;
import com.example.mercadoesclavo.util.ResultListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MercadoEsclavoFragment extends Fragment implements ProductosAdapter.ProductosAdapterListener {

    private MercadoEsclavoFragmentListener mercadoEsclavoFragmentListener;
    private RecyclerView recyclerViewProductos;


    public MercadoEsclavoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mercadoEsclavoFragmentListener = (MercadoEsclavoFragmentListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_mercado_esclavo, container, false);

        recyclerViewProductos = view.findViewById(R.id.fragmentRecycleMercadoEsclavo);





        ProductoController productoController = new ProductoController();
        productoController.getProductos(new ResultListener<List<Productos>>() {
            @Override
            public void onFinish(List<Productos> result) {
                ProductosAdapter productosAdapter = new ProductosAdapter(result, MercadoEsclavoFragment.this);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(),2);
                recyclerViewProductos.setLayoutManager(gridLayoutManager);
                recyclerViewProductos.setAdapter(productosAdapter);

            }

             });


        return view;
    }

    @Override
    public void onClickProductos(Productos productos) {
        mercadoEsclavoFragmentListener.onClickProductosDesdeFragment(productos);

    }
    public interface MercadoEsclavoFragmentListener {
        public void onClickProductosDesdeFragment (Productos productos);
    }


}
