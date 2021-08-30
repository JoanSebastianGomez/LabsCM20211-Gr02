package co.edu.udea.compumovil.gr02_20211.lab2.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import co.edu.udea.compumovil.gr02_20211.lab2.data.database.entity.PlaceEntity

@Dao
interface PlaceDao {

    @Query("Select * from placeentity")
    fun getEntities() : List<PlaceEntity>

    @Delete
    fun deleteEntity(entity: PlaceEntity)

    @Insert
    fun insert(vararg entries: PlaceEntity)
}