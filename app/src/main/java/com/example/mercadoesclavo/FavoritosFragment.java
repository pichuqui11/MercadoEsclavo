package com.example.mercadoesclavo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.load.model.ImageVideoWrapper;
import com.example.mercadoesclavo.dao.FireBaseDao;
import com.example.mercadoesclavo.model.Productos;
import com.example.mercadoesclavo.util.ResultListener;
import com.example.mercadoesclavo.view.FavoritosAdapter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;


public class FavoritosFragment extends Fragment implements FavoritosAdapter.FavoritosAdapterListener {

    private FavoritosFragmentListener favoritosFragmentListener;
    private RecyclerView recyclerViewProductos;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;

    public FavoritosFragment() {
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.favoritosFragmentListener = (FavoritosFragmentListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_favoritos, container, false);
        recyclerViewProductos = view.findViewById(R.id.fragmentRecycleFavoritos);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);

        FireBaseDao fireBaseDao = new FireBaseDao();
        fireBaseDao.obtenerProductosFav(verificaSesion(), new ResultListener<List<Productos>>() {
            @Override
            public void onFinish(List<Productos> result) {
                FavoritosAdapter favoritosAdapter = new FavoritosAdapter(result, FavoritosFragment.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
                recyclerViewProductos.setLayoutManager(linearLayoutManager);
                recyclerViewProductos.setAdapter(favoritosAdapter);
            }
        });

        return view;
    }




    @Override
    public void onClick(Productos productos) {
        favoritosFragmentListener.onClickProductosDesdeFavoritos(productos);
    }

    public interface FavoritosFragmentListener {
        public void onClickProductosDesdeFavoritos (Productos productos);

    }

    public String verificaSesion() {

        String idDocumentoFireStore = null;
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            idDocumentoFireStore = currentUser.getUid();
        } else {
            if (account != null) {
                idDocumentoFireStore = account.getId();
            }


        }

        return idDocumentoFireStore;
    }



}