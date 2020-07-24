package com.example.mercadoesclavo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mercadoesclavo.dao.FireBaseDao;
import com.example.mercadoesclavo.model.Productos;
import com.example.mercadoesclavo.view.AboutUs;
import com.example.mercadoesclavo.view.DetailActivity;
import com.example.mercadoesclavo.view.FavoritosAdapter;
import com.example.mercadoesclavo.view.LoginActivity;
import com.example.mercadoesclavo.view.MercadoEsclavoFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements MercadoEsclavoFragment.MercadoEsclavoFragmentListener, FavoritosFragment.FavoritosFragmentListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbarMainActivity;

    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInAccount mGoogleSignInAccount;
    private FirebaseAuth mAuth;
    private FirebaseUser usuarioFireBaseLogueado;
    private TextView nombreDeUsuarioVistaPeril;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .requestEmail()
                .build();


        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);
        nombreDeUsuarioVistaPeril = findViewById(R.id.navigationViewNombreUsuario);

        mAuth = FirebaseAuth.getInstance();
        usuarioFireBaseLogueado = mAuth.getCurrentUser();


        toolbarMainActivity = findViewById(R.id.activityMainToolBar);
        setSupportActionBar(toolbarMainActivity);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setLogo(R.drawable.logo_mercado_esclavo_mini);


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);





        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()){
                    case R.id.menuFavoritoss:
                        FavoritosFragment favoritosFragment = new FavoritosFragment();
                        pegarFragment(favoritosFragment);
                        break;
                    case R.id.menuAboutUs:
                        AboutUs aboutUs = new AboutUs();
                        pegarFragment(aboutUs);
                    break;
                    case R.id.menuCerrarSesión:
                        switch (verificaSesiónInicada()){
                            case "GMAIL":
                                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(MainActivity.this);
                                signOut();
                                Toast.makeText(MainActivity.this,"Sesión Cerrada",Toast.LENGTH_LONG).show();
                                break;
                            case "FIREBASE":
                                mAuth.signOut();
                                Toast.makeText(MainActivity.this,"Sesión Cerrada",Toast.LENGTH_LONG).show();
                                break;
                            case "NO":
                                Toast.makeText(MainActivity.this,"No hay sesión inciada",Toast.LENGTH_LONG).show();
                        }

                }
                drawerLayout.closeDrawers();
                return true;
            }


        });




        MercadoEsclavoFragment mercadoEsclavoFragment = new MercadoEsclavoFragment();
        pegarFragment(mercadoEsclavoFragment);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    private void pegarFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activiyMainContenedorFragment, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClickProductosDesdeFragment(Productos productos) {

        Intent unIntent = new Intent(this, DetailActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("productos", productos);

        unIntent.putExtras(bundle);

        startActivity(unIntent);

    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }

    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }


    @Override
    public void onClickProductosDesdeFavoritos(Productos productos) {

        Intent unIntent = new Intent(this, DetailActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("productos", productos);

        unIntent.putExtras(bundle);

        startActivity(unIntent);

    }

    public String verificaSesiónInicada () {
        String sesionIniciada = null;
        if (usuarioFireBaseLogueado != null) {
            sesionIniciada = "FIREBASE";
        } else {
            if (mGoogleSignInClient != null) {
                sesionIniciada = "GMAIL";
            } else {
                sesionIniciada = "NO";
            }
        }return sesionIniciada;
    }
}
