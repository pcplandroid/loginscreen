package com.example.pagingsample.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingsample.R
import com.example.pagingsample.databinding.AdapterItemBinding
import com.example.pagingsample.modal.Player

class PlayerAdapter(private val clicked: (String) -> Unit) :
    PagingDataAdapter<Player, PlayerAdapter.PlayerViewHolder>(COMPARATOR) {


    inner class PlayerViewHolder(var binding: AdapterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(player: Player) {

            binding.let {
                it.tvName.text = it.root.context.getString(
                    R.string.name,
                    "Name", player.first_name
                )
                it.tvLastName.text = it.root.context.getString(
                    R.string.name,
                    "Last Name", player.last_name
                )
                it.tvTeam.text = it.root.context.getString(
                    R.string.name,
                    "Team ", player.team.city
                )
                it.tvPosition.text = it.root.context.getString(
                    R.string.position,
                    "Position", player.position
                )

                it.root.setOnClickListener {
                    clicked.invoke(player.first_name)
                }
            }
        }
    }

    companion object {
        var COMPARATOR = object : DiffUtil.ItemCallback<Player>() {

            override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
                return oldItem.id == newItem.id

            }

            override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {


        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(
            AdapterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}