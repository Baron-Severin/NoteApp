package com.example.severin.noteapp

data class NoteState(val notes: List<NoteViewModel>)

data class NoteViewModel(var title : String, var content: String)
