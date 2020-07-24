package com.example.mercadoesclavo.dao;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mercadoesclavo.MainActivity;
import com.example.mercadoesclavo.model.DatosUsuario;
import com.example.mercadoesclavo.model.Productos;
import com.example.mercadoesclavo.util.ResultListener;
import com.example.mercadoesclavo.view.DetailFragment;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FireBaseDao {


    public static final String PRODUCTOS = "productos";
    private static final String FAVORITOS = "favoritos";
    public static final String USUARIOS = "usuarios";
    public static final String FIREBASE = "firebase";
    private FirebaseAuth mAuth;
    private FirebaseUser usuarioLogueado;
    private FirebaseUser fireBaseUser;
    private GoogleSignInClient mGoogleSignInClient;
    private DetailFragment detailFragment;


    FirebaseFirestore db;


    public FireBaseDao() {

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        fireBaseUser = mAuth.getCurrentUser();


    }

    public void agregarUsuarioAfirebase(DatosUsuario datosUsuario) {
        db.collection(USUARIOS)
                .document()
                .set(datosUsuario);

    }


    public void agregarProductoFav(Productos productos, String idUsuario) {
                db.collection(FAVORITOS)
                .document(idUsuario)
                .collection(PRODUCTOS)
                .document(productos.getId())
                .set(productos);

    }

    public void obtenerProductosFav(String idUsuario, final ResultListener<List<Productos>> resultListener) {

              db.collection(FAVORITOS)
                .document(idUsuario)
                .collection(PRODUCTOS)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<Productos> productosfav = new ArrayList<>();
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Productos productos = document.toObject(Productos.class);
                        productosfav.add(productos);
                        resultListener.onFinish(productosfav);

                    }

                }
            }
        });


    }


}
