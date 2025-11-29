package com.example.telecable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Telecable.db";
    private static final int DATABASE_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 1. Crear Tabla CLIENTE
        // Nota: En SQLite no hay ENUM, usamos TEXT.
        String crearCliente = "CREATE TABLE cliente (" +
                "id_cliente INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, " +
                "usuario TEXT, " +
                "contrasena TEXT, " +
                "contrato_numero INTEGER)";
        db.execSQL(crearCliente);

        // 2. Crear Tabla TÉCNICO
        String crearTecnico = "CREATE TABLE tecnico (" +
                "id_tecnico INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, " +
                "usuario TEXT, " +
                "contrasena TEXT, " +
                "codigo_token TEXT)"; // El código especial de 5 dígitos
        db.execSQL(crearTecnico);

        // 3. Insertar DATOS DE PRUEBA (Para que puedas entrar)
        // Cliente: Usuario: cliente1, Pass: 1234, Contrato: 1001
        db.execSQL("INSERT INTO cliente (nombre, usuario, contrasena, contrato_numero) VALUES ('Juan Perez', 'cliente1', '1234', 1001)");

        // Técnico: Usuario: admin1, Pass: admin, Codigo: 55555
        db.execSQL("INSERT INTO tecnico (nombre, usuario, contrasena, codigo_token) VALUES ('Tecnico Luis', 'admin1', 'admin', '55555')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS cliente");
        db.execSQL("DROP TABLE IF EXISTS tecnico");
        onCreate(db);
    }

    // --- MÉTODOS DE VALIDACIÓN ---

    // Validar Cliente (Usuario + Password + Contrato)
    public boolean validarCliente(String usuario, String password, String contrato) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM cliente WHERE usuario=? AND contrasena=? AND contrato_numero=?",
                new String[]{usuario, password, contrato}
        );
        boolean existe = cursor.getCount() > 0;
        cursor.close();
        return existe;
    }

    // Validar Técnico (Usuario + Password + Código Especial)
    public boolean validarTecnico(String usuario, String password, String codigo) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM tecnico WHERE usuario=? AND contrasena=? AND codigo_token=?",
                new String[]{usuario, password, codigo}
        );
        boolean existe = cursor.getCount() > 0;
        cursor.close();
        return existe;
    }
}