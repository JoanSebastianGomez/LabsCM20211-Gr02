package co.edu.udea.compumovil.gr02_20211.lab2.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class UserEntity(
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "password")
    val password: String,
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}