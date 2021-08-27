package co.edu.udea.compumovil.gr02_20211.lab2.data.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.edu.udea.compumovil.gr02_20211.lab2.data.database.dao.PlaceDao
import co.edu.udea.compumovil.gr02_20211.lab2.data.database.entity.PlaceEntity

@Database(entities = [PlaceEntity::class], version = 1, exportSchema = false)
abstract class JapanDatabase : RoomDatabase() {
    abstract fun placeDao(): PlaceDao

    companion object {
        fun getInstance(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            JapanDatabase::class.java,
            "Japan_Database"
        ).build()
    }
}