package com.example.pagingsample.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pagingsample.modal.Player
import com.example.pagingsample.modal.Players


@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayers(playersList:List<Player>)

    @Query("select * from player")
     fun getPlayers():PagingSource<Int,Player>

     @Query("select count(id) from player")
     suspend fun getCount():Int
}