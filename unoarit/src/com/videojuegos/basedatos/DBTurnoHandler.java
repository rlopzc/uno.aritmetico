package com.videojuegos.basedatos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBTurnoHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 3;

	// Database Name
	private static final String DATABASE_NAME = "gestionPartidas";

	// Contacts table name
	private static final String TABLE_PARTIDA = "partida";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_ID_PARTIDA = "num_partida";
	private static final String KEY_TURNO = "turno";
	private static final String KEY_JUGADOR = "jugador";
	private static final String KEY_COLOR = "color";
	private static final String KEY_FECHA = "fecha";

	private static final String KEY_OPERACION_MAZO = "operacion_mazo";
	private static final String KEY_OPERACION_JUGADA = "operacion_jugada";
	private static final String KEY_VALOR = "valor";

	public DBTurnoHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * Método para crear la tabla
	 */

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREAR_TABLA_PARTIDA = "CREATE TABLE " + TABLE_PARTIDA + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_ID_PARTIDA + " INTEGER,"
				+ KEY_TURNO + " INTEGER," + KEY_JUGADOR + " TEXT,"
				+ KEY_COLOR + " TEXT," + KEY_OPERACION_MAZO + " TEXT,"
				+ KEY_OPERACION_JUGADA + " TEXT," + KEY_VALOR + " TEXT, " + KEY_FECHA + " DATE DEFAULT CURRENT_DATE)";
		db.execSQL(CREAR_TABLA_PARTIDA);
	}

	/**
	 * Método para actualizar la tabla
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTIDA);
		onCreate(db);
	}

	/**
	 * Método para agregar un nuevo turno en la tabla
	 */
	public void agregarTurno(DBTurno turno) {
		String insertQuery = "INSERT INTO " + TABLE_PARTIDA + "(" +
				KEY_ID_PARTIDA + "," + KEY_TURNO + "," + KEY_JUGADOR + "," + KEY_COLOR + ","
				+ KEY_OPERACION_MAZO + "," + KEY_OPERACION_JUGADA + "," + KEY_VALOR + ") VALUES " + "("
				+ turno.getIdPartidaDB() + "," + turno.getTurnoDB() + ",'" + turno.getJugadorDB() + "','" + turno.getColorDB()
				+ "','" + turno.getOperacionMazoDB() + "','" + turno.getOperacionJugadaDB() + "','" + turno.getValorDB() + "')";
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(insertQuery);
	}

	/**
	 * Método para obtener todos los turnos de la tabla
	 */
	public List<DBTurno> obtenerTodosTurnos() {
		List<DBTurno> turnoList = new ArrayList<DBTurno>();
		String selectQuery = "SELECT  * FROM " + TABLE_PARTIDA;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				DBTurno turno = new DBTurno();
				turno.setID(Integer.parseInt(cursor.getString(0)));
				turno.setIdPartidaDB(Integer.parseInt(cursor.getString(1)));
				turno.setTurnoDB(Integer.parseInt(cursor.getString(2)));
				turno.setJugadorDB(cursor.getString(3));
				turno.setColorDB(cursor.getString(4));
				turno.setOperacionMazoDB(cursor.getString(5));
                turno.setOperacionJugadaDB(cursor.getString(6));
                turno.setValorDB(cursor.getString(7));
				turno.setFecha(cursor.getString(8));

				turnoList.add(turno);
			} while (cursor.moveToNext());
		}
		//System.out.println(turnoList.get(0));

		return turnoList;
	}

	/**
	 * Método para borrar todos los turnos de la tabla
	 */
	public void borrarTodosTurnos() {
		SQLiteDatabase db= this.getWritableDatabase();
		db.delete(TABLE_PARTIDA, null, null);
		System.out.println("Base de datos borrada.");
	}

	/**
	 * Método para asignar el ultimo id de partida de la tabla
	 */
	public int asignarUltimoIdPartida() {
        int ultimoIdDb = 0;
        String selectQuery = "SELECT  * FROM " + TABLE_PARTIDA;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if(cursor!=null && cursor.getCount()!=0){
			cursor.moveToLast();
			ultimoIdDb = cursor.getInt(cursor.getColumnIndex(KEY_ID_PARTIDA));
		}

        return ultimoIdDb + 1;
    }
}
