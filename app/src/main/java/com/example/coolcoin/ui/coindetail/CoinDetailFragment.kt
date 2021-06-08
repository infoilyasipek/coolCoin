package com.example.coolcoin.ui.coindetail

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.coolcoin.R
import com.example.coolcoin.databinding.FragmentCoinDetailBinding
import com.example.coolcoin.di.ViewModelFactory
import com.example.coolcoin.util.UserManager
import com.example.coolcoin.util.extensions.compactColor
import com.example.coolcoin.util.extensions.compactDrawable
import com.example.domain.models.CoinDetail
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CoinDetailFragment : Fragment(), View.OnClickListener {

    @Inject
    lateinit var userManager: UserManager

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: CoinDetailViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentCoinDetailBinding

    private val args by navArgs<CoinDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinDetailBinding.inflate(inflater, container, false).also {
            it.handler = this
            it.coin = args.coin
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCoinDetail(args.coin.id)
        viewModel.getIsFavoriteCoin(args.coin.id)

        observeData()
    }

    private fun observeData() {
        viewModel.coinDetail.observe(viewLifecycleOwner) {
            setCoinDetail(it)
        }

        viewModel.isFavoriteCoin.observe(viewLifecycleOwner) {
            binding.imageViewAddToFavorites.imageTintList = ColorStateList.valueOf(
                compactColor(if (it) R.color.gold else R.color.black)
            )
            binding.imageViewAddToFavorites.setImageDrawable(
                compactDrawable(if (it) R.drawable.ic_star_24 else R.drawable.ic_star_outline_24)
            )
        }
    }

    private fun setCoinDetail(coinDetail: CoinDetail) {
        binding.coinDetail = coinDetail
        with(binding) {
            if (coinDetail.priceChangePercentageIn24H > 0) {
                textViewPriceChangePercentage.setTextColor(compactColor(R.color.green))
                imageViewPriceArrow.imageTintList =
                    ColorStateList.valueOf(compactColor(R.color.green))
                imageViewPriceArrow.setImageDrawable(compactDrawable(R.drawable.ic_arrow_drop_up_24))
            } else {
                textViewPriceChangePercentage.setTextColor(compactColor(R.color.red))
                imageViewPriceArrow.imageTintList =
                    ColorStateList.valueOf(compactColor(R.color.red))
                imageViewPriceArrow.setImageDrawable(compactDrawable(R.drawable.ic_arrow_drop_down_24))
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imageViewBack -> {
                findNavController().popBackStack()
            }
            R.id.imageViewAddToFavorites -> {
                if (userManager.isSignedIn) {
                    viewModel.updateFavoriteStatus(args.coin)
                } else {
                    findNavController().navigate(R.id.action_coinDetailFragment_to_registerFragment)
                }
            }
        }
    }
}
