package com.example.tcc;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText entradaEmail, entradaSenha;
    private Button buttomLogin, buttomCad;


    public static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;
    public String lbEmailDoUsuarioLogado;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        inicializarComponentes();

        entradaEmail = (EditText) findViewById(R.id.editEmail);
        entradaSenha = (EditText) findViewById(R.id.editSenha);

        buttomLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        buttomCad.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainCadastro.class);
                startActivity(intent);
                finish();
            }
        });
        

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void updateUI(FirebaseUser user) {
        if (user != null) {
            lbEmailDoUsuarioLogado = user.getEmail();
        } else {
            lbEmailDoUsuarioLogado = "Nenhum Usuário Logado";
        }

    }

    public void login() {
        mAuth.signInWithEmailAndPassword(entradaEmail.getText().toString(), entradaSenha.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Intent intent = new Intent(MainActivity.this, ActivityPrincipal.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            updateUI(null);
                        }
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Falhou...", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

    private void inicializarComponentes() {
        entradaEmail = findViewById(R.id.editEmail);
        entradaSenha = findViewById(R.id.editSenha);
        buttomLogin = findViewById(R.id.butaoLog);
        buttomCad = findViewById(R.id.butaoCad);
    }

}


