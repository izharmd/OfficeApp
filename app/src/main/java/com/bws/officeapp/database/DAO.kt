/*
package com.bws.officeapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LoginDAO {

    @Insert
    suspend fun insertUserDetails(loginTable: LoginTable)

    @Update
    suspend fun updateUserDetails(loginTable: LoginTable)

    @Delete
    suspend fun deleteUserDetails(loginTable: LoginTable)

    @Query("SELECT * FROM login")
    fun getUserDetails(): LiveData<List<LoginTable>>
}

@Dao
interface UserDAO {

    @Insert
    fun insetUser(userTable: UserTable)

    @Update
    fun updateUser(userTable: UserTable)

    @Delete
    fun deleteUser(userTable: UserTable)

    @Query("SELECT * FROM User")
    fun getUser(): LiveData<List<UserTable>>

}*/
