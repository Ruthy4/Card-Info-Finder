package com.example.cardinfofinder.util

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import retrofit2.Response

object FakeCallResponse {
    private val emptyResponseBody: ResponseBody =
        ResponseBody.create("application/json".toMediaTypeOrNull(), "");

    fun <T> fakeSuccessCall(code: Int, body: T?): Response<T> {
        val call = Calls.FakeCall(Response.success(code, body), null)
        return call.execute()
    }

    fun <T> fakeErrorCall(code: Int): Response<T> {
        val call = Calls.FakeCall<T>(Response.error(code, emptyResponseBody), null)
        return call.execute()
    }

    const val CARD_INFO_RESPONSE =
        """
        {"number":
            {
                "length":16,
                "luhn":true
            },
            "scheme":"visa",
            "type":"debit",
            "brand":"Traditional",
            "prepaid":false,
            "country": { 
            "numeric": "566",
            "alpha2": "NG", 
            "name": "Nigeria",
            "emoji":"🇳🇬",
            "currency":"NGN",
            "latitude":10,
            "longitude":8},
            "bank": {
                "name":"TELLER, A.S.",
                "url":"www.teller.no",
                "phone":"815 00 400"
                }
                }
        """
}