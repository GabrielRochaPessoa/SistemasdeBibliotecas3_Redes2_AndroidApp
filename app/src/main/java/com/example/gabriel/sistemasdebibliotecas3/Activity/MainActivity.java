package com.example.gabriel.sistemasdebibliotecas3.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gabriel.sistemasdebibliotecas3.R;

public class MainActivity extends AppCompatActivity {

    private Button btnAbrirActivityLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAbrirActivityLogin = (Button) findViewById(R.id.buttonEntrar);

        btnAbrirActivityLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAbrirTelaLogin = new Intent(MainActivity.this, log2Activity.class);
                startActivity(intentAbrirTelaLogin);
            }
        });
    }
}
