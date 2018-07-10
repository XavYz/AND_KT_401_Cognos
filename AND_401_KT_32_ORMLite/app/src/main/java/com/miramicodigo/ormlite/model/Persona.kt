package com.miramicodigo.ormlite.model

import com.j256.ormlite.dao.Dao
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import com.miramicodigo.ormlite.db.DatabaseHelper

@DatabaseTable(tableName = "persona")
data class Persona(
        @DatabaseField(generatedId = true)
        var id: Int? = null,

        @DatabaseField
        var nombre: String? = null
)

class PersonaDAO {
    companion object {
        lateinit var dao: Dao<Persona, Int>
    }

    init {
        dao = DatabaseHelper.getDao(Persona::class.java)
    }

    fun add(p: Persona) = dao.createOrUpdate(p)

    fun update(p: Persona) = dao.update(p)

    fun delete(p: Persona) = dao.delete(p)

    fun queryForAll() = dao.queryForAll()

    fun queryForOne(p: Persona) = dao.queryForSameId(p)

    fun removeAll() {
        for (table in queryForAll()) {
            dao.delete(table)
        }
    }
}
