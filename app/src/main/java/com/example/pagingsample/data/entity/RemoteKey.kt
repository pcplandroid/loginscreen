package com.example.pagingsample.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "RemoteKey")
data class RemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    val nextKey:Int,
    val isEndReached:Boolean
)
