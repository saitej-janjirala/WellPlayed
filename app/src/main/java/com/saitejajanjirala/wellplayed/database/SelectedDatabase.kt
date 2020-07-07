package com.saitejajanjirala.wellplayed.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Selected::class], version = 1, exportSchema = false)
abstract class SelectedDatabase:RoomDatabase() {

    abstract val selectedDao: SelectedDao
    companion object {
        @Volatile
        private var INSTANCE: SelectedDatabase? = null
        fun getInstance(context: Context): SelectedDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SelectedDatabase::class.java,
                        "selecteddatabase")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}