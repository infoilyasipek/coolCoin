package com.example.coolcoin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.coolcoin.R
import com.example.coolcoin.databinding.FragmentHomeBinding
import com.example.coolcoin.di.ViewModelFactory
import com.example.coolcoin.util.UserManager
import com.example.coolcoin.util.extensions.*
import com.example.domain.models.Coin
import com.example.domain.models.Status.*
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(), View.OnClickListener {

    @Inject
    lateinit var userManager: UserManager

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val homeViewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentHomeBinding

    private val searchResultsAdapter: SearchResultsAdapter by lazy {
        SearchResultsAdapter(object : SearchResultsAdapter.OnSearchResultClickListener {
            override fun onSearchResultClicked(coin: Coin) {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToCoinDetailFragment(
                        coin
                    )
                )
            }

            override fun onAddToFavoritesClicked(coin: Coin) {
                toast("This feature is for premium users, please hire me to access it :D")
            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.fetchCoins()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false).also {
            it.handler = this
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        observeData()
    }

    private fun initViews() = with(binding) {

        recyclerViewSearchResults.adapter = searchResultsAdapter

        editTextSearch.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard()
                return@OnEditorActionListener true
            }
            false
        })

        editTextSearch.doOnTextChanged { text, _, _, _ ->
            performSearch()
            imageViewClearSearchText.isVisible = !text.isNullOrBlank()
            layoutSearchAnimation.isVisible = text.isNullOrBlank()
            if (text.isNullOrBlank()) {
                lottieSearchAnimation.playAnimation()
            }
        }
    }

    private fun performSearch() {
        homeViewModel.searchCoins(binding.editTextSearch.text())
    }

    private fun observeData() {
        homeViewModel.fetchCoinsStatus.observe(viewLifecycleOwner) {
            with(binding) {
                progressBarLoading.isVisible = it == LOADING
                layoutError.isVisible = it == ERROR
                layoutSearch.isVisible = it == SUCCESS
                layoutSearchAnimation.isVisible = it == SUCCESS
            }
        }

        homeViewModel.searchResults.observe(viewLifecycleOwner) {
            searchResultsAdapter.submitList(it)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imageViewClearSearchText -> {
                binding.editTextSearch.clearText()
            }
            R.id.layoutError -> {
                homeViewModel.fetchCoins()
            }
            R.id.imageViewFavoriteCoins -> {
                if (userManager.isSignedIn) {
                    findNavController().navigate(R.id.action_homeFragment_to_favoriteCoinsFragment)
                } else {
                    findNavController().navigate(R.id.action_homeFragment_to_registerFragment)
                }
            }
        }
    }

}
