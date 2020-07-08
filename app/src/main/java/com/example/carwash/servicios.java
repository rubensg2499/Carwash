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
import com.example.carwash.tablas.Servicio;
import com.example.carwash.utilidades.utilidades;

import java.util.ArrayList;

public class servicios extends AppCompatActivity implements View.OnClickListener {
    private ListView listview;
    private ArrayList<String> informacion;
    private ArrayList<Servicio> servicios;
    ArrayAdapter<String> adapter;
    ConexionSQLite con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);
        this.setTitle(R.string.titulo_servicio);
        View agregar = findViewById(R.id.btn_agregar_servicio);
        agregar.setOnClickListener(this);
        listview = (ListView) findViewById(R.id.lista_servicios);
        informacion = new ArrayList<String>();

        con = new ConexionSQLite(getApplicationContext(), utilidades.NOMBRE_BASE_DE_DATOS,null,1);
        consultarListaServicios();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, informacion);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                AlertDialog.Builder alerta = new AlertDialog.Builder(servicios.this);
                alerta.setMessage(servicios.get(position).getNombre()
                        +"\nCódigo "+servicios.get(position).getCodigo()
                        +"\nCosto: "+servicios.get(position).getCosto()
                        +"\nDescripción: "+servicios.get(position).getDescripcion())
                        .setCancelable(false)
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog.Builder alerta = new AlertDialog.Builder(servicios.this);
                                alerta.setMessage("¿Está seguro de eliminar este registro?, esta acción no es reversible.")
                                        .setCancelable(false)
                                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if(eliminarServicio(utilidades.ELIMINAR_SERVICIO+"'"+servicios.get(position).getCodigo()+"'")){
                                                    servicios.this.recreate();
                                                    Toast.makeText(getApplicationContext(),"Registro eliminado",Toast.LENGTH_LONG).show();
                                                }else{
                                                    Toast.makeText(getApplicationContext(),"¡Error no se pudo borrar el registro!",Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        })
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        });
                                AlertDialog ventana = alerta.create();
                                ventana.setTitle("Alerta");
                                ventana.show();
                            }
                        })
                        .setNeutralButton("Actualizar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(servicios.this,agregar_servicio.class);
                                intent.putExtra("codigo",servicios.get(position).getCodigo());
                                intent.putExtra("nombre",servicios.get(position).getNombre());
                                intent.putExtra("costo",servicios.get(position).getCosto());
                                intent.putExtra("descripcion",servicios.get(position).getDescripcion());
                                intent.putExtra("bandera",utilidades.ACTUALIZAR);
                                startActivity(intent);
                            }
                        });

                AlertDialog ventana = alerta.create();
                ventana.setTitle("Servicio");
                ventana.show();
            }
        });
    }
    boolean eliminarServicio(String sql){
        SQLiteDatabase db = con.getWritableDatabase();
        if(db!=null){
            db.execSQL(sql);
            db.close();
            return true;
        }
        return false;
    }
    private void consultarListaServicios() {
        SQLiteDatabase db = con.getReadableDatabase();
        Servicio s = null;
        servicios = new ArrayList<Servicio>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+utilidades.TABLA_SERVICIO,null);
        while(cursor.moveToNext()){
            s = new Servicio();
            s.setCodigo(cursor.getString(0));
            s.setNombre(cursor.getString(1));
            s.setCosto(Double.parseDouble(cursor.getString(2)));
            s.setDescripcion(cursor.getString(3));
            servicios.add(s);
        }
        obtenerLista();
    }
    private void obtenerLista() {
        informacion = new ArrayList<String>();
        for(int i=0;i<servicios.size();i++){
            informacion.add(servicios.get(i).getCodigo()+" - "+servicios.get(i).getNombre());
        }
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_agregar_servicio:
                intent = new Intent (v.getContext(), agregar_servicio.class);
                intent.putExtra("bandera", utilidades.GUARDAR);
                startActivityForResult(intent, 0);
                break;
        }

    }
}
