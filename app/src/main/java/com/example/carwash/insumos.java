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
        View agregar = findViewById(R.id.btn_agregar_insumo);
        agregar.setOnClickListener(this);
        listview = (ListView) findViewById(R.id.lista_insumos);
        informacion = new ArrayList<String>();

        con = new ConexionSQLite(getApplicationContext(), utilidades.NOMBRE_BASE_DE_DATOS,null,1);
        consultarListaInsumos();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, informacion);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
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
                        })
                        .setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog.Builder alerta = new AlertDialog.Builder(insumos.this);
                                alerta.setMessage("¿Está seguro de eliminar este registro?, esta acción no es reversible.")
                                        .setCancelable(false)
                                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if(eliminarInsumo(utilidades.ELIMINAR_INSUMO+"'"+insumos.get(position).getCodigo()+"'")){
                                                    insumos.this.recreate();
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
                                Intent intent = new Intent(insumos.this,agregar_insumo.class);
                                intent.putExtra("codigo",insumos.get(position).getCodigo());
                                intent.putExtra("nombre",insumos.get(position).getNombre());
                                intent.putExtra("precio",insumos.get(position).getPrecio());
                                intent.putExtra("cantidad",insumos.get(position).getCantidad());
                                intent.putExtra("descripcion",insumos.get(position).getDescripcion());
                                intent.putExtra("bandera",utilidades.ACTUALIZAR);
                                startActivity(intent);
                            }
                        });

                AlertDialog ventana = alerta.create();
                ventana.setTitle("Insumo");
                ventana.show();
            }
        });
    }
    boolean eliminarInsumo(String sql){
        SQLiteDatabase db = con.getWritableDatabase();
        if(db!=null){
            db.execSQL(sql);
            db.close();
            return true;
        }
        return false;
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
                intent.putExtra("bandera", utilidades.GUARDAR);
                startActivityForResult(intent, 0);
                break;
        }

    }
}
