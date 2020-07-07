package com.example.carwash.utilidades;

public class utilidades {
    public static final String NOMBRE_BASE_DE_DATOS = "bd_carwash";
    //Tabla de usuarios
    public static final String TABLA_USUARIO = "usuario";
    public static final String CODIGO_USUARIO = "id_usuario";
    public static final String PASSWORD_USUARIO = "pass_usuario";

    //Tabla trabajadores
    public static final String TABLA_TRABAJADOR = "trabajador";
    public static final String CODIGO_TRABAJADOR = "codigo_trabajador";
    public static final String NOMBRE_TRABAJADOR = "nombre";
    public static final String APELLIDO_PATERNO_TRABAJADOR = "apellido_pat";
    public static final String APELLIDO_MATERNO_TRABAJADOR = "apellido_mat";
    public static final String TELEFONO_TRABAJADOR = "telefono";
    public static final String FECHA_CONTRATACION = "fecha";

    //Tabla clientes
    public static final String TABLA_CLIENTE = "cliente";
    public static final String NOMBRE_CLIENTE = "nombre";
    public static final String APELLIDOS_CLIENTE = "apellidos";
    public static final String TELEFONO_CLIENTE = "telefono";
    public static final String PLACAS_VEHICULO = "placas";
    public static final String MARCA_VEHICULO = "marca";
    public static final String TIPO_VEHICULO = "tipo";
    public static final String MODELO_VEHICULO = "modelo";

    //Tabla insumos
    public static final String TABLA_INSUMO = "insumo";
    public static final String CODIGO_INSUMO = "codigo_insumo";
    public static final String NOMBRE_INSUMO = "nombre";
    public static final String PRECIO_UNITARIO_INSUMO = "precio";
    public static final String CANTIDAD_INSUMO = "cantidad";
    public static final String DESCRIPCION_INSUMO = "descripcion";

    //Tabla servicios
    public static final String TABLA_SERVICIO = "servicio";
    public static final String CODIGO_SERVICIO = "codigo_servicio";
    public static final String NOMBRE_SERVICIO = "nombre";
    public static final String COSTO_SERVICIO = "costo";
    public static final String DESCRIPCION_SERVICIO = "descripcion";

    //Crear tablas
    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE "+TABLA_USUARIO+" ("+CODIGO_USUARIO+" TEXT NOT NULL,"+PASSWORD_USUARIO+" TEXT NOT NULL,"+CODIGO_TRABAJADOR+" TEXT NOT NULL, PRIMARY KEY("+CODIGO_USUARIO+"), FOREIGN KEY("+CODIGO_USUARIO+") REFERENCES "+TABLA_TRABAJADOR+"("+CODIGO_TRABAJADOR+"))";
    public static final String CREAR_TABLA_TRABAJADOR = "CREATE TABLE "+TABLA_TRABAJADOR+" ("+CODIGO_TRABAJADOR +" TEXT NOT NULL,"+NOMBRE_TRABAJADOR+" TEXT NOT NULL,"+APELLIDO_PATERNO_TRABAJADOR+" TEXT NOT NULL,"+APELLIDO_MATERNO_TRABAJADOR+" TEXT NOT NULL,"+TELEFONO_TRABAJADOR+" TEXT NOT NULL,"+FECHA_CONTRATACION+" TEXT NOT NULL, PRIMARY KEY("+CODIGO_TRABAJADOR+"))";
    public static final String CREAR_TABLA_CLIENTE = "CREATE TABLE "+TABLA_CLIENTE+" ("+NOMBRE_CLIENTE+" TEXT NOT NULL,"+APELLIDOS_CLIENTE+" TEXT, "+TELEFONO_CLIENTE+" TEXT,"+PLACAS_VEHICULO+" TEXT NOT NULL,"+MARCA_VEHICULO+" TEXT,"+TIPO_VEHICULO+" TEXT,"+MODELO_VEHICULO+" TEXT, PRIMARY KEY("+PLACAS_VEHICULO+"))";
    public static final String CREAR_TABLA_INSUMO = "CREATE TABLE "+TABLA_INSUMO+" ("+CODIGO_INSUMO+" TEXT NOT NULL,"+NOMBRE_INSUMO+" TEXT NOT NULL,"+PRECIO_UNITARIO_INSUMO+" REAL NOT NULL DEFAULT 0,"+CANTIDAD_INSUMO+" INTEGER NOT NULL DEFAULT 0,"+DESCRIPCION_INSUMO+" TEXT, PRIMARY KEY("+CODIGO_INSUMO+"))";
    public static final String CREAR_TABLA_SERVICIO = "CREATE TABLE "+TABLA_SERVICIO+" ("+CODIGO_SERVICIO+" TEXT NOT NULL,"+NOMBRE_SERVICIO+" TEXT NOT NULL,"+COSTO_SERVICIO+" REAL NOT NULL DEFAULT 0,"+DESCRIPCION_SERVICIO+" TEXT NOT NULL, PRIMARY KEY("+CODIGO_SERVICIO+"))";

    //Borrar tablas si existen
    public static final String BORRRAR_SI_EXISTE_USUARIO = "DROP TABLE IF EXIST "+TABLA_USUARIO;
    public static final String BORRAR_SI_EXISTE_TRABAJADOR = "DROP TABLE IF EXIST "+TABLA_TRABAJADOR;
    public static final String BORRAR_SI_EXISTE_CLIENTE = "DROP TABLE IF EXIST "+TABLA_CLIENTE;
    public static final String BORRAR_SI_EXISTE_INSUMO = "DROP TABLE IF EXIST "+TABLA_INSUMO;
    public static final String BORRAR_SI_EXISTE_SERVICIO = "DROP TABLE IF EXIST "+TABLA_SERVICIO;

    //Borrar un registro
    public  static final String ELIMINAR_USUARIO = "DELETE FROM "+TABLA_USUARIO+" WHERE "+CODIGO_USUARIO+" = ";
    public  static final String ELIMINAR_TRABAJADOR = "DELETE FROM "+TABLA_TRABAJADOR+" WHERE "+CODIGO_TRABAJADOR+" = ";
    public  static final String ELIMINAR_CLIENTE = "DELETE FROM "+TABLA_CLIENTE+" WHERE "+PLACAS_VEHICULO+" = ";
    public  static final String ELIMINAR_INSUMO = "DELETE FROM "+TABLA_INSUMO+" WHERE "+CODIGO_INSUMO+" = ";
    public  static final String ELIMINAR_SERVICIO = "DELETE FROM "+TABLA_SERVICIO+" WHERE "+CODIGO_SERVICIO+" = ";
}
