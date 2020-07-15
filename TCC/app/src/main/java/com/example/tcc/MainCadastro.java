package com.example.tcc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainCadastro extends AppCompatActivity {

    private FirebaseAuth mAuth;
    public String lbEmailDoUsuarioLogado;
    public static final String TAG = "EmailPassword";

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
    private Button btnvoltar;
    DatabaseReference reff;
    Cad cad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cadastro);

        mAuth = FirebaseAuth.getInstance();


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
        btnvoltar = (Button) findViewById(R.id.btnvoltar);
        cad = new Cad();
        reff = FirebaseDatabase.getInstance().getReference().child("Cad");

        btncad.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int cpfi=Integer.parseInt(txtcpf.getText().toString.trim());
                int cepp=Integer.parseInt(txtcep.getText().toString.trim());
                Long tell=Long.parseLong(txttel.getText().toString.trim());
                cad.setNome(txtnome.getText().toString.trim());
                cad.setSexo(txtgroup.getText().toString.trim());
                cad.setEmail(txtemail.getText().toString.trim());
                cad.setSenha(txtsenha.getText().toString.trim());
                cad.setNasc(nasc);
                cad.setCep(cepp);
                cad.setCpf(cpfi);
                cad.setTel(tell);


                reff.push().setValue(cad);
                Toast.makeText(datainsert.this, "Dados salvos com sucesso!",Toast.LENGTH_LONG);
            }
        }));


        /*
        btncad.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainCadastro.this, "Cadastrando...", Toast.LENGTH_SHORT).show();
                signUp();

            }
        });
        */
        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome.setText("");
                nasc.getText().clear();

            }
        });

        btnvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCadastro.this, MainActivity.class);
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

    public void signUp() {
        mAuth.createUserWithEmailAndPassword(email.getText().toString(), senha.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Intent intent = new Intent(MainCadastro.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            updateUI(null);
                        }
                    }
                });
    }
}


