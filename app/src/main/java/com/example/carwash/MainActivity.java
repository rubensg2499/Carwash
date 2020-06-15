package com.example.carwash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View usuarios = findViewById(R.id.btn_usuarios);
        View trabajadores = findViewById(R.id.btn_trabajadores);
        View clientes = findViewById(R.id.btn_clientes);
        View insumos = findViewById(R.id.btn_insumos);
        View servicios = findViewById(R.id.btn_servicios);
        View info = findViewById(R.id.btn_about);
        View salir = findViewById(R.id.btn_salir);

        usuarios.setOnClickListener(this);
        trabajadores.setOnClickListener(this);
        clientes.setOnClickListener(this);
        insumos.setOnClickListener(this);
        servicios.setOnClickListener(this);
        info.setOnClickListener(this);
        salir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_usuarios:
                intent = new Intent (v.getContext(), usuarios.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.btn_trabajadores:
                intent = new Intent (v.getContext(), trabajadores.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.btn_clientes:
                intent = new Intent (v.getContext(), clientes.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.btn_insumos:
                intent = new Intent (v.getContext(), insumos.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.btn_servicios:
                intent = new Intent (v.getContext(), servicios.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.btn_about:
                intent = new Intent (v.getContext(), info.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.btn_salir:
                intent = new Intent (Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }
}
