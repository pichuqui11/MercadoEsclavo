package com.example.mercadoesclavo.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.controller.ProductoController;
import com.example.mercadoesclavo.model.Productos;
import com.example.mercadoesclavo.util.ResultListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class  DetailFragment extends Fragment implements PictureAdapter.PictureAdapterListener {

    public static final String PRODUCTOS = "productos";
    private RecyclerView recyclerViewPictureList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_detail, container, false);

        Bundle bundle = getArguments();
        final Productos productos = (Productos) bundle.getSerializable(PRODUCTOS);



        //ImageView imagenProducto = view.findViewById(R.id.detailFragmentImageView);
        TextView nombreProducto = view.findViewById(R.id.detailFragmentNombreProductoTextView);
        TextView precioProducto = view.findViewById(R.id.TextViewPrecioFragmentDetalle);
        final TextView ubicacionProducto = view.findViewById(R.id.DetailFragmentTextViewLocation);
        final TextView descripcionProducto = view.findViewById(R.id.FragmentDetailDescriptionTexto);

        //imagenProducto.setImageResource(productos.getImagen());
        nombreProducto.setText(productos.getTitle());
        precioProducto.setText("$"+productos.getPrice().toString());
        ubicacionProducto.setText(productos.getAddress().getStateName() +", "+ productos.getAddress().getCityName());

        ProductoController productoController = new ProductoController();
        productoController.getDescriptionsPorId(productos.getId(), new ResultListener<ArrayList<Productos>>() {
            @Override
            public void onFinish(ArrayList<Productos> result) {
                ArrayList<Productos> descripcion = result;
                descripcionProducto.setText(descripcion.get(0).getPlainText());

        recyclerViewPictureList = view.findViewById(R.id.FragmentDetailRecycle);

        ProductoController productoControllerPorId = new ProductoController();
        productoControllerPorId.getProductosPorId(productos.getId(), new ResultListener<Productos>() {
            @Override
            public void onFinish(Productos result) {
                PictureAdapter pictureAdapter = new PictureAdapter(result, DetailFragment.this);
                LinearLayoutManager linearLayout = new LinearLayoutManager(view.getContext(),RecyclerView.HORIZONTAL, false);
                recyclerViewPictureList.setLayoutManager(linearLayout);
                recyclerViewPictureList.setAdapter(pictureAdapter);


            }
        });

            }

        });

        return view;





    }
}
