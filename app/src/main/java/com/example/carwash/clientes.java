package com.example.carwash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class clientes extends AppCompatActivity implements View.OnClickListener {
    private ListView listview;
    private ArrayList<String> clientes;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        this.setTitle(R.string.titulo_cliente);
        View regresar = findViewById(R.id.btn_regresar_cliente);
        View agregar = findViewById(R.id.btn_agregar_cliente);
        regresar.setOnClickListener(this);
        agregar.setOnClickListener(this);
        listview = (ListView) findViewById(R.id.lista_clientes);
        clientes = new ArrayList<String>();
        clientes.add("Cliente1");
        clientes.add("Cliente2");
        clientes.add("Cliente3");
        clientes.add("Cliente4");
        clientes.add("Cliente5");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clientes);
        listview.setAdapter(adapter);
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_regresar_cliente:
                intent = new Intent (v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.btn_agregar_cliente:
                intent = new Intent (v.getContext(), agregar_cliente.class);
                startActivityForResult(intent, 0);
                break;
        }

    }
}
