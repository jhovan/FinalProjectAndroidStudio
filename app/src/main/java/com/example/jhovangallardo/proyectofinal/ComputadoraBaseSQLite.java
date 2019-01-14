package com.example.jhovangallardo.proyectofinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Crea la estructura de la base de datos para almacenar las ordenes de las computadoras
 */

public class ComputadoraBaseSQLite extends SQLiteOpenHelper {

	private static final String TABLA_COMPUTADORA = "tabla_computadoras";
	private static final String COL_ID = "ID";
	private static final String COL_CLIENT = "CLIENTE";
	private static final String COL_PROC = "PROCESADOR";
	private static final String COL_ALM = "ALMACENAMIENTO";
	private static final String COL_CAR = "CARGADOR";
	private static final String COL_AUD = "AUDIFONOS";

	private static final String CREATE_BDD = "CREATE TABLE " + TABLA_COMPUTADORA + " (" +
	COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_CLIENT + " TEXT NOT NULL, " +
	COL_PROC + " TEXT NOT NULL, " + COL_ALM + " TEXT NOT NULL," + COL_CAR + " TEXT NOT NULL," +
            COL_AUD + " TEXT NOT NULL);";

	public ComputadoraBaseSQLite(Context context, String name, CursorFactory factory, int version) {
		super (context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_BDD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE " + TABLA_COMPUTADORA);
		onCreate(db);
	}
	
}
