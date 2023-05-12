package com.example.pagingsample.data.remotemediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.pagingsample.api.PlayerApi
import com.example.pagingsample.data.db.PlayerDatabase
import com.example.pagingsample.data.entity.RemoteKey
import com.example.pagingsample.modal.Player
import com.example.pagingsample.utils.CONSTANTS

@OptIn(ExperimentalPagingApi::class)
class PlayerRemoteMediator(val playerDatabase: PlayerDatabase, val playerApi: PlayerApi) :
    RemoteMediator<Int, Player>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Player>): MediatorResult {
        var key = when (loadType) {
            LoadType.REFRESH -> {
                if (playerDatabase.getPlayerDao().getCount() > 0) return MediatorResult.Success(
                    endOfPaginationReached = false
                )
                null
            }
            LoadType.PREPEND -> {
                return MediatorResult.Success(endOfPaginationReached = true)
            }
            LoadType.APPEND -> getKey()
        }

        try {
            if (key != null) {
                if (key.isEndReached) return MediatorResult.Success(true)
            }

            val page = key?.nextKey ?: CONSTANTS.STARTING_PAGE_INDEX
            val result = playerApi.getPlayersList(state.config.pageSize, page)
            val playerList = result.playerList

            val endOfPage =
                result.meta.next_page == null || result.meta.current_page == result.meta.total_pages
            playerDatabase.withTransaction {
                val nextPage = page + 1
                playerDatabase.getRemoteDao().insertKey(
                    RemoteKey(0, nextPage, endOfPage)

                )
                playerDatabase.getPlayerDao().insertPlayers(playerList)

            }
            return MediatorResult.Success(endOfPage)

        } catch (e: Exception) {
            return MediatorResult.Error(Exception(e))
        }

    }

    suspend fun getKey(): RemoteKey? {
        return playerDatabase.getRemoteDao().getKeys().lastOrNull()
    }
}