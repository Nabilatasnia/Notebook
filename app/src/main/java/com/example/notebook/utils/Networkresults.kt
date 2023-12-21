package com.example.notebook.utils

sealed class Networkresults<T>(val data:T?=null,val message:String?=null) {
    class Success<T>(data:T):Networkresults<T>(data)
    class Error<T>(message: String?,data:T?=null):Networkresults<T>(data, message)
    class Loading<T>:Networkresults<T>()
}