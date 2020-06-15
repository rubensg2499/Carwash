package com.example.carwash;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.carwash.utilidades.utilidades;

public class ConexionSQLite extends SQLiteOpenHelper {

    public ConexionSQLite(@Nullable Context context,
                          @Nullable String name,
                          @Nullable SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(utilidades.CREAR_TABLA_TRABAJADOR);
        db.execSQL(utilidades.CREAR_TABLA_USUARIO);
        db.execSQL(utilidades.CREAR_TABLA_CLIENTE);
        db.execSQL(utilidades.CREAR_TABLA_INSUMO);
        db.execSQL(utilidades.CREAR_TABLA_SERVICIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(utilidades.BORRRAR_SI_EXISTE_USUARIO);
        db.execSQL(utilidades.BORRAR_SI_EXISTE_TRABAJADOR);
        db.execSQL(utilidades.BORRAR_SI_EXISTE_CLIENTE);
        db.execSQL(utilidades.BORRAR_SI_EXISTE_INSUMO);
        db.execSQL(utilidades.BORRAR_SI_EXISTE_SERVICIO);

        onCreate(db);
    }
}
