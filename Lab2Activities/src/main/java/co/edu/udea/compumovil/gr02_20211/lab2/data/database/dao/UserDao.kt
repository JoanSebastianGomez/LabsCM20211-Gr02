package co.edu.udea.compumovil.gr02_20211.lab2.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import co.edu.udea.compumovil.gr02_20211.lab2.data.database.entity.UserEntity

@Dao
interface UserDao {

    @Query("Select * from userentity")
    fun getEntities() : List<UserEntity>

    @Delete
    fun deleteEntity(entity: UserEntity)

    @Insert
    fun insert(vararg entries: UserEntity)
}