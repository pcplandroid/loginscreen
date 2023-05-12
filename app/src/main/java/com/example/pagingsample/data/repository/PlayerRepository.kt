package com.example.pagingsample.data.repository

import androidx.paging.*
import com.example.pagingsample.api.PlayerApi
import com.example.pagingsample.data.dataSource.PlayerDataSource
import com.example.pagingsample.data.db.PlayerDatabase
import com.example.pagingsample.data.remotemediator.PlayerRemoteMediator
import com.example.pagingsample.modal.Player
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow
import javax.inject.Inject

class PlayerRepository @Inject constructor(val playerApi: PlayerApi,val playerDatabase: PlayerDatabase) {


     @OptIn(ExperimentalPagingApi::class)
     fun getPlayerList():kotlinx.coroutines.flow.Flow<PagingData<Player>>{
        return Pager(
            config = PagingConfig(pageSize = NETWROK_PAGE_SIZE),
            remoteMediator = PlayerRemoteMediator(playerDatabase,playerApi),
            pagingSourceFactory = {playerDatabase.getPlayerDao().getPlayers()}
        ).flow
    }

    companion object {
        val NETWROK_PAGE_SIZE=25
    }
}

