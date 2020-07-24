package com.example.mercadoesclavo.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mercadoesclavo.CreacionDePerfil;
import com.example.mercadoesclavo.MainActivity;
import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.dao.FireBaseDao;
import com.example.mercadoesclavo.model.DatosUsuario;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String GMAIL = "gmail";
    public static final String USUARIOS = "usuarios";
    public static final String ACCOUNT = "account";
    private static final int RC_SIGN_IN = 1;
    private Button buttonIniciarSesion;
    private SignInButton signInButton;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private TextView textViewEmail;
    private TextView textViewPassword;
    private FirebaseUser usuarioLogueado;
    private FireBaseDao fireBaseDao = new FireBaseDao();
    private String idDocumentoFireStore;
    private TextView nombreDeUsuarioVistaPeril;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInButton = findViewById(R.id.sign_in_button);
        buttonIniciarSesion = findViewById(R.id.botonIniciarSesion);
        textViewEmail = findViewById(R.id.usuarioLogin);
        textViewPassword = findViewById(R.id.passwordLogin);




        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .requestEmail()
                .build();

        mAuth = FirebaseAuth.getInstance();

        usuarioLogueado = mAuth.getCurrentUser();
        // Access a Cloud Firestore instance from your Activity
        db = FirebaseFirestore.getInstance();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Set the dimensions of the sign-in button.
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setOnClickListener(this);
        buttonIniciarSesion.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUIFirebase(currentUser);


    }




    private void loguearUsuarioFireBase (String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("LOGUEO OK FIREBASE", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUIFirebase(user);
                            nombreDeUsuarioVistaPeril.setText(user.getDisplayName());


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("LOGUEO ERROR FIREBASE", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                            updateUIFirebase(null);
                            // ...
                        }

                        // ...
                    }
                });
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.botonIniciarSesion:
                loguearUsuarioFireBase(textViewEmail.getText().toString(),textViewPassword.getText().toString());
                break;
            case R.id.textViewSinRegistro:
                Intent intent = new Intent(this,CreacionDePerfil.class);
                startActivity(intent);
        }

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            System.out.println("Hubo Login correcto");
            Log.i("GOOGLE", "Login Correcto");
            Toast.makeText(this, "Se ha logeado correctamente",Toast.LENGTH_SHORT).show();
            // Signed in successfully, show authenticated UI.
            updateUI(account);
            String email = account.getEmail();
            String nombre = account.getDisplayName();
            String apellido = account.getFamilyName();
            String id = account.getId();
            fireBaseDao.agregarUsuarioAfirebase(new DatosUsuario(email,nombre,apellido,GMAIL,id));
          // nombreDeUsuarioVistaPeril.setText(nombre);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            System.out.println("hubo un error");
            Log.w("GOOGLE", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }

    }

    public void updateUI(GoogleSignInAccount account) {
        if (account != null) {
            Intent intent = new Intent(this, MainActivity.class);
            Bundle bundle = new Bundle();
            startActivity(intent);
        }
    }

    public void updateUIFirebase(FirebaseUser currentUser) {
        if (currentUser != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }




}





