package com.saitejajanjirala.wellplayed.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SelectedDao {
    @Insert
    fun insert(selected: Selected)
    @Update
    fun update(selected: Selected)

    @Query("SELECT  * from selectedplayers ORDER BY id DESC ")
    fun getallselected():LiveData<List<Selected>>

    @Query("DELETE from selectedplayers WHERE id = :key")
    fun deleteselected(key:Long)

    @Query("SELECT * FROM selectedplayers ORDER BY id DESC LIMIT 1")
    fun gettop():Selected

    @Query("DELETE from selectedplayers")
    fun clear()

    @Query("SELECT COUNT(*) from selectedplayers")
    fun getcount():LiveData<Int>

    @Query("SELECT COUNT(*) from selectedplayers WHERE type=:playertype ")
    fun gettypecount(playertype:String):LiveData<Int>

    @Query("SELECT * from selectedplayers WHERE type=:playertype")
    fun getselectedbytype(playertype: String):LiveData<List<Selected>>
}