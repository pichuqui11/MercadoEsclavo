package com.example.mercadoesclavo.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.model.Productos;

import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolderProductos> {

    private List<Productos> productosList;

    public ProductosAdapter(List<Productos> productosList) {
        this.productosList = productosList;
    }

    @NonNull
    @Override
    public ViewHolderProductos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.celda_producto, parent, false);

        return new ViewHolderProductos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProductos holder, int position) {
        Productos productos = this.productosList.get(position);
        holder.onBind(productos);
    }

    @Override
    public int getItemCount() {
        return productosList.size();
    }

    protected class ViewHolderProductos extends RecyclerView.ViewHolder{

        private ImageView imageViewProductos;
        private TextView textViewNombreProductos;
        private TextView textViewPreciosProductos;

        public ViewHolderProductos(@NonNull View itemView) {
            super(itemView);
            imageViewProductos = itemView.findViewById(R.id.celdaImageViewProducto);
            textViewNombreProductos = itemView.findViewById(R.id.celdaTextViewNombreProducto);
            textViewPreciosProductos = itemView.findViewById(R.id.celdaTextViewPrecioProducto);
        }


        public void onBind(Productos productos) {
            imageViewProductos.setImageResource(productos.getImagen());
            textViewNombreProductos.setText(productos.getNombre());
            textViewPreciosProductos.setText("$ " + productos.getPrecio().toString());
        }
    }
}
