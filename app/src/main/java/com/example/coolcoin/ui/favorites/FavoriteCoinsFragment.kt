package com.example.coolcoin.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.coolcoin.R
import com.example.coolcoin.databinding.FragmentFavoriteCoinsBinding
import com.example.coolcoin.di.ViewModelFactory
import com.example.coolcoin.ui.home.SearchResultsAdapter
import com.example.coolcoin.util.extensions.toast
import com.example.domain.models.Coin
import com.example.domain.models.Status.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteCoinsFragment : Fragment(), View.OnClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: FavoriteCoinsViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentFavoriteCoinsBinding

    private val favoriteCoinsAdapter: SearchResultsAdapter by lazy {
        SearchResultsAdapter(object : SearchResultsAdapter.OnSearchResultClickListener {
            override fun onSearchResultClicked(coin: Coin) {
                findNavController().navigate(
                    FavoriteCoinsFragmentDirections.actionFavoriteCoinsFragmentToCoinDetailFragment(
                        coin
                    )
                )
            }

            override fun onAddToFavoritesClicked(coin: Coin) {
                toast("This feature is for premium users, please hire me to access it :D")
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteCoinsBinding.inflate(inflater, container, false).also {
            it.handler = this
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFavoriteCoins()

        initViews()

        observeData()
    }

    private fun initViews() {
        binding.recyclerViewFavorites.adapter = favoriteCoinsAdapter
    }

    private fun observeData() {
        viewModel.favoriteCoins.observe(viewLifecycleOwner) {
            binding.progressBarLoading.isVisible = it.status == LOADING
            when (it.status) {
                SUCCESS -> {
                    binding.textViewEmpty.isVisible = it.data.isNullOrEmpty()
                    favoriteCoinsAdapter.submitList(it.data)
                }
                ERROR -> {
                    toast("Something went wrong")
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imageViewBack -> {
                findNavController().popBackStack()
            }
        }
    }
}
