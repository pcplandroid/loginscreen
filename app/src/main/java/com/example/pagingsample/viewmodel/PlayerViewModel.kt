package com.example.pagingsample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pagingsample.data.repository.PlayerRepository
import com.example.pagingsample.modal.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(val playerRepository: PlayerRepository) : ViewModel() {
    fun getPlayerList(): Flow<PagingData<Player>> {

        return playerRepository.getPlayerList().cachedIn(viewModelScope)
    }
}