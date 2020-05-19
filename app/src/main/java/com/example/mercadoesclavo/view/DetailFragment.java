package com.example.mercadoesclavo.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.model.Productos;


/**
 * A simple {@link Fragment} subclass.
 */
public class  DetailFragment extends Fragment {

    public static final String PRODUCTOS = "productos";

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        Bundle bundle = getArguments();
        Productos productos = (Productos) bundle.getSerializable(PRODUCTOS);

        ImageView imagenProducto = view.findViewById(R.id.detailFragmentImageView);
        TextView nombreProducto = view.findViewById(R.id.detailFragmentNombreProductoTextView);

        imagenProducto.setImageResource(productos.getImagen());
        nombreProducto.setText(productos.getNombre());

        return view;

    }
}
