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

public class agregar_cliente extends AppCompatActivity implements View.OnClickListener{
    private int bandera;
    EditText nombre;
    EditText apellidos;
    EditText telefono;
    EditText placas;
    EditText marca;
    EditText tipo;
    EditText modelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bandera = getIntent().getIntExtra("bandera",utilidades.GUARDAR);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cliente);
        if(bandera == utilidades.GUARDAR){
            setTitle("Agregar cliente");
        }else if(bandera == utilidades.ACTUALIZAR){
            setTitle("Actualizar cliente");
        }

        View boton = findViewById(R.id.btn_guardar_agregar_cliente);
        boton.setOnClickListener(this);
        nombre = (EditText) findViewById(R.id.txt_nombre_cliente);
        apellidos = (EditText) findViewById(R.id.txt_apellidos_cliente);
        telefono = (EditText) findViewById(R.id.txt_telefono_cliente);
        placas = (EditText) findViewById(R.id.txt_placas_cliente);
        marca = (EditText) findViewById(R.id.txt_marca_cliente);
        tipo = (EditText) findViewById(R.id.txt_tipo_cliente);
        modelo = (EditText) findViewById(R.id.txt_modelo_cliente);

        if(bandera == utilidades.ACTUALIZAR){
            nombre.setText(getIntent().getStringExtra("nombre"));
            apellidos.setText(getIntent().getStringExtra("apellidos"));
            telefono.setText(getIntent().getStringExtra("telefono"));
            placas.setText(getIntent().getStringExtra("placas"));
            marca.setText(getIntent().getStringExtra("marca"));
            tipo.setText(getIntent().getStringExtra("tipo"));
            modelo.setText(getIntent().getStringExtra("modelo"));
            placas.setEnabled(false);
        }
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_guardar_agregar_cliente:
                if(bandera == utilidades.GUARDAR){
                    if(nombre.getText().toString().length() > 0 &&
                            placas.getText().toString().length() > 0 &&
                            marca.getText().toString().length() > 0 &&
                            tipo.getText().toString().length() > 0
                    ){
                        registrarCliente();
                        Intent intent = new Intent(v.getContext(),MainActivity.class);
                        startActivityForResult(intent, 0);
                    }else{
                        Toast.makeText(this,"Por favor complete los campos solicitados.",Toast.LENGTH_LONG).show();
                    }
                }else if(bandera == utilidades.ACTUALIZAR){
                    if(nombre.getText().toString().length() > 0 &&
                            placas.getText().toString().length() > 0 &&
                            marca.getText().toString().length() > 0 &&
                            tipo.getText().toString().length() > 0
                    ){
                        actualizarCliente();
                        Intent intent = new Intent(v.getContext(),MainActivity.class);
                        startActivityForResult(intent, 0);
                    }else{
                        Toast.makeText(this,"Por favor complete los campos solicitados.",Toast.LENGTH_LONG).show();
                    }
                }

                break;
        }

    }

    private void registrarCliente() {
        ConexionSQLite con = new ConexionSQLite(this, utilidades.NOMBRE_BASE_DE_DATOS,null,1);
        SQLiteDatabase db = con.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(utilidades.NOMBRE_CLIENTE,nombre.getText().toString());
        values.put(utilidades.APELLIDOS_CLIENTE,apellidos.getText().toString());
        values.put(utilidades.TELEFONO_CLIENTE,telefono.getText().toString());
        values.put(utilidades.PLACAS_VEHICULO,placas.getText().toString());
        values.put(utilidades.MARCA_VEHICULO,marca.getText().toString());
        values.put(utilidades.TIPO_VEHICULO,tipo.getText().toString());
        values.put(utilidades.MODELO_VEHICULO,modelo.getText().toString());

        Long idResultante =  db.insert(utilidades.TABLA_CLIENTE,utilidades.PLACAS_VEHICULO,values);

        Toast.makeText(getApplicationContext(),"??Cliente registrado!",Toast.LENGTH_LONG).show();
        db.close();
    }
    private void actualizarCliente(){
        ConexionSQLite con = new ConexionSQLite(this, utilidades.NOMBRE_BASE_DE_DATOS,null,1);
        SQLiteDatabase db = con.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(utilidades.NOMBRE_CLIENTE,nombre.getText().toString());
        values.put(utilidades.APELLIDOS_CLIENTE,apellidos.getText().toString());
        values.put(utilidades.TELEFONO_CLIENTE,telefono.getText().toString());
        values.put(utilidades.MARCA_VEHICULO,marca.getText().toString());
        values.put(utilidades.TIPO_VEHICULO,tipo.getText().toString());
        values.put(utilidades.MODELO_VEHICULO,modelo.getText().toString());
        int idResultante =  db.update(utilidades.TABLA_CLIENTE,values,utilidades.PLACAS_VEHICULO+" = '"+placas.getText().toString()+"'",null);
        Toast.makeText(getApplicationContext(),"??Cliente actualizado!",Toast.LENGTH_LONG).show();
        db.close();
    }
}
