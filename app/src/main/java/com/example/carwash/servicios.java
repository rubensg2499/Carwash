package com.example.carwash;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class servicios extends AppCompatActivity implements View.OnClickListener {
    private ListView listview;
    private ArrayList<String> servicios;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);
        this.setTitle(R.string.titulo_servicio);
        View regresar = findViewById(R.id.btn_regresar_servicio);
        View agregar = findViewById(R.id.btn_agregar_servicio);
        regresar.setOnClickListener(this);
        agregar.setOnClickListener(this);
        listview = (ListView) findViewById(R.id.lista_servicios);
        servicios = new ArrayList<String>();
        servicios.add("Servicio1");
        servicios.add("Servicio2");
        servicios.add("Servicio3");
        servicios.add("Servicio4");
        servicios.add("Servicio5");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, servicios);
        listview.setAdapter(adapter);


    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_regresar_servicio:
                intent = new Intent (v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.btn_agregar_servicio:
                intent = new Intent (v.getContext(), agregar_servicio.class);
                startActivityForResult(intent, 0);
                break;
        }

    }
}
