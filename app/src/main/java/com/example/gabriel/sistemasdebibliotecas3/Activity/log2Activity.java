package com.example.gabriel.sistemasdebibliotecas3.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gabriel.sistemasdebibliotecas3.Database.ConfiguracaoFirebase;
import com.example.gabriel.sistemasdebibliotecas3.Entidades.Usuarios;
import com.example.gabriel.sistemasdebibliotecas3.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class log2Activity extends AppCompatActivity {
    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnLogar;
    private Button btnNovaSenha;
    private FirebaseAuth autenticacao;
    private Usuarios usuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log2);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnLogar = (Button) findViewById(R.id.btnLogar);
        btnNovaSenha = (Button) findViewById(R.id.btnNovaSenha);



        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtEmail.getText().toString().equals("") && !edtSenha.getText().toString().equals("")) {
                    usuarios = new Usuarios();
                    usuarios.setEmail(edtEmail.getText().toString());
                    usuarios.setSenha(edtSenha.getText().toString());
                    validaLogin();
                } else {
                    Toast.makeText(log2Activity.this, "Preencha os campos de email e senha", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

            private void validaLogin() {
                autenticacao = ConfiguracaoFirebase.getReferenciaAutenticacao();
                autenticacao.signInWithEmailAndPassword(usuarios.getEmail(), usuarios.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            abrirTelaPrincipal();
                            Toast.makeText(log2Activity.this, "Login Efetuado com Sucesso ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            public void abrirTelaPrincipal() {
                Intent intentAbrirTelaPrincipal = new Intent(log2Activity.this, PrincipalActivity.class);
                startActivity(intentAbrirTelaPrincipal);
            }


    }





