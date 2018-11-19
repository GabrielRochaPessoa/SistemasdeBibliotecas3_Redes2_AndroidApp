package com.example.gabriel.sistemasdebibliotecas3.Activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.gabriel.sistemasdebibliotecas3.Entidades.Livros;
import com.example.gabriel.sistemasdebibliotecas3.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExcluirActivity extends AppCompatActivity {

    private ListView listLivros;
    private EditText barraPesquisa;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<Livros> listaLivros = new ArrayList<Livros>();
    private ArrayAdapter <Livros> arrayAdapterLivros;
    private Button btnPesquisar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exclui);
        botaoPesquisar();

    }
    private void inicializaComponentes(){
        barraPesquisa = (EditText) findViewById(R.id.barraPesquisa);
        listLivros = (ListView) findViewById(R.id.listLivros);
    }
    private void inicializaFirebase(){
        FirebaseApp.initializeApp(ExcluirActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
    private void botaoPesquisar(){
        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inicializaComponentes();
                inicializaFirebase();
                eventoEdit();
            }
        });
    }
    private void eventoEdit(){
        barraPesquisa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String palavra = barraPesquisa.getText().toString().trim();
                pesquisaPalavra(palavra);
            }
        });
    }
    private void pesquisaPalavra(String palavra){
        Query query;
        if(palavra.equals("")){
             query = databaseReference.child("Livros").orderByChild("nome");
        }else{
            query = databaseReference.child("Livros").orderByChild(palavra).startAt(palavra).endAt(palavra+"\uf8ff");
        }
            listaLivros.clear();
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                        Livros l = objSnapshot.getValue(Livros.class);
                        listaLivros.add(l);
                    }
                    arrayAdapterLivros = new ArrayAdapter<Livros>(ExcluirActivity.this,android.R.layout.simple_list_item_1,listaLivros);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
    }

    @Override
    protected void onResume() {
        super.onResume();
        pesquisaPalavra("");
    }
}
