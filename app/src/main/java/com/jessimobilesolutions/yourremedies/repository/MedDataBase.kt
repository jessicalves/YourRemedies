package com.jessimobilesolutions.yourremedies.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.jessimobilesolutions.yourremedies.constantes.DataBaseConstantes

class MedDataBase(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {
    companion object {
        private const val NAME = "remedydb"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        //Criacao do banco - chama uma unica vez. depois que já é criando, esse metodo nao é mais chamado

        db.execSQL(
            "CREATE TABLE ${DataBaseConstantes.MED.TABLE_NAME}(${DataBaseConstantes.MED.COLUMNS.ID} integer primary key autoincrement, " +
                    "${DataBaseConstantes.MED.COLUMNS.NAME} text, " +
                    "${DataBaseConstantes.MED.COLUMNS.CLASSIFICATION} integer);"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }
}