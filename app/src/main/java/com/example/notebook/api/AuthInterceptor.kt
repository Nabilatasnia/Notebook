package com.example.notebook.api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() :Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request=chain.request().newBuilder()
        request.addHeader("Content-type","application/json; charset=UTF-8")
        return chain.proceed(request.build())
    }
}