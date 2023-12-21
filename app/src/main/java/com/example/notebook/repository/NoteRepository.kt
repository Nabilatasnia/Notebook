package com.example.notebook.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notebook.api.NotesAPI
import com.example.notebook.models.NoteRequest
import com.example.notebook.models.NoteResponse
import com.example.notebook.utils.Networkresults
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class NoteRepository @Inject constructor(private val notesAPI: NotesAPI){
    private val _notesLiveData=MutableLiveData<Networkresults<List<NoteResponse>>>()
    val notesLiveData:LiveData<Networkresults<List<NoteResponse>>>
        get()=_notesLiveData

    private val _statusLiveData=MutableLiveData<Networkresults<String>>()
    val statusLiveData: LiveData<Networkresults<String>>
        get()=_statusLiveData

    suspend fun getNotes(){
        _notesLiveData.postValue(Networkresults.Loading())
        val response=notesAPI.getNotes()
        if(response.isSuccessful && response.body()!=null){
            _notesLiveData.postValue(Networkresults.Success(response.body()!!))
        }
        else if(response.errorBody()!=null){
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _notesLiveData.postValue(Networkresults.Error(errorObj.getString(("message"))))
        }
        else{
            _notesLiveData.postValue(Networkresults.Error("Something went wrong"))
        }


    }

    suspend fun createNote(noteRequest: NoteRequest)
    {
        _statusLiveData.postValue(Networkresults.Loading())
        val response=notesAPI.createNote(noteRequest)
        handleResponse(response,"Note created")
    }



    suspend fun deleteNote(noteId:String)
    {
        _statusLiveData.postValue(Networkresults.Loading())
        val response=notesAPI.deleteNote(noteId)
        handleResponse(response,"Note Deleted")
    }
    suspend fun updateNote(noteId:String, noteRequest: NoteRequest)
    {
        _statusLiveData.postValue(Networkresults.Loading())
        val response = notesAPI.updateNote(noteId,noteRequest)
        handleResponse(response,"Note Updated")
    }
    private fun handleResponse(response: Response<NoteResponse>,message:String) {
        if (response.isSuccessful && response.body() != null) {
            _statusLiveData.postValue(Networkresults.Success(message))
        } else {
            _statusLiveData.postValue(Networkresults.Error("Something went wrong"))
        }
    }
}