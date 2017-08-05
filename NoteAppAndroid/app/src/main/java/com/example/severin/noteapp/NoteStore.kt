package com.example.severin.noteapp

import io.reactivex.Observable
import io.reactivex.subjects.Subject

class NoteStore(val stateSub: Subject<NoteState>, val eventObs: Observable<Event>, val reducer: NoteReducer) {
  var state: NoteState

  init {
    //TODO: Hook this up to a DB or network call
    state = NoteState(listOf())
  }
}



