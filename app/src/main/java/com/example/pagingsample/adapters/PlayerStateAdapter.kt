package com.example.pagingsample.adapters

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingState
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingsample.databinding.NetworkErrorBinding

class PlayerStateAdapter(val retry: () -> Unit) :
    LoadStateAdapter<PlayerStateAdapter.PlayerViewHolder>() {

    inner class PlayerViewHolder(var binding: NetworkErrorBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): PlayerViewHolder {
        return PlayerViewHolder(
            NetworkErrorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, loadState: LoadState) {
        val progressBar = holder.binding.progressBar
        val tvErrorMessage = holder.binding.tvErrorMsg
        val retryBtn = holder.binding.btnRetry

        if (loadState is LoadState.Loading) {
            progressBar.isVisible = true
            tvErrorMessage.isVisible = false
            retryBtn.isVisible = false
        } else {
            progressBar.isVisible = false
        }
        if (loadState is LoadState.Error) {
            progressBar.isVisible = false
            tvErrorMessage.isVisible = true
            tvErrorMessage.text = loadState.error.localizedMessage
            retryBtn.isVisible = true
        }
        retryBtn.setOnClickListener {
            retry.invoke()
        }
    }

}