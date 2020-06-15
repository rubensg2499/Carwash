package com.example.carwash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class insumos extends AppCompatActivity implements View.OnClickListener {
    private ListView listview;
    private ArrayList<String> insumos;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insumos);
        this.setTitle(R.string.titulo_insumo);
        View regresar = findViewById(R.id.btn_regresar_insumo);
        View agregar = findViewById(R.id.btn_agregar_insumo);
        regresar.setOnClickListener(this);
        agregar.setOnClickListener(this);
        listview = (ListView) findViewById(R.id.lista_insumos);
        insumos = new ArrayList<String>();
        insumos.add("Insumo1");
        insumos.add("Insumo2");
        insumos.add("Insumo3");
        insumos.add("Insumo4");
        insumos.add("Insumo5");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, insumos);
        listview.setAdapter(adapter);
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_regresar_insumo:
                intent = new Intent (v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.btn_agregar_insumo:
                intent = new Intent (v.getContext(), agregar_insumo.class);
                startActivityForResult(intent, 0);
                break;
        }

    }
}
