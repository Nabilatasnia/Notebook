package com.example.notebook.api

import com.example.notebook.models.NoteRequest
import com.example.notebook.models.NoteResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface NotesAPI {
    @GET("/posts")
    suspend fun getNotes(): Response<List<NoteResponse>>
    @POST("/posts")
    suspend fun createNote(@Body noteRequest: NoteRequest):Response<NoteResponse>

    @PUT("/posts/{noteId}")
    suspend fun updateNote(@Path("noteId") noteID: String,@Body noteRequest: NoteRequest):Response<NoteResponse>

    @DELETE("/note/{noteId}")
    suspend fun deleteNote(noteID: String):Response<NoteResponse>



}