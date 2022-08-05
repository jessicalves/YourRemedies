package com.jessimobilesolutions.yourremedies.repository

import android.content.ContentValues
import android.content.Context
import com.jessimobilesolutions.yourremedies.constantes.DataBaseConstantes
import com.jessimobilesolutions.yourremedies.model.MedModel

class MedRepository private constructor(context: Context) {
    private val medDataBase = MedDataBase(context)

    //Singleton - Controla o numero de acessos das instancias da minha classe
    companion object {
        private lateinit var repository: MedRepository

        fun getInstance(context: Context): MedRepository {
            if (!Companion::repository.isInitialized) {
                repository = MedRepository(context)
            }
            return repository
        }
    }

    fun insert(med: MedModel): Boolean {
        return try {
            val db = medDataBase.writableDatabase
            val classification = if (med.classification) 1 else 0
            val values = ContentValues()
            values.put(DataBaseConstantes.MED.COLUMNS.NAME, med.name)
            values.put(DataBaseConstantes.MED.COLUMNS.CLASSIFICATION, classification)

            db.insert(DataBaseConstantes.MED.TABLE_NAME, null, values)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(med: MedModel): Boolean {
        return try {
            val db = medDataBase.writableDatabase
            val classification = if (med.classification) 1 else 0
            val values = ContentValues()
            values.put(DataBaseConstantes.MED.COLUMNS.NAME, med.name)
            values.put(DataBaseConstantes.MED.COLUMNS.CLASSIFICATION, classification)

            val selection = "${DataBaseConstantes.MED.COLUMNS.ID} = ?"
            val args = arrayOf(med.id.toString())

            db.update(DataBaseConstantes.MED.TABLE_NAME, values, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun delete(medId: Int): Boolean {
        return try {
            val db = medDataBase.writableDatabase

            val selection = "${DataBaseConstantes.MED.COLUMNS.ID} = ?"
            val args = arrayOf(medId.toString())

            db.delete(DataBaseConstantes.MED.TABLE_NAME, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAll(): List<MedModel> {
        val list = mutableListOf<MedModel>()

        try {
            val db = medDataBase.readableDatabase
            val projection = arrayOf(
                DataBaseConstantes.MED.COLUMNS.ID,
                DataBaseConstantes.MED.COLUMNS.NAME,
                DataBaseConstantes.MED.COLUMNS.CLASSIFICATION
            )
            val cursor =
                db.query(
                    DataBaseConstantes.MED.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
                )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstantes.MED.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstantes.MED.COLUMNS.NAME))
                    val classification =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstantes.MED.COLUMNS.CLASSIFICATION))

                    val med = MedModel(id, name, classification == 1)
                    list.add(med)
                }
            }
            cursor.close()
        } catch (e: Exception) {
            return list
        }
        return list
    }

    fun getCrontrolled(): List<MedModel> {
        val list = mutableListOf<MedModel>()

        try {
            val db = medDataBase.readableDatabase
            val projection = arrayOf(
                DataBaseConstantes.MED.COLUMNS.ID,
                DataBaseConstantes.MED.COLUMNS.NAME,
                DataBaseConstantes.MED.COLUMNS.CLASSIFICATION
            )
            val cursor =
                db.rawQuery("SELECT id,name,classification FROM Med WHERE classification = 1", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstantes.MED.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstantes.MED.COLUMNS.NAME))
                    val classification =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstantes.MED.COLUMNS.CLASSIFICATION))

                    val med = MedModel(id, name, classification == 1)
                    list.add(med)
                }
            }
            cursor.close()
        } catch (e: Exception) {
            return list
        }
        return list
    }

    fun getNotControlled(): List<MedModel> {
        val list = mutableListOf<MedModel>()

        try {
            val db = medDataBase.readableDatabase
            val projection = arrayOf(
                DataBaseConstantes.MED.COLUMNS.ID,
                DataBaseConstantes.MED.COLUMNS.NAME,
                DataBaseConstantes.MED.COLUMNS.CLASSIFICATION
            )
            val cursor =
                db.rawQuery("SELECT id,name,classification FROM Med WHERE classification = 0", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstantes.MED.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstantes.MED.COLUMNS.NAME))
                    val classification =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstantes.MED.COLUMNS.CLASSIFICATION))

                    val med = MedModel(id, name, classification == 0)
                    list.add(med)
                }
            }
            cursor.close()
        } catch (e: Exception) {
            return list
        }
        return list
    }

    fun get(id: Int): MedModel? {
        var med: MedModel? = null
        try {
            val db = medDataBase.readableDatabase
            val projection = arrayOf(
                DataBaseConstantes.MED.COLUMNS.ID,
                DataBaseConstantes.MED.COLUMNS.NAME,
                DataBaseConstantes.MED.COLUMNS.CLASSIFICATION
            )

            val selection = "${DataBaseConstantes.MED.COLUMNS.ID} = ?"
            val args = arrayOf(id.toString())

            val cursor =
                db.query(
                    DataBaseConstantes.MED.TABLE_NAME,
                    projection,
                    selection,
                    args,
                    null,
                    null,
                    null
                )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstantes.MED.COLUMNS.NAME))
                    val classification =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstantes.MED.COLUMNS.CLASSIFICATION))

                    med = MedModel(id, name, classification == 1)
                }
            }
            cursor.close()
        } catch (e: Exception) {
            return med
        }
        return med
    }
}