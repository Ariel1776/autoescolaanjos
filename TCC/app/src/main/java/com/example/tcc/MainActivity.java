package com.example.tcc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

                Toast.makeText(getApplicationContext(),"Ola" + email +  "Você digitou a senha" + senha,Toast.LENGTH_SHORT).show();
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
