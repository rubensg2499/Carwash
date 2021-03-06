package com.example.carwash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carwash.utilidades.utilidades;

public class agregar_servicio extends AppCompatActivity implements View.OnClickListener{
    private int bandera;
    EditText codigo;
    EditText nombre;
    EditText costo;
    EditText descripcion;
    ConexionSQLite con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bandera = getIntent().getIntExtra("bandera",utilidades.GUARDAR);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_servicio);
        if(bandera == utilidades.GUARDAR){
            setTitle("Agregar servicio");
        }else if(bandera == utilidades.ACTUALIZAR){
            setTitle("Actualizar servicio");
        }
        View boton = findViewById(R.id.btn_guardar_agregar_servicio);
        boton.setOnClickListener(this);
        con = new ConexionSQLite(getApplicationContext(), utilidades.NOMBRE_BASE_DE_DATOS,null,1);
        codigo = (EditText) findViewById(R.id.txt_codigo_servicio);
        nombre = (EditText) findViewById(R.id.txt_servicio_servicio);
        costo = (EditText) findViewById(R.id.txt_costo_servicio);
        descripcion = (EditText) findViewById(R.id.txt_descripcion_servicio);

        if(bandera == utilidades.ACTUALIZAR){
            codigo.setText(getIntent().getStringExtra("codigo"));
            nombre.setText(getIntent().getStringExtra("nombre"));
            costo.setText(""+getIntent().getDoubleExtra("costo",0.0));
            descripcion.setText(getIntent().getStringExtra("descripcion"));
            codigo.setEnabled(false);
        }
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_guardar_agregar_servicio:
                if(bandera == utilidades.GUARDAR){
                    if(codigo.getText().toString().length() > 0 &&
                            nombre.getText().toString().length() > 0 &&
                            costo.getText().toString().length() > 0 &&
                            descripcion.getText().toString().length() > 0
                    ){
                        registrarServicio();
                        Intent intent = new Intent(v.getContext(),MainActivity.class);
                        startActivityForResult(intent, 0);
                    }else{
                        Toast.makeText(this,"Por favor complete los campos solicitados.",Toast.LENGTH_LONG).show();
                    }
                }else if(bandera == utilidades.ACTUALIZAR){
                    if(codigo.getText().toString().length() > 0 &&
                            nombre.getText().toString().length() > 0 &&
                            costo.getText().toString().length() > 0 &&
                            descripcion.getText().toString().length() > 0
                    ){
                        actualizarServicio();
                        Intent intent = new Intent(v.getContext(),MainActivity.class);
                        startActivityForResult(intent, 0);
                    }else{
                        Toast.makeText(this,"Por favor complete los campos solicitados.",Toast.LENGTH_LONG).show();
                    }
                }

                break;
        }
    }

    private void registrarServicio() {
        SQLiteDatabase db = con.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(utilidades.CODIGO_SERVICIO,codigo.getText().toString());
        values.put(utilidades.NOMBRE_SERVICIO,nombre.getText().toString());
        values.put(utilidades.COSTO_SERVICIO,Double.parseDouble(costo.getText().toString()));
        values.put(utilidades.DESCRIPCION_SERVICIO,descripcion.getText().toString());
        Long idResultante =  db.insert(utilidades.TABLA_SERVICIO,utilidades.CODIGO_SERVICIO,values);
        Toast.makeText(getApplicationContext(),"??Servicio registrado!",Toast.LENGTH_LONG).show();
        db.close();
    }
    private void actualizarServicio() {
        SQLiteDatabase db = con.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(utilidades.NOMBRE_SERVICIO,nombre.getText().toString());
        values.put(utilidades.COSTO_SERVICIO,Double.parseDouble(costo.getText().toString()));
        values.put(utilidades.DESCRIPCION_SERVICIO,descripcion.getText().toString());
        int idResultante =  db.update(utilidades.TABLA_SERVICIO,values,utilidades.CODIGO_SERVICIO +" = '"+codigo.getText().toString()+"'",null);
        Toast.makeText(getApplicationContext(),"??Servicio actualizado!",Toast.LENGTH_LONG).show();
        db.close();
    }
}
