package com.jessimobilesolutions.yourremedies.constantes

class DataBaseConstantes private constructor() {
    object MED {
        const val ID = "medid"
        const val TABLE_NAME = "Med"

        object COLUMNS {
            const val ID = "id"
            const val NAME = "name"
            const val CLASSIFICATION = "classification"
        }
    }
}