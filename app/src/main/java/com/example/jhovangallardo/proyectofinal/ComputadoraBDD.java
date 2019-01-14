package com.example.jhovangallardo.proyectofinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import java.util.ArrayList;

/**
*Se definen los metodos necesarios para administrar la base de datos de ordenes (insertar, consultar, modificar y eliminar ordenes)
 */
public class ComputadoraBDD {

	private static final int VERSION = 1;
	private static final String NOM_BDD = "computadora.db";
	private static final String TABLA_COMPUTADORAS = "tabla_computadoras";
	private static final String COL_ID = "ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_CLIENT = "CLIENTE";
	private static final int NUM_COL_CLIENT = 1;
	private static final String COL_PROC = "PROCESADOR";
	private static final int NUM_COL_PROC = 2;
	private static final String COL_ALM = "ALMACENAMIENTO";
	private static final int NUM_COL_ALM = 3;
	private static final String COL_CAR = "CARGADOR";
	private static final int NUM_COL_CAR = 4;
	private static final String COL_AUD = "AUDIFONOS";
	private static final int NUM_COL_AUD = 5;



	private SQLiteDatabase bdd;

	private ComputadoraBaseSQLite computadoras;

	public ComputadoraBDD(Context context) {
		computadoras = new ComputadoraBaseSQLite(context, NOM_BDD, null, VERSION);
	}

	public void openForWrite() {
			bdd = computadoras.getWritableDatabase();
	}

	public void openForRead() {
			bdd = computadoras.getReadableDatabase();
	}

	public void close() {
		bdd.close();
	}

	public SQLiteDatabase getBdd() {
		return bdd;
	}

	public long insertComputadora(Computadora computadora) {
		ContentValues content = new ContentValues();
		content.put(COL_CLIENT,computadora.getCliente());
		content.put(COL_PROC,computadora.getProcesador());
		content.put(COL_ALM,computadora.getAlmacenamiento());
		content.put(COL_CAR,computadora.getCargador());
		content.put(COL_AUD,computadora.getAudifonos());
		return bdd.insert(TABLA_COMPUTADORAS, null, content);
	}

	public int updateComputadora(int id, Computadora computadora) {
		ContentValues content = new ContentValues();
		content.put(COL_CLIENT,computadora.getCliente());
		content.put(COL_PROC,computadora.getProcesador());
		content.put(COL_ALM,computadora.getAlmacenamiento());
		content.put(COL_CAR,computadora.getCargador());
		content.put(COL_AUD,computadora.getAudifonos());
		return bdd.update(TABLA_COMPUTADORAS, content, COL_ID + " = " + id, null);
	}

	public int removeComputadora(int id) {
		return bdd.delete(TABLA_COMPUTADORAS, COL_ID + " = " + id, null);
	}

	public Computadora getComputadora(int id) {
		Cursor c = bdd.query(TABLA_COMPUTADORAS, new String[] { COL_ID, COL_CLIENT,COL_PROC,COL_ALM,
				COL_CAR,COL_AUD}, COL_ID + " = " + id, null, null,
				null, COL_ID);
		c.moveToFirst();
		Computadora computadora = cursorToComputadora(c);
		//Log.v("ComputadoraBDD","Obtener cursor: " + computadora.toString());
		return computadora;
	}

	public Computadora cursorToComputadora(Cursor c) {
		Log.v("ComputadoraBDD","Cursor: " + c.getCount());
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		Computadora computadora = new Computadora();
		computadora.setId(c.getInt(NUM_COL_ID));
		computadora.setCliente(c.getString(NUM_COL_CLIENT));
		computadora.setProcesador(c.getString(NUM_COL_PROC));
		computadora.setAlmacenamiento(c.getString(NUM_COL_ALM));
		computadora.setCargador(c.getString(NUM_COL_CAR));
		computadora.setAudifonos(c.getString(NUM_COL_AUD));
		c.close();
		return computadora;
	}

	public ArrayList<Computadora> getAllComputadoras() {
		Cursor c = bdd.query(TABLA_COMPUTADORAS, new String[] { COL_ID, COL_CLIENT,COL_PROC,COL_ALM,
				COL_CAR,COL_AUD}, null, null, null, null, COL_ID);
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		ArrayList<Computadora> computadoraList = new ArrayList<Computadora>();
		while (c.moveToNext()) {
			Computadora computadora = new Computadora();
			computadora.setId(c.getInt(NUM_COL_ID));
			computadora.setCliente(c.getString(NUM_COL_CLIENT));
			computadora.setProcesador(c.getString(NUM_COL_PROC));
			computadora.setAlmacenamiento(c.getString(NUM_COL_ALM));
			computadora.setCargador(c.getString(NUM_COL_CAR));
			computadora.setAudifonos(c.getString(NUM_COL_AUD));
			computadoraList.add(computadora);
		}
		c.close();
		return computadoraList;
	}

}
