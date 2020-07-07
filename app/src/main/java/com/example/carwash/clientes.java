package com.example.carwash;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;

import android.content.ClipData;
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

import com.example.carwash.tablas.Cliente;
import com.example.carwash.tablas.Usuario;
import com.example.carwash.utilidades.utilidades;

import java.util.ArrayList;

public class clientes extends AppCompatActivity implements View.OnClickListener {
    private ListView listview;
    private ArrayList<String> informacion;
    private ArrayList<Cliente> clientes;
    ArrayAdapter<String> adapter;
    ConexionSQLite con;

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
        informacion = new ArrayList<String>();

        con = new ConexionSQLite(getApplicationContext(), utilidades.NOMBRE_BASE_DE_DATOS,null,1);
        consultarListaClientes();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, informacion);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                AlertDialog.Builder alerta = new AlertDialog.Builder(clientes.this);
                alerta.setMessage(clientes.get(position).getNombre()
                        +" "+clientes.get(position).getApellidos()
                        +"\nTeléfono: "+clientes.get(position).getTelefono()
                        +"\nPlacas: "+clientes.get(position).getPlacas()
                        +"\nMarca: "+clientes.get(position).getMarca()
                        +"\nTipo: "+clientes.get(position).getTipo()
                        +"\nModelo: "+clientes.get(position).getModelo())
                .setCancelable(false)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog ventana = alerta.create();
                ventana.setTitle("Cliente");
                ventana.show();
            }
        });
    }

    private void consultarListaClientes() {
        SQLiteDatabase db = con.getReadableDatabase();
        Cliente c = null;
        clientes = new ArrayList<Cliente>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+utilidades.TABLA_CLIENTE,null);
        while(cursor.moveToNext()){
            c = new Cliente();
            c.setNombre(cursor.getString(0));
            c.setApellidos(cursor.getString(1));
            c.setTelefono(cursor.getString(2));
            c.setPlacas(cursor.getString(3));
            c.setMarca(cursor.getString(4));
            c.setTipo(cursor.getString(5));
            c.setModelo(cursor.getString(6));

            clientes.add(c);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        informacion = new ArrayList<String>();
        for(int i=0;i<clientes.size();i++){
            informacion.add(clientes.get(i).getPlacas()+" - "+clientes.get(i).getNombre()+" "+clientes.get(i).getApellidos());
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_agregar_cliente:
                intent = new Intent (v.getContext(), agregar_cliente.class);
                startActivityForResult(intent, 0);
                break;
        }

    }

}
