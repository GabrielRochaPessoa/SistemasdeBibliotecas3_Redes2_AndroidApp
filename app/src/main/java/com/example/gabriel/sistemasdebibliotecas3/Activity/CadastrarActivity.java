package com.example.gabriel.sistemasdebibliotecas3.Activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gabriel.sistemasdebibliotecas3.Entidades.Livros;
import com.example.gabriel.sistemasdebibliotecas3.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class CadastrarActivity extends AppCompatActivity {

    private EditText edtNomeLivro;
    private EditText edtEditora;
    private EditText edtAnoLivro;
    private EditText edtEdicao;
    private EditText edtQuantidade;
    private EditText edtCreditos;
    private Button btnCadastrar;
    private Livros livros;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar3);
        databaseReference = FirebaseDatabase.getInstance().getReference("path");
        edtNomeLivro = (EditText)findViewById(R.id.edtNomeLivro);
        edtEditora = (EditText)findViewById(R.id.edtEditora);
        edtAnoLivro = (EditText)findViewById(R.id.edtAnoLivro);
        edtEdicao = (EditText)findViewById(R.id.edtEdicao);
        edtQuantidade = (EditText)findViewById(R.id.edtQtdd);
        edtCreditos = (EditText)findViewById(R.id.edtCreditos);
        btnCadastrar = (Button)findViewById(R.id.btnCadastro);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Livros livros = new Livros();
                livros.setId(UUID.randomUUID().toString());
                livros.setNome(edtNomeLivro.getText().toString());
                livros.setEditora(edtEditora.getText().toString());
                livros.setAno(edtAnoLivro.getText().toString());
                livros.setEdicao(edtEdicao.getText().toString());
                livros.setQuantidade(Integer.parseInt(edtQuantidade.getText().toString()));
                livros.setCreditos(Integer.parseInt(edtCreditos.getText().toString()));
                inicializaFirebase();
                databaseReference.child(livros.getNome()).setValue(livros).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(CadastrarActivity.this, "Livro Cadastrado com Sucesso! ", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(CadastrarActivity.this, "Livro Não Cadastrado, por favor tente novamente ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                limparCampos();

            }

            private void limparCampos(){
                edtNomeLivro.setText("");
                edtCreditos.setText("");
                edtQuantidade.setText("");
                edtEdicao.setText("");
                edtEditora.setText("");
                edtNomeLivro.setText("");
            }

            private void inicializaFirebase(){
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference=firebaseDatabase.getReference();
            }

        });
    }
}
