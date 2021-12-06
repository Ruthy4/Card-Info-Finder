package com.example.cardinfofinder.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.cardinfofinder.R
import com.example.cardinfofinder.databinding.CardInfoResultBottomSheetBinding
import com.example.cardinfofinder.domain.CardInfoModel
import com.example.cardinfofinder.util.SavedCardPreference
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CardInfoResultBottomSheet : BottomSheetDialogFragment() {
    private val cardInfoViewModel: CardInfoViewModel by activityViewModels()

    private var _binding: CardInfoResultBottomSheetBinding? = null
    private val binding get() =_binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CardInfoResultBottomSheetBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogStyle)
        observeCardInfo()

        binding.saveButton.setOnClickListener {
            cardInfoViewModel.cardInfoLiveData.observe(this, {
                SavedCardPreference.put(it, "KEY_CARD_INFO")
            })

        }
    }

    /*get card details from viewModel*/
    private fun observeCardInfo() {
        cardInfoViewModel.cardInfoLiveData.observe(this, {
            it.data.let { cardInfo ->
                binding.visaTextView.text = cardInfo?.type
                binding.bankNameTextView.text = cardInfo?.bank?.name
                binding.creditCardTextView.text = cardInfo?.brand
                binding.countryNameTextView.text = cardInfo?.country?.name
            }
        })
    }
}