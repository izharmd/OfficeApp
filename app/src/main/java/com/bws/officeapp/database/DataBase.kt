package com.bws.officeapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LoginTable::class,UserTable::class], version = 2)
abstract class DataBase : RoomDatabase() {
    abstract fun loginDao(): LoginDAO
    abstract fun  userDao(): UserDAO

}