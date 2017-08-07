package com.example.severin.noteapp.global

import com.example.severin.noteapp.note.NoteEvent
import com.example.severin.noteapp.note.NoteReducer
import io.reactivex.Observable
import io.reactivex.subjects.Subject

class Store(private val stateSub: Subject<State>,
            private val eventObs: Observable<NoteEvent>,
            private val reducer: NoteReducer,
            private var state : State) {

  init {
    eventObs.subscribe {
      println("SEVTEST: former state: $state")
      state = reducer.reduce(state, it)
      println("SEVTEST: new state: $state")
      stateSub.onNext( state )
    }
    stateSub.onNext(state)
  }

}



