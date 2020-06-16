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

import com.example.carwash.tablas.Trabajador;
import com.example.carwash.tablas.Usuario;
import com.example.carwash.utilidades.utilidades;

import java.util.ArrayList;

public class trabajadores extends AppCompatActivity implements View.OnClickListener {
    private ListView listview;
    private ArrayList<String> informacion;
    private ArrayList<Trabajador> trabajadores;
    ArrayAdapter<String> adapter;
    ConexionSQLite con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(R.string.titulo_trabajador);
        setContentView(R.layout.activity_trabajadores);
        View regresar = findViewById(R.id.btn_regresar_trabajador);
        View agregar = findViewById(R.id.btn_agregar_trabajador);
        regresar.setOnClickListener(this);
        agregar.setOnClickListener(this);
        listview = (ListView) findViewById(R.id.lista_trabajadores);
        informacion = new ArrayList<String>();

        con = new ConexionSQLite(getApplicationContext(), utilidades.NOMBRE_BASE_DE_DATOS,null,1);
        consultarListaTrabajadores();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, informacion);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                AlertDialog.Builder alerta = new AlertDialog.Builder(trabajadores.this);
                alerta.setMessage("Código: "+trabajadores.get(position).getCodigo_trabajador()
                        +"\nNombre: "+trabajadores.get(position).getNombre() + " " +trabajadores.get(position).getApe_pat()+ " " + trabajadores.get(position).getApe_mat()
                        +"\nTeléfono: "+trabajadores.get(position).getTelefono()
                        +"\nFecha contratación: "+trabajadores.get(position).getFecha())
                        .setCancelable(false)
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog ventana = alerta.create();
                ventana.setTitle("Trabajador");
                ventana.show();
            }
        });
    }

    private void consultarListaTrabajadores() {
        SQLiteDatabase db = con.getReadableDatabase();
        Trabajador t = null;
        trabajadores = new ArrayList<Trabajador>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+utilidades.TABLA_TRABAJADOR,null);
        while(cursor.moveToNext()){
            t = new Trabajador();
            t.setCodigo_trabajador(cursor.getString(0));
            t.setNombre(cursor.getString(1));
            t.setApe_pat(cursor.getString(2));
            t.setApe_mat(cursor.getString(3));
            t.setTelefono(cursor.getString(4));
            t.setFecha(cursor.getString(5));

            trabajadores.add(t);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        informacion = new ArrayList<String>();
        for(int i=0;i<trabajadores.size();i++){
            informacion.add(trabajadores.get(i).getCodigo_trabajador()+" - "+trabajadores.get(i).getNombre());
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_regresar_trabajador:
                intent = new Intent (v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.btn_agregar_trabajador:
                intent = new Intent (v.getContext(), agregar_trabajador.class);
                startActivityForResult(intent, 0);
                break;
        }

    }
}
