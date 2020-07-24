package com.example.mercadoesclavo.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.model.Productos;

import java.util.List;

public class FavoritosAdapter extends RecyclerView.Adapter<FavoritosAdapter.ViewHolderFavoritos> {

    private List<Productos> productosList;
    private FavoritosAdapterListener favoritosAdapterListener;


    public FavoritosAdapter(List<Productos> productosList, FavoritosAdapterListener favoritosAdapterListener) {
        this.productosList = productosList;
        this.favoritosAdapterListener = favoritosAdapterListener;
    }

    @NonNull
    @Override
    public ViewHolderFavoritos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.celda_producto, parent, false);

        return new ViewHolderFavoritos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFavoritos holder, int position) {
        Productos productos = this.productosList.get(position);
        holder.onBind(productos);
    }

    @Override
    public int getItemCount() {
        return productosList.size();
    }

    protected class ViewHolderFavoritos extends RecyclerView.ViewHolder{

        private ImageView imageViewProductos;
        private TextView textViewNombreProductos;
        private TextView textViewPreciosProductos;
        private TextView textViewLocation;
        private ImageButton buttonFav;

        public ViewHolderFavoritos(@NonNull View itemView) {
            super(itemView);

            imageViewProductos = itemView.findViewById(R.id.celdaImageViewProducto);
            textViewNombreProductos = itemView.findViewById(R.id.celdaTextViewNombreProducto);
            textViewPreciosProductos = itemView.findViewById(R.id.celdaTextViewPrecioProducto);
            textViewLocation = itemView.findViewById(R.id.CeldaTextViewLocation);
            buttonFav = itemView.findViewById(R.id.fragmentDetailBotonFav);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Productos productos = productosList.get(getAdapterPosition());
                    favoritosAdapterListener.onClick(productos);
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


    public interface FavoritosAdapterListener {
        public void onClick(Productos productos);
    }

}
