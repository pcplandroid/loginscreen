package com.example.pagingsample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.pagingsample.R
import com.example.pagingsample.adapters.PlayerAdapter
import com.example.pagingsample.adapters.PlayerStateAdapter
import com.example.pagingsample.databinding.ActivityMainBinding
import com.example.pagingsample.viewmodel.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val playerViewModel: PlayerViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    private val playerAdapter = PlayerAdapter({ name -> Log.d("TAG", "$name: ") })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpAdapter()
        setUpObserver()
        binding.srPlayers.setOnRefreshListener {
            playerAdapter.refresh()
        }
    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            playerViewModel.getPlayerList().collect() {
                playerAdapter.submitData(it)
            }
        }
    }

    private fun setUpAdapter() {
        binding.rvPlayers.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = playerAdapter.withLoadStateFooter(PlayerStateAdapter { retry() })
        }
        playerAdapter.addLoadStateListener { loadState ->
            if (loadState.mediator?.refresh is LoadState.Loading) {
                if (playerAdapter.snapshot().isEmpty()) {
                    binding.pbLoading.isVisible = true
                }
                binding.tvError.isVisible = false
            } else {
                binding.pbLoading.isVisible = false
                binding.srPlayers.isRefreshing = false
                var error = when {
                    loadState.mediator?.refresh is LoadState.Error -> loadState.mediator?.refresh as LoadState.Error
                    loadState.mediator?.prepend is LoadState.Error -> loadState.mediator?.prepend as LoadState.Error
                    loadState.mediator?.append is LoadState.Error -> loadState.mediator?.append as LoadState.Error
                    else -> null
                }
                error?.let {
                    if(playerAdapter.snapshot().isEmpty()){
                        binding.tvError.isVisible = true
                        binding.tvError.text = it.error.localizedMessage
                    }

                }
            }

        }
    }

    fun retry() {
        playerAdapter.retry()
    }
}