package com.example.mercadoesclavo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mercadoesclavo.dao.FireBaseDao;
import com.example.mercadoesclavo.model.DatosUsuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreacionDePerfil extends AppCompatActivity {

    public static final String USUARIOS = "usuarios";
    public static final String FIREBASE = "firebase";
    private FirebaseAuth mAuth;
    private FirebaseUser usuarioLogueado;
    private EditText editTextMail;
    private EditText editTextPassword;
    private Button buttonCrearPerfil;
    private EditText editTextNombre;
    private EditText editTextApellido;
    private FireBaseDao fireBaseDao = new FireBaseDao();


    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_de_perfil);


        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);

        int ancho = medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;

        getWindow().setLayout((int) (ancho * 0.95), (int) (alto * 0.95));

        mAuth = FirebaseAuth.getInstance();
        usuarioLogueado = mAuth.getCurrentUser();
        // Access a Cloud Firestore instance from your Activity
        db = FirebaseFirestore.getInstance();


        editTextMail = findViewById(R.id.editTextMailCreacionPerfil);
        editTextPassword = findViewById(R.id.editTextPasswordCreacionPerfil);
        buttonCrearPerfil = findViewById(R.id.botonCrearPerfil);
        editTextNombre = findViewById(R.id.editTextNombreCreacionPerfil);
        editTextApellido = findViewById(R.id.editTextApellidoCreacionPerfil);

        buttonCrearPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextMail.getText().toString();
                String password = editTextPassword.getText().toString();
                crearUsuario(email, password);

            }
        });


    }

    private void crearUsuario(final String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("REGISTRO OK FIREBASE", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUIFirebase(user);
                            String nombre = editTextNombre.getText().toString();
                            String apellido = editTextApellido.getText().toString();
                            String id = mAuth.getUid();
                            fireBaseDao.agregarUsuarioAfirebase(new DatosUsuario(email,nombre,apellido,FIREBASE,id));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("ERROR EN REG FIREBASE", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CreacionDePerfil.this, task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                            updateUIFirebase(null);
                        }

                        // ...
                    }
                });
    }



    public void updateUIFirebase(FirebaseUser currentUser) {
        if (currentUser != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }









}