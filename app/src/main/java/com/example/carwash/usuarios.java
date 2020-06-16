package com.example.carwash;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.carwash.tablas.Usuario;

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

import com.example.carwash.tablas.Usuario;
import com.example.carwash.utilidades.utilidades;

import java.util.ArrayList;

public class usuarios extends AppCompatActivity implements View.OnClickListener {
    private ListView listview;
    private ArrayList<String> informacion;
    private ArrayList<Usuario> usuarios;
    ArrayAdapter<String> adapter;
    ConexionSQLite con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(R.string.titulo_usuario);
        setContentView(R.layout.activity_usuarios);
        View regresar = findViewById(R.id.btn_regresar_usuario);
        View agregar = findViewById(R.id.btn_agregar_usuario);
        regresar.setOnClickListener(this);
        agregar.setOnClickListener(this);
        listview = (ListView) findViewById(R.id.lista_usuarios);
        informacion = new ArrayList<String>();

        con = new ConexionSQLite(getApplicationContext(), utilidades.NOMBRE_BASE_DE_DATOS,null,1);
        consultarListaUsuarios();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, informacion);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                AlertDialog.Builder alerta = new AlertDialog.Builder(usuarios.this);
                alerta.setMessage("Usuario: "+usuarios.get(position).getCodigo()
                        +"\nCódigo trabajador: "+usuarios.get(position).getCodigo_trabajador())
                        .setCancelable(false)
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog ventana = alerta.create();
                ventana.setTitle("Usuario");
                ventana.show();
            }
        });
    }

    private void consultarListaUsuarios() {
        SQLiteDatabase db = con.getReadableDatabase();
        Usuario u = null;
        usuarios = new ArrayList<Usuario>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+utilidades.TABLA_USUARIO,null);
        while(cursor.moveToNext()){
            u = new Usuario();
            u.setCodigo(cursor.getString(0));
            u.setPassword(cursor.getString(1));
            u.setCodigo_trabajador(cursor.getString(2));
            usuarios.add(u);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        informacion = new ArrayList<String>();
        for(int i=0;i<usuarios.size();i++){
            informacion.add(usuarios.get(i).getCodigo()+" - "+usuarios.get(i).getCodigo_trabajador());
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_regresar_usuario:
                intent = new Intent (v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.btn_agregar_usuario:
                intent = new Intent (v.getContext(), agregar_usuario.class);
                startActivityForResult(intent, 0);
                break;
        }

    }
}
