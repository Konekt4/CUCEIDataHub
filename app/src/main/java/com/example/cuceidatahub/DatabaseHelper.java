package com.example.cuceidatahub;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CuceiDataHub.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USUARIOS = "usuarios";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_APELLIDO_P = "apellido_paterno";
    public static final String COLUMN_APELLIDO_M = "apellido_materno";
    public static final String COLUMN_MATRICULA = "matricula";
    public static final String COLUMN_PASSWORD = "password";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_USUARIOS + " (" +
                    COLUMN_EMAIL + " TEXT PRIMARY KEY," +
                    COLUMN_NOMBRE + " TEXT NOT NULL," +
                    COLUMN_APELLIDO_P + " TEXT NOT NULL," +
                    COLUMN_APELLIDO_M + " TEXT," +
                    COLUMN_MATRICULA + " TEXT NOT NULL," +
                    COLUMN_PASSWORD + " TEXT NOT NULL)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        onCreate(db);
    }

    public long insertarUsuario(String email, String nombre, String apellidoP,
                                String apellidoM, String matricula, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_NOMBRE, nombre);
        values.put(COLUMN_APELLIDO_P, apellidoP);
        values.put(COLUMN_APELLIDO_M, apellidoM);
        values.put(COLUMN_MATRICULA, matricula);
        values.put(COLUMN_PASSWORD, password);

        long resultado = db.insert(TABLE_USUARIOS, null, values);
        db.close();

        return resultado;
    }
}
