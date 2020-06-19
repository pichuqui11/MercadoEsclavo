package com.example.mercadoesclavo.view;

import android.graphics.Picture;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.model.Pictures;
import com.example.mercadoesclavo.model.Productos;

import java.util.ArrayList;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolderPictures> {

    private Productos productos;
    private PictureAdapterListener pictureAdapterListener;

    public PictureAdapter(Productos productos, PictureAdapterListener pictureAdapterListener) {
        this.productos = productos;
        this.pictureAdapterListener = pictureAdapterListener;
    }


    @NonNull
    @Override
    public ViewHolderPictures onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.celda_picture, parent, false);

        return new ViewHolderPictures(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPictures holder, int position) {
        Pictures picture = this.productos.getPictures().get(position);
        holder.onBine(picture);
        
    }

    @Override
    public int getItemCount() {
        return productos.getPictures().size();
    }

    protected class ViewHolderPictures extends RecyclerView.ViewHolder{
        private ImageView ImageViewCeldaPicture;


        public ViewHolderPictures(@NonNull View itemView) {
            super(itemView);
            ImageViewCeldaPicture = itemView.findViewById(R.id.CeldaPicture);
            
            
        }

        public void onBine(Pictures picture) {
            Glide.with(ImageViewCeldaPicture.getContext())
                    .load(picture.getUrl())
                    .fitCenter()
                    .into(ImageViewCeldaPicture);
        }
    }

    public interface PictureAdapterListener {
    }

    }



