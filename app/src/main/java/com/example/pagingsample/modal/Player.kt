package com.example.pagingsample.modal

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.pagingsample.utils.TeamConverter



@TypeConverters(TeamConverter::class)
@Entity(tableName = "Player")
data class Player(
    val first_name: String,
    val height_feet: Int,
    val height_inches: Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val last_name: String,
    val position: String,
    val team: Team,
    val weight_pounds: Int
)