package com.miramicodigo.ormlite.db

import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import com.miramicodigo.ormlite.App
import com.miramicodigo.ormlite.model.Persona


object DatabaseHelper: OrmLiteSqliteOpenHelper
            (App.instance, "personas.db", null, 1) {

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        TableUtils.createTableIfNotExists(connectionSource, Persona::class.java)
    }

    override fun onUpgrade(database: SQLiteDatabase?, connectionSource: ConnectionSource?,
                           oldVersion: Int, newVersion: Int) {

        TableUtils.dropTable<Persona, Any>(connectionSource, Persona::class.java, true)
        onCreate(database, connectionSource)
    }
}
