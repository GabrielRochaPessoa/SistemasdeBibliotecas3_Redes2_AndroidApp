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
import android.widget.TextView;
import android.widget.Toast;

import com.example.gabriel.sistemasdebibliotecas3.Database.ConfiguracaoFirebase;
import com.example.gabriel.sistemasdebibliotecas3.Entidades.Usuarios;
import com.example.gabriel.sistemasdebibliotecas3.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class NovaSenhaActivity extends AppCompatActivity {
    private EditText edtEmail;
    private Button btnMudarSenha;
    private FirebaseAuth autenticacao;
    private Usuarios usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log2);

        btnMudarSenha = (Button) findViewById(R.id.btnMs);
        autenticacao = FirebaseAuth.getInstance();


        btnMudarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NovaSenhaActivity.super.notify();

            }

            public void abrirTelaPrincipal() {
                Intent intentAbrirTelaPrincipal = new Intent(NovaSenhaActivity.this, PrincipalActivity.class);
                startActivity(intentAbrirTelaPrincipal);
            }

            public void reset(View view) {
                autenticacao
                        .sendPasswordResetEmail(edtEmail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(
                                            NovaSenhaActivity.this, "Senha Alterada com Sucesso", Toast.LENGTH_SHORT
                                    ).show();
                                } else {
                                    Toast.makeText(
                                            NovaSenhaActivity.this, "Houve um Problema com a Alteração de Senha, Por Favor, Tente Novamente", Toast.LENGTH_SHORT
                                    ).show();
                                }
                            }

                        });
            }
        })



    ;}
}
