package com.example.severin.noteapp.note

import com.example.severin.noteapp.global.State

class NoteReducer() {

  fun reduce(oldState: State, noteEvent: NoteEvent) : State {
    when(noteEvent) {
      is TitleChangedNoteEvent -> return updateTitle(oldState, noteEvent)
      else -> throw RuntimeException("Reducer passed NoteEvent of type it does not understand: $noteEvent")
    }
  }


  fun updateTitle(oldState: State, event: TitleChangedNoteEvent) : State {
    val noteList = oldState.notes
    val id = event.id
    var note : NoteViewModel? = null
    var index = -1
    for ((i, value) in noteList.withIndex()) {
      if (value.id == id) {
        note = value
        index = i
        break
      }
    }
    if (note == null) throw RuntimeException("Reducer is unable to update Note: ID not found in state")
    val newList = noteList.toMutableList()
    newList.set(index, NoteViewModel(title = event.newTitle, content = note.content, dispatcher = note.dispatcher, id = note.id))
    return State(newList)
  }

}