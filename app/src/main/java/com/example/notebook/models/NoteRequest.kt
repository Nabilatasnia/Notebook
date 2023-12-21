package com.example.notebook.models

data class NoteRequest(val title: String = "",
                       val body: String = "",
                       val userId: String = "")