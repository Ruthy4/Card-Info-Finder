package com.example.cardinfofinder.util

//sealed class Resource<T>(
//    val data: T? = null,
//    val errorBody: Response<Any>? = null,
//    val isNetworkError: Boolean? = null,
//    val message: String? = null,
//) {
//    class Success<T>(data: T, message: String? = "Success") : Resource<T>(data, message = message)
//    class Loading<T>(data: T? = null, message: String? = null) : Resource<T>(data)
//    class Error<T>(
//        isNetworkError: Boolean?,
//        errorBody: Response<Any>?,
//        data: T? = null,
//        message: String? = "Errorrrrrrr"
//    ) : Resource<T>(data, errorBody, isNetworkError, message)
//}

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message:String?
){
    companion object{

        fun <T> success(data:T?): Resource<T>{
            return Resource(Status.SUCCESS, data, "Successful")
        }

        fun <T> error(msg:String, data:T?): Resource<T>{
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data:T?): Resource<T>{
            return Resource(Status.LOADING, data, null)
        }

    }
}