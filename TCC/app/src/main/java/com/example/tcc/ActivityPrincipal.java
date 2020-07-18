package com.example.tcc;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ActivityPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;

    private FirebaseAuth mAuth;
    DatabaseReference reff, reff2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MainActivity ma = new MainActivity();

        mAuth = FirebaseAuth.getInstance();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        String emaildouser = mAuth.getCurrentUser().getEmail();
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.emailaluno);
        navUsername.setText(emaildouser);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_checklist) {

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container_fragment, new MainFragment());
            fragmentTransaction.commit();

        } else if (id == R.id.nav_sobre) {

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, new FragmentSecond());
            fragmentTransaction.commit();

            reff = FirebaseDatabase.getInstance().getReference().child("Escola");
            reff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String TAG = "";

                    String cnpj = snapshot.child("cnpj").getValue().toString();
                    TextView tcnpj = findViewById(R.id.txtcnpj);
                    tcnpj.setText(cnpj);

                    String telefone = snapshot.child("telefone").getValue().toString();
                    TextView ttelefone = findViewById(R.id.txttelefone);
                    ttelefone.setText(telefone);

                    String email = snapshot.child("email").getValue().toString();
                    TextView temail = findViewById(R.id.txtemails);
                    temail.setText(email);

                    String endereco = snapshot.child("endereco").getValue().toString();
                    TextView tendereco = findViewById(R.id.txtendereco);
                    tendereco.setText(endereco);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        } else if (id == R.id.nav_meusdados) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, new FragmentThree());
            fragmentTransaction.commit();




        } else if (id == R.id.nav_seguranca) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, new FragmentFour());
            fragmentTransaction.commit();



        } else if (id == R.id.nav_sair) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void AlterarSenha(View view) {

        EditText txtsenha;
        txtsenha = findViewById(R.id.txtnova);

        String senha = txtsenha.getText().toString();

        if (TextUtils.isEmpty(senha)) {
            txtsenha.setError("Nova senha requerida");
        }

        if (senha.length() < 8) {
            txtsenha.setError("A senha deve conter mais de 8 digitos");
        }

            FirebaseUser user = mAuth.getCurrentUser();

        assert user != null;
        user.updatePassword(senha).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(ActivityPrincipal.this, "Senha alterada", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ActivityPrincipal.this, "Falhou" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }


}

