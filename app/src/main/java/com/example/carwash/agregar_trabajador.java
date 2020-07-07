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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class agregar_trabajador extends AppCompatActivity implements View.OnClickListener{
    EditText codigo_trabajador;
    EditText nombre;
    EditText paterno;
    EditText materno;
    EditText telefono;
    EditText fecha_contratacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_trabajador);
        setTitle("Agregar trabajador");

        View boton = findViewById(R.id.btn_guardar_agregar_trabajador);
        boton.setOnClickListener(this);
        codigo_trabajador = (EditText) findViewById(R.id.txt_codigo_trabajador);
        nombre = (EditText) findViewById(R.id.txt_nombre_trabajador);
        paterno = (EditText) findViewById(R.id.txt_apepat_trabajador);
        materno = (EditText) findViewById(R.id.txt_apemat_trabajador);
        telefono = (EditText) findViewById(R.id.txt_telefono_trabajador);
        fecha_contratacion = (EditText) findViewById(R.id.txt_fecha_trabajador);
        String fecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        fecha_contratacion.setText(fecha);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_guardar_agregar_trabajador:
                if(codigo_trabajador.getText().toString().length() > 0 &&
                        nombre.getText().toString().length() > 0 &&
                        paterno.getText().toString().length() > 0 &&
                        materno.getText().toString().length() > 0 &&
                        telefono.getText().toString().length() > 0 &&
                        fecha_contratacion.getText().toString().length() > 0
                ){
                    registrarTrabajador();
                    Intent intent = new Intent(v.getContext(),trabajadores.class);
                    startActivityForResult(intent, 0);
                }else{
                    Toast.makeText(this,"Por favor complete los campos solicitados.",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void registrarTrabajador() {
        ConexionSQLite con = new ConexionSQLite(this, utilidades.NOMBRE_BASE_DE_DATOS,null,1);
        SQLiteDatabase db = con.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(utilidades.CODIGO_TRABAJADOR,codigo_trabajador.getText().toString());
        values.put(utilidades.NOMBRE_TRABAJADOR,nombre.getText().toString());
        values.put(utilidades.APELLIDO_PATERNO_TRABAJADOR,paterno.getText().toString());
        values.put(utilidades.APELLIDO_MATERNO_TRABAJADOR,materno.getText().toString());
        values.put(utilidades.TELEFONO_TRABAJADOR,telefono.getText().toString());
        values.put(utilidades.FECHA_CONTRATACION,fecha_contratacion.getText().toString());

        Long idResultante =  db.insert(utilidades.TABLA_TRABAJADOR,utilidades.CODIGO_TRABAJADOR,values);
        Toast.makeText(getApplicationContext(),"¡Trabajador registrado!",Toast.LENGTH_LONG).show();
        db.close();
    }
}
