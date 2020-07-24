package com.example.mercadoesclavo.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.controller.ProductoController;
import com.example.mercadoesclavo.dao.FireBaseDao;
import com.example.mercadoesclavo.model.Productos;
import com.example.mercadoesclavo.util.ResultListener;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment implements PictureAdapter.PictureAdapterListener {



    public static final String USUARIOS = "usuarios";
    public static final String PRODUCTOS = "productos";
    private RecyclerView recyclerViewPictureList;
    private ImageButton botonFavDesdeDetail;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;


    FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_detail, container, false);

        Bundle bundle = getArguments();
        final Productos productos = (Productos) bundle.getSerializable(PRODUCTOS);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        final FireBaseDao fireBaseDao = new FireBaseDao();

        db = FirebaseFirestore.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);


        botonFavDesdeDetail = view.findViewById(R.id.fragmentDetailBotonFav);
        TextView nombreProducto = view.findViewById(R.id.detailFragmentNombreProductoTextView);
        TextView precioProducto = view.findViewById(R.id.TextViewPrecioFragmentDetalle);
        final TextView ubicacionProducto = view.findViewById(R.id.DetailFragmentTextViewLocation);
        final TextView descripcionProducto = view.findViewById(R.id.FragmentDetailDescriptionTexto);

        botonFavDesdeDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (verificaSesion() != null) {
                    if (productos.getEsFav() == false) {
                        productos.setEsFav(true);
                        fireBaseDao.agregarProductoFav(productos,verificaSesion());
                        botonFavDesdeDetail.setImageResource(R.drawable.ic_baseline_favorite_24);
                        Toast.makeText(getContext(), "Agregado a favoritos", Toast.LENGTH_SHORT).show();

                    }

                } else {
                        Toast.makeText(getContext(), "Debe iniciar sesión para guardar en favoritos", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });


        nombreProducto.setText(productos.getTitle());
        precioProducto.setText("$" + productos.getPrice().toString());
        ubicacionProducto.setText(productos.getAddress().getStateName() + ", " + productos.getAddress().getCityName());
        productos.setEsFav(false);

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
                        LinearLayoutManager linearLayout = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);
                        recyclerViewPictureList.setLayoutManager(linearLayout);
                        recyclerViewPictureList.setAdapter(pictureAdapter);


                    }
                });

            }

        });

        return view;


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
