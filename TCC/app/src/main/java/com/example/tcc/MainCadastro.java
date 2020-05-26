package com.example.tcc;

import android.annotation.SuppressLint;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Date;

public class MainCadastro extends AppCompatActivity {

    private EditText nome;
    private EditText nasc;
    private RadioGroup sexo;
    private EditText cpf;
    private EditText tel;
    private EditText cep;
    private EditText email;
    private EditText senha;
    private Button btncad;
    private Button btnclear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cadastro);

        nome = (EditText) findViewById(R.id.txtnome);
        nasc = (EditText) findViewById(R.id.txtnascimento);
        sexo = (RadioGroup) findViewById(R.id.txtgroup);
        cpf = (EditText) findViewById(R.id.txtcpf);
        tel = (EditText) findViewById(R.id.txttel);
        cep = (EditText) findViewById(R.id.txtcep);
        email = (EditText) findViewById(R.id.txtemail);
        senha = (EditText) findViewById(R.id.txtsenha);
        btncad = (Button) findViewById(R.id.btncad);
        btnclear = (Button) findViewById(R.id.btnclear);

        btncad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome.setText("");
                nasc.getText().clear();

                }
            });
        }
    }
