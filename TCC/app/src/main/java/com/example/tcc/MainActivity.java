package com.example.tcc;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText entradaEmail, entradaSenha;
    private Button buttomLogin, buttomCad;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarComponentes();

        buttomLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = entradaEmail.getText().toString();
                String senha = entradaSenha.getText().toString();

                Intent intent = new Intent(MainActivity.this, ActivityPrincipal.class);
                startActivity(intent);
                finish();
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

    private void inicializarComponentes() {
        entradaEmail = findViewById(R.id.editEmail);
        entradaSenha = findViewById(R.id.editSenha);
        buttomLogin = findViewById(R.id.butaoLog);
        buttomCad = findViewById(R.id.butaoCad);
    }
}
