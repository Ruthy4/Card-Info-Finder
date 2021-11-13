package com.example.cardinfofinder

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.cardinfofinder.domain.CardInfoModel
import com.example.cardinfofinder.repository.CardInfoRepository
import com.example.cardinfofinder.ui.CardInfoViewModel
import com.example.cardinfofinder.util.FakeCallResponse
import com.example.cardinfofinder.util.Resource
import com.example.cardinfofinder.util.Status
import com.example.cardinfofinder.util.getOrAwaitValue
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response

@ExperimentalCoroutinesApi
class CardInfoViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var cardInfoViewModel: CardInfoViewModel
    private lateinit var cardInfoRepository: CardInfoRepository
    private lateinit var observer: Observer<Resource<CardInfoModel>>

    private val responseInString = FakeCallResponse.CARD_INFO_RESPONSE
    private val gson = Gson()
    val cardInfoResponse: CardInfoModel = gson.fromJson(responseInString, CardInfoModel::class.java)
    val dispatcher = TestCoroutineDispatcher()


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Before
    fun setUpViewModel() {
        Dispatchers.setMain(dispatcher)

        cardInfoRepository = Mockito.mock(CardInfoRepository::class.java)
        cardInfoViewModel = CardInfoViewModel(cardInfoRepository)
    }


    fun setUpCallResponse(code: Int, body: CardInfoModel?, id: String) = runBlockingTest {
        var fakeCallResponse: Response<CardInfoModel>? = null

        fakeCallResponse = if (code == 200) {
            FakeCallResponse.fakeSuccessCall(code, body)
        } else {
            FakeCallResponse.fakeErrorCall(code)
        }
        Mockito.`when`(cardInfoRepository.getCardDetails(id))
            .thenReturn(fakeCallResponse)

        observer = Observer<Resource<CardInfoModel>> { }
        cardInfoViewModel.cardInfoLiveData.observeForever(observer)

    }

    @Test
    fun test_test_success() = runBlockingTest {
        setUpCallResponse(200, cardInfoResponse, "41874515")

        cardInfoViewModel.getCardDetails("41874515")
        val response = cardInfoViewModel.cardInfoLiveData.getOrAwaitValue()

        assertEquals(Status.SUCCESS, response.status)
    }

    @Test
    fun test_test_success_with_data() = runBlockingTest {
        setUpCallResponse(200, cardInfoResponse, "41874515")

        cardInfoViewModel.getCardDetails("41874515")
        val response = cardInfoViewModel.cardInfoLiveData.getOrAwaitValue()

        assertEquals("TELLER, A.S.", response.data?.bank?.name)
    }

    @Test
    fun test_test_error() = runBlockingTest {
        setUpCallResponse(500, cardInfoResponse, "41874515")
        cardInfoViewModel.getCardDetails("41874515")
        val response = cardInfoViewModel.cardInfoLiveData.getOrAwaitValue()
        assertEquals(Status.ERROR, response.status)
    }

}