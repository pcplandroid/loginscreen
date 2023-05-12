package com.example.pagingsample.utils

import androidx.room.TypeConverter
import com.example.pagingsample.modal.Team
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class TeamConverter {

    private val gson = Gson()

    private val type: Type = object : TypeToken<Team?>() {}.type

    @TypeConverter
    fun from(team: Team?): String? {
        if (team == null) {
            return null
        }
        return gson.toJson(team, type)
    }


    @TypeConverter
    fun to(teamString: String?): Team? {
        if (teamString == null) {
            return null
        }
        return gson.fromJson(teamString, type)
    }
}