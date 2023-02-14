package com.example.notesapp.Models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notesapp.Database.NotesDatabase
import com.example.notesapp.Database.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application){

    private val repository: NotesRepository

    val allnotes: LiveData<List<Note>>

    init{
        val dao = NotesDatabase.getDatabase(application).getNoteDao()
        repository  = NotesRepository(dao)
        allnotes  = repository.allNotes
    }

    fun deleteNote(note:Note) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(note)
    }
    fun insertNote(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
    fun updateNote(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }


}