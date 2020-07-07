package com.example.carwash;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.carwash.tablas.Insumo;
import com.example.carwash.tablas.Usuario;
import com.example.carwash.utilidades.utilidades;

import java.util.ArrayList;

public class insumos extends AppCompatActivity implements View.OnClickListener {
    private ListView listview;
    private ArrayList<String> informacion;
    private ArrayList<Insumo> insumos;
    ArrayAdapter<String> adapter;
    ConexionSQLite con;

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
        informacion = new ArrayList<String>();

        con = new ConexionSQLite(getApplicationContext(), utilidades.NOMBRE_BASE_DE_DATOS,null,1);
        consultarListaInsumos();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, informacion);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                AlertDialog.Builder alerta = new AlertDialog.Builder(insumos.this);
                alerta.setMessage("Producto: "+insumos.get(position).getNombre()
                        +"\nCódigo: "+insumos.get(position).getCodigo()
                        +"\nPrecio U: "+insumos.get(position).getPrecio()
                        +"\nCantidad: "+insumos.get(position).getCantidad()
                        +"\nDescripción: "+insumos.get(position).getDescripcion())
                        .setCancelable(false)
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog ventana = alerta.create();
                ventana.setTitle("Insumo");
                ventana.show();
            }
        });
    }

    private void consultarListaInsumos() {
        SQLiteDatabase db = con.getReadableDatabase();
        Insumo i = null;
        insumos = new ArrayList<Insumo>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+utilidades.TABLA_INSUMO,null);
        while(cursor.moveToNext()){
            i = new Insumo();
            i.setCodigo(cursor.getString(0));
            i.setNombre(cursor.getString(1));
            i.setPrecio(Integer.parseInt(cursor.getString(2)));
            i.setCantidad(Integer.parseInt(cursor.getString(3)));
            i.setDescripcion(cursor.getString(4));
            insumos.add(i);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        informacion = new ArrayList<String>();
        for(int i=0;i<insumos.size();i++){
            informacion.add(insumos.get(i).getCodigo()+" - "+insumos.get(i).getNombre());
        }
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_agregar_insumo:
                intent = new Intent (v.getContext(), agregar_insumo.class);
                startActivityForResult(intent, 0);
                break;
        }

    }
}
