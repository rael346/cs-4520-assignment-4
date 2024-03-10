package com.cs4520.assignment4.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class OfflineDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var Instance: OfflineDatabase? = null

        fun getDatabase(context: Context): OfflineDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    OfflineDatabase::class.java,
                    "offline_database"
                )
                    // TODO: this is considered bad practice but couldn't find a good way to
                    // do retrofit with room database
                    .allowMainThreadQueries()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}