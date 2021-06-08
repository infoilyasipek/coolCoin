package com.example.coolcoin.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coolcoin.R
import com.example.coolcoin.databinding.ItemSearchResultBinding
import com.example.domain.models.Coin

class SearchResultsAdapter(
    val onSearchResultClickListener: OnSearchResultClickListener
) : ListAdapter<Coin, SearchResultsAdapter.SearchResultViewHolder>(SearchResultDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding =
            ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SearchResultViewHolder(
        private val binding: ItemSearchResultBinding
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            binding.handler = this
        }

        fun bind(coin: Coin) {
            binding.coin = coin
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.layoutResultContainer -> {
                    onSearchResultClickListener.onSearchResultClicked(getItem(adapterPosition))
                }
                R.id.imageViewAddToFavorites -> {
                    onSearchResultClickListener.onAddToFavoritesClicked(getItem(adapterPosition))
                }
            }
        }
    }

    class SearchResultDiffCallBack : DiffUtil.ItemCallback<Coin>() {
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem == newItem
        }
    }

    interface OnSearchResultClickListener {

        fun onSearchResultClicked(coin: Coin)

        fun onAddToFavoritesClicked(coin: Coin)

    }
}
