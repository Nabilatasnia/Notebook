package com.example.notebook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notebook.models.NoteRequest
import com.example.notebook.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository): ViewModel()  {

    val notesLiveData get() = noteRepository.notesLiveData
    val statusLiveData get() = noteRepository.statusLiveData
    fun getNote()
    {
        viewModelScope.launch {
            noteRepository.getNotes()
        }
    }
    fun createNote(noteRequest: NoteRequest)
    {
        viewModelScope.launch {
            noteRepository.createNote(noteRequest)
        }
    }

    fun updateNote(noteId:String, noteRequest: NoteRequest){
        viewModelScope.launch {
            noteRepository.updateNote(noteId,noteRequest)
        }
    }
    fun deleteNote(noteId:String){
        viewModelScope.launch {
            noteRepository.deleteNote(noteId)
        }
    }
}