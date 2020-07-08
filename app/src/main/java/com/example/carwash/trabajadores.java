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
        View agregar = findViewById(R.id.btn_agregar_trabajador);
        agregar.setOnClickListener(this);
        listview = (ListView) findViewById(R.id.lista_trabajadores);
        informacion = new ArrayList<String>();

        con = new ConexionSQLite(getApplicationContext(), utilidades.NOMBRE_BASE_DE_DATOS,null,1);
        consultarListaTrabajadores();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, informacion);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
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
                        })
                        .setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog.Builder alerta = new AlertDialog.Builder(trabajadores.this);
                                alerta.setMessage("¿Está seguro de eliminar este registro?, esta acción no es reversible.")
                                        .setCancelable(false)
                                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if(eliminarTrabajador(utilidades.ELIMINAR_TRABAJADOR+"'"+trabajadores.get(position).getCodigo_trabajador()+"'")){
                                                    trabajadores.this.recreate();
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
                                Intent intent = new Intent(trabajadores.this,agregar_trabajador.class);
                                intent.putExtra("codigo_trabajador",trabajadores.get(position).getCodigo_trabajador());
                                intent.putExtra("nombre",trabajadores.get(position).getNombre());
                                intent.putExtra("paterno",trabajadores.get(position).getApe_pat());
                                intent.putExtra("materno",trabajadores.get(position).getApe_mat());
                                intent.putExtra("telefono",trabajadores.get(position).getTelefono());
                                intent.putExtra("fecha",trabajadores.get(position).getFecha());
                                intent.putExtra("bandera",utilidades.ACTUALIZAR);
                                startActivity(intent);
                            }
                        });

                AlertDialog ventana = alerta.create();
                ventana.setTitle("Trabajador");
                ventana.show();
            }
        });
    }
    boolean eliminarTrabajador(String sql){
        SQLiteDatabase db = con.getWritableDatabase();
        if(db!=null){
            db.execSQL(sql);
            db.close();
            return true;
        }
        return false;
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
            case R.id.btn_agregar_trabajador:
                intent = new Intent (v.getContext(), agregar_trabajador.class);
                startActivityForResult(intent, 0);
                break;
        }

    }
}
