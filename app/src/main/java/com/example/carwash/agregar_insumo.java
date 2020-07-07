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

public class agregar_insumo extends AppCompatActivity implements View.OnClickListener{
    EditText codigo;
    EditText nombre;
    EditText precio;
    EditText cantidad;
    EditText descripcion;
    ConexionSQLite con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_insumo);
        this.setTitle("Agregar insumo");
        View boton = findViewById(R.id.btn_guardar_agregar_insumo);
        boton.setOnClickListener(this);
        con = new ConexionSQLite(getApplicationContext(), utilidades.NOMBRE_BASE_DE_DATOS,null,1);
        codigo = (EditText) findViewById(R.id.txt_codigo_insumo);
        nombre = (EditText) findViewById(R.id.txt_nombre_insumo);
        precio = (EditText) findViewById(R.id.txt_preciounitario_insumo);
        cantidad = (EditText) findViewById(R.id.txt_cantidad_insumo);
        descripcion = (EditText) findViewById(R.id.txt_descripcion_insumo);

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_guardar_agregar_insumo:
                if(codigo.getText().toString().length() > 0 &&
                        nombre.getText().toString().length() > 0
                ){
                    registrarInsumo();
                    Intent intent = new Intent(v.getContext(),MainActivity.class);
                    startActivityForResult(intent, 0);
                }else{
                    Toast.makeText(this,"Por favor complete los campos solicitados.",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void registrarInsumo() {
        SQLiteDatabase db = con.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(utilidades.CODIGO_INSUMO,codigo.getText().toString());
        values.put(utilidades.NOMBRE_INSUMO,nombre.getText().toString());
        values.put(utilidades.PRECIO_UNITARIO_INSUMO,Double.parseDouble(precio.getText().toString()));
        values.put(utilidades.CANTIDAD_INSUMO,Integer.parseInt(cantidad.getText().toString()));
        values.put(utilidades.DESCRIPCION_INSUMO,descripcion.getText().toString());
        Long idResultante =  db.insert(utilidades.TABLA_INSUMO,utilidades.CODIGO_INSUMO,values);
        Toast.makeText(getApplicationContext(),"¡Insumo registrado! ",Toast.LENGTH_LONG).show();
        db.close();
    }
}
