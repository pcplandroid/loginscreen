package com.example.pagingsample.data.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagingsample.api.PlayerApi
import com.example.pagingsample.modal.Player
import com.example.pagingsample.utils.CONSTANTS

class PlayerDataSource(val playerApi: PlayerApi ) :PagingSource<Int,Player>() {
    override fun getRefreshKey(state: PagingState<Int, Player>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Player> {
        var page=params.key?:CONSTANTS.STARTING_PAGE_INDEX
        return try {
            var apiResult=playerApi.getPlayersList(params.loadSize,page)
            LoadResult.Page(
                data=apiResult.playerList,
                prevKey = if(page==CONSTANTS.STARTING_PAGE_INDEX) null else page-1,
                nextKey = if(page == apiResult.meta.total_pages) null else page+1
            )
        }catch (e:Exception){
            LoadResult.Error(Exception(e))
        }
    }
}