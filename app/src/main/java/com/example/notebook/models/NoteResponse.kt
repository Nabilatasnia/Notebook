package com.example.notebook.models

data class NoteResponse(val id: Int = 0,
                        val title: String = "",
                        val body: String = "",
                        val userId: Int = 0)