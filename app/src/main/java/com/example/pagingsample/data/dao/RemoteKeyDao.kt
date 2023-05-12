package com.example.pagingsample.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pagingsample.data.entity.RemoteKey
import com.example.pagingsample.modal.Player

@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKeys(remote: List<RemoteKey>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKey(remote: RemoteKey)

    @Query("select * from RemoteKey where id =:id")
    suspend fun remoteKeyID(id: Int): RemoteKey

    @Query("select * from RemoteKey")
    suspend fun getKeys(): List<RemoteKey>


}