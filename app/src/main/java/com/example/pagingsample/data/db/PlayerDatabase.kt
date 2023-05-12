package com.example.pagingsample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pagingsample.data.dao.PlayerDao
import com.example.pagingsample.data.dao.RemoteKeyDao
import com.example.pagingsample.data.entity.RemoteKey
import com.example.pagingsample.modal.Player


@Database(entities = [Player::class,RemoteKey::class], version = 1)
abstract  class PlayerDatabase  : RoomDatabase(){

    abstract fun getPlayerDao():PlayerDao
    abstract fun getRemoteDao():RemoteKeyDao
}