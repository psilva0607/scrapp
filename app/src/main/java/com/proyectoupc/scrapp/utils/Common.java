package com.proyectoupc.scrapp.utils;

public class Common {

    /*DATABASE*/
    public static final String DB_NAME = "scrapp";

    /*USER TABLE CONSTANTS LOGIN*/
    public static final String USER_TABLE = "user";
    public static final String U_ID = "id";
    public static final String U_NAME = "name";
    public static final String U_EMAIL = "email";

    public static final String CREATE_USER_TABLE = "CREATE TABLE " +
            "" + USER_TABLE + " (" + U_ID + " " +
            "TEXT, " + U_NAME + " TEXT," + U_EMAIL + " TEXT)";

    /*USER TABLE CONSTANTS TALLERES*/
    public static final String T_TALLER = "t_talleres";
    public static final String T_FECHA_HORA = "FechaHora";
    public static final String T_NOMBRE_TALLER = "NombreTaller";
    public static final String T_DESCRIPCION = "Descripcion";

    public static final String CREATE_TALLER_TABLE = "CREATE TABLE " +
            "" + T_TALLER + " (" + T_FECHA_HORA + " " +
            "TEXT, " + T_NOMBRE_TALLER + " TEXT," + T_DESCRIPCION + " TEXT)";


    /*USER TABLE CONSTANTS SORTEOS*/
    public static final String S_SORTEO = "t_sorteos";
    public static final String S_FECHA_HORA = "FechaHora";
    public static final String S_NOMBRE_SORTEO = "NombreSorteo";
    public static final String S_DESCRIPCION = "Descripcion";

    public static final String CREATE_SORTEO_TABLE = "CREATE TABLE " +
            "" + S_SORTEO + " (" + S_FECHA_HORA + " " +
            "TEXT, " + S_NOMBRE_SORTEO + " TEXT," + S_DESCRIPCION + " TEXT)";
}
