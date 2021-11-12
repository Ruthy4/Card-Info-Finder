package com.example.cardinfofinder.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardinfofinder.domain.CardInfoModel
import com.example.cardinfofinder.repository.CardInfoRepository
import com.example.cardinfofinder.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardInfoViewModel @Inject constructor(private val cardInfoRepository: CardInfoRepository) :
    ViewModel() {
    private var _cardInfoMutableLiveData = MutableLiveData<Resource<CardInfoModel>>()
    val cardInfoLiveData: LiveData<Resource<CardInfoModel>> get() = _cardInfoMutableLiveData


    /*function to get the details of the card*/
    fun getCardDetails(id:String)  = viewModelScope.launch {

        _cardInfoMutableLiveData.postValue(Resource.loading(null))
        cardInfoRepository.getCardDetails(id).let {

            if (it.isSuccessful){
                _cardInfoMutableLiveData.postValue(Resource.success(it.body()))
            }else{
                _cardInfoMutableLiveData.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }
}