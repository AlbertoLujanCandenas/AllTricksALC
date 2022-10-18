package net.azarquiel.examenalltricksalc.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Bici::class, Marca::class], version = 1)
abstract class AllTricksDB: RoomDatabase() {
    abstract fun BiciDao(): BiciDao
    abstract fun MarcaDao(): MarcaDao
    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE:  AllTricksDB? = null

        fun getDatabase(context: Context): AllTricksDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AllTricksDB::class.java,"alltricks.sqlite"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}