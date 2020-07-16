package com.example.tcc;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainCadastro extends AppCompatActivity {

    EditText txtname, txtemail, txtpass, txtnasc, txtcpf, txttel, txtcep, txtnum;
    Button btnsignup;
    TextView btnlogin;
    ProgressBar progressBar;
    RadioGroup txtsexo;

    FirebaseAuth fireAuth;
    DatabaseReference reff;

    Aluno aluno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cadastro);

        txtnum = findViewById(R.id.txtnum);
        txtcep = findViewById(R.id.txtcep);
        txttel = findViewById(R.id.txttel);
        txtcpf = findViewById(R.id.txtcpf);
        txtsexo = findViewById(R.id.txtgroup);
        txtnasc = findViewById(R.id.txtnascimento);
        txtname = findViewById(R.id.txtnome);
        txtemail = findViewById(R.id.txtemail);
        txtpass = findViewById(R.id.txtsenha);
        btnsignup = findViewById(R.id.btncad);
        btnlogin = findViewById(R.id.btnjatenho);
        progressBar = findViewById(R.id.progressBar2);

        fireAuth = FirebaseAuth.getInstance();
        aluno = new Aluno();
        reff = FirebaseDatabase.getInstance().getReference().child("Aluno");


        if (fireAuth.getCurrentUser() != null) {
           startActivity(new Intent(getApplicationContext(), ActivityPrincipal.class));
           finish();
        }

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtemail.getText().toString().trim();
                String pass = txtpass.getText().toString().trim();
                String nome = txtname.getText().toString().trim();
                String nasc = txtnasc.getText().toString().trim();
                String sexo = ((RadioButton)findViewById(txtsexo.getCheckedRadioButtonId())).getText().toString();
                String cpf = txtcpf.getText().toString().trim();
                String tel = txttel.getText().toString().trim();
                String cep = txtcep.getText().toString().trim();
                String num = txtnum.getText().toString().trim();


                aluno.setCpf(cpf);
                aluno.setNasc(nasc);
                aluno.setCep(cep);
                aluno.setEmail(email);
                aluno.setSenha(pass);
                aluno.setSexo(sexo);
                aluno.setTel(tel);
                aluno.setNum(num);
                aluno.setNome(nome);
                reff.push().setValue(aluno);

               progressBar.setVisibility(View.VISIBLE);

                if (TextUtils.isEmpty(email)) {
                    txtemail.setError("Email requerido.");
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    txtpass.setError("Senha requerido.");
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }

                if (pass.length() < 8) {
                    txtpass.setError("A senha deve possuir mais de 6 caracteres.");
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }

                fireAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainCadastro.this, "Usuário registrado", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), ActivityPrincipal.class));
                            finish();

                        } else {
                            Toast.makeText(MainCadastro.this, "Erro" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    public void fLogin(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}



