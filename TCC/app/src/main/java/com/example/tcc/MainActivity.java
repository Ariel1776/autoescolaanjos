package com.example.tcc;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText txtemail,txtpass;
    Button btnsignin;
    TextView btnnew;
    ProgressBar progressBar;
    FirebaseAuth fireAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtemail = findViewById(R.id.editEmail);
        txtpass = findViewById(R.id.editSenha);
        btnsignin = findViewById(R.id.butaoLog);
        btnnew = findViewById(R.id.btnnovoaqui);
        progressBar = findViewById(R.id.progressBar);

        fireAuth = FirebaseAuth.getInstance();

        if (fireAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), ActivityPrincipal.class));
            finish();
        }

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtemail.getText().toString().trim();
                String pass = txtpass.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    txtemail.setError("Email requerido");
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }

                if(TextUtils.isEmpty(pass)) {
                    txtpass.setError("Senha requerida");
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }

                if(pass.length() < 8) {
                    txtpass.setError("Senha deve ter mais de 8 caracteres");
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fireAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), ActivityPrincipal.class));
                        } else {
                            Toast.makeText(MainActivity.this, "Erro" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }

                    }
                });

            }
        });
    }

    public void fCadastro(View view) {
        startActivity(new Intent(getApplicationContext(), MainCadastro.class));
        finish();
    }
}

