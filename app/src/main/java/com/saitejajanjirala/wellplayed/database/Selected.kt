package com.saitejajanjirala.wellplayed.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.FieldPosition
@Entity(tableName="selectedplayers")
data class Selected(
    @PrimaryKey
    var id:Long=System.currentTimeMillis(),
    @ColumnInfo(name="name")
    var name:String?=null,
    @ColumnInfo(name="team")
    var team:String?=null,
    @ColumnInfo(name="type")
    var type:String?=null
)
