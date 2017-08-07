package com.example.severin.noteapp.note

abstract class NoteEvent

data class TitleChangedNoteEvent(val id : String, val newTitle: String) : NoteEvent()



