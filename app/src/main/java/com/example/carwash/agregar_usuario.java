package com.example.carwash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.carwash.tablas.Usuario;
import com.example.carwash.utilidades.utilidades;

import java.sql.SQLData;
import java.util.ArrayList;

public class agregar_usuario extends AppCompatActivity implements View.OnClickListener{
    EditText usuario;
    EditText pass;
    EditText reppass;
    Spinner codigo_trabajador;
    ArrayList<String> codigos;
    ConexionSQLite con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);
        this.setTitle(R.string.titulo_agregar_usuario);
        codigos = new ArrayList<String>();
        View boton = findViewById(R.id.btn_guardar_agregar_usuario);
        boton.setOnClickListener(this);
        usuario = (EditText) findViewById(R.id.txt_nombre_usuario);
        pass = (EditText) findViewById(R.id.txt_pass_usuario);
        reppass = (EditText) findViewById(R.id.txt_repetirpassword_usuario);
        codigo_trabajador = (Spinner) findViewById(R.id.cbx_codigo_trabajador_usuario);
        con = new ConexionSQLite(getApplicationContext(), utilidades.NOMBRE_BASE_DE_DATOS,null,1);
        cargarCodigos();
        codigo_trabajador.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,codigos));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_guardar_agregar_usuario:
                if(usuario.getText().toString().length() > 0 &&
                        pass.getText().toString().length() > 0 &&
                        reppass.getText().toString().length() > 0 &&
                        codigo_trabajador.getSelectedItemPosition() > 0
                ){
                    if(pass.getText().toString().equals(reppass.getText().toString())){
                        registrarUsuario();
                        Intent intent = new Intent(v.getContext(),MainActivity.class);
                        startActivityForResult(intent, 0);
                    }else{
                        Toast.makeText(this,"Las contraseñas no coinciden, intente de nuevo.",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(this,"Por favor complete los campos solicitados.",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public void registrarUsuario(){
        ConexionSQLite con = new ConexionSQLite(this, utilidades.NOMBRE_BASE_DE_DATOS,null,1);
        SQLiteDatabase db = con.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(utilidades.CODIGO_USUARIO,usuario.getText().toString());
        values.put(utilidades.PASSWORD_USUARIO,pass.getText().toString());
        values.put(utilidades.CODIGO_TRABAJADOR,codigo_trabajador.getSelectedItem().toString());
        Long idResultante =  db.insert(utilidades.TABLA_USUARIO,utilidades.CODIGO_USUARIO,values);
        Toast.makeText(getApplicationContext(),"¡Usuario registrado!",Toast.LENGTH_LONG).show();
        db.close();
    }

    public void cargarCodigos(){
        SQLiteDatabase db = con.getReadableDatabase();
        codigos.add("Seleccione un código");
        Cursor cursor = db.rawQuery("SELECT * FROM "+utilidades.TABLA_TRABAJADOR,null);
        while(cursor.moveToNext()){
            codigos.add(cursor.getString(0));
        }
    }
}
