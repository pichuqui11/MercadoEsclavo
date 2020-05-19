package com.example.mercadoesclavo.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mercadoesclavo.dao.ProveedorDeProductos;
import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.model.Productos;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MercadoEsclavoFragment extends Fragment implements ProductosAdapter.ProductosAdapterListener {

    private MercadoEsclavoFragmentListener mercadoEsclavoFragmentListener;
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
        View view = inflater.inflate(R.layout.fragment_mercado_esclavo, container, false);

        RecyclerView recyclerViewProductos = view.findViewById(R.id.fragmentRecycleMercadoEsclavo);

        List<Productos> productosList = ProveedorDeProductos.getProductos();
        ProductosAdapter productosAdapter = new ProductosAdapter(productosList, this);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(view.getContext(),2);

        recyclerViewProductos.setLayoutManager(linearLayoutManager);
        recyclerViewProductos.setAdapter(productosAdapter);

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
