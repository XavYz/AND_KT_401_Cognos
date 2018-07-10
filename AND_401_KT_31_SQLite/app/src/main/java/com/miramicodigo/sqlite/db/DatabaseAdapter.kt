package com.miramicodigo.sqlite.db

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.miramicodigo.sqlite.Constants

class DatabaseAdapter(context: Context) {

    private val databaseHelper: PersonasDatabaseHelper
    private var db: SQLiteDatabase? = null

    init {
        databaseHelper = PersonasDatabaseHelper(context)
    }

    fun abrir() {
        db = databaseHelper.writableDatabase
    }

    fun cerrar() {
        databaseHelper.close()
    }

    fun adicionarPersona(nombre: String, telefono: Long, correo: String, genero: String): Long {
        val contentValues = ContentValues()
        contentValues.put(Constants.NOMBRE, nombre)
        contentValues.put(Constants.TELEFONO, telefono)
        contentValues.put(Constants.CORREO, correo)
        contentValues.put(Constants.GENERO, genero)
        return db!!.insert(Constants.TABLA_PERSONA, null, contentValues)
    }

    fun actualizarPersona(id: Long, nombre: String, telefono: Long,
                          correo: String, genero: String): Int {
        val contentValues = ContentValues()
        contentValues.put(Constants.NOMBRE, nombre)
        contentValues.put(Constants.TELEFONO, telefono)
        contentValues.put(Constants.CORREO, correo)
        contentValues.put(Constants.GENERO, genero)
        return db!!.update(Constants.TABLA_PERSONA, contentValues, "${Constants.ID}=?", arrayOf(id.toString() + ""))
    }

    fun eliminarPersona(id: Long): Boolean {
        return db!!.delete(Constants.TABLA_PERSONA, "${Constants.ID}=$id", null) > 0
    }

    fun obtenerPersona(id: Long): Cursor {
        return db!!.query(Constants.TABLA_PERSONA,
                arrayOf(Constants.ID, Constants.NOMBRE, Constants.TELEFONO, Constants.CORREO, Constants.GENERO),
                "${Constants.ID}=?", arrayOf(id.toString() + ""), null, null, null)
    }

    fun obtenerTodasPersonas(): Cursor {
        return db!!.query(Constants.TABLA_PERSONA,
                arrayOf(Constants.ID, Constants.NOMBRE, Constants.TELEFONO, Constants.CORREO, Constants.GENERO), null, null, null, null, null)
    }

    private class PersonasDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "dbpersonas.db", null, 1) {

        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL("CREATE TABLE ${Constants.TABLA_PERSONA} (" +
                    "${Constants.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${Constants.NOMBRE} TEXT NOT NULL, " +
                    "${Constants.TELEFONO} INTEGER, " +
                    "${Constants.CORREO} TEXT, " +
                    "${Constants.GENERO} TEXT)")
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS ${Constants.TABLA_PERSONA}")
            onCreate(db)
        }
    }
}
