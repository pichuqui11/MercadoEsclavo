package com.example.mercadoesclavo.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.model.Productos;
import com.example.mercadoesclavo.model.ProductosConteiner;

import java.text.Format;
import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolderProductos> {

    private List<Productos> productosList;
    private ProductosAdapterListener productosAdapterListener;

    public ProductosAdapter(List<Productos> productosList, ProductosAdapterListener listener) {
        this.productosList = productosList;
        this.productosAdapterListener = listener;
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

    public void setProductosList(List<Productos> productosList){
        this.productosList = productosList;
        notifyDataSetChanged();
    }

    protected class ViewHolderProductos extends RecyclerView.ViewHolder{

        private ImageView imageViewProductos;
        private TextView textViewNombreProductos;
        private TextView textViewPreciosProductos;
        private TextView textViewLocation;

        public ViewHolderProductos(@NonNull final View itemView) {
            super(itemView);
            imageViewProductos = itemView.findViewById(R.id.celdaImageViewProducto);
            textViewNombreProductos = itemView.findViewById(R.id.celdaTextViewNombreProducto);
            textViewPreciosProductos = itemView.findViewById(R.id.celdaTextViewPrecioProducto);
            textViewLocation = itemView.findViewById(R.id.CeldaTextViewLocation);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Productos productos = productosList.get(getAdapterPosition());
                    productosAdapterListener.onClickProductos(productos);
                }
            });


        }


        public void onBind(Productos productos) {
            Glide.with(textViewNombreProductos.getContext())
                    .load(productos.getThumbnail())
                    .fitCenter()
                    .into(imageViewProductos);


            textViewNombreProductos.setText(productos.getTitle());
            textViewPreciosProductos.setText("$ " + productos.getPrice().toString());
            textViewLocation.setText(productos.getAddress().getStateName()+ ", " + productos.getAddress().getCityName());
        }
    }

        public interface ProductosAdapterListener {
        public void onClickProductos(Productos productos);
        }

}
