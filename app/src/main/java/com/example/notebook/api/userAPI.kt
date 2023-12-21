package com.example.notebook.api

import com.example.notebook.models.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface userAPI {
    @POST("/users")
    suspend fun signup(@Body userResponse:UserResponse):Response<UserResponse>

    @POST("/users")
    suspend fun signin(@Body userResponse:UserResponse):Response<UserResponse>

}