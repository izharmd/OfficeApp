package com.bws.officeapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Login")
data class LoginTable(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val emailID: String,
    val mobileNo: String,
    val password: String
)

@Entity(tableName = "User")
data class UserTable(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val name:String,
    val address:String
)
