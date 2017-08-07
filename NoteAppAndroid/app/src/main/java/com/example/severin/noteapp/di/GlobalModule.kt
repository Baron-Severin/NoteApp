package com.example.severin.noteapp.di

import com.example.severin.noteapp.global.State
import com.example.severin.noteapp.global.Store
import com.example.severin.noteapp.note.*
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import javax.inject.Singleton

@Module
class GlobalModule() {

  @Provides
  @Singleton
  fun providesDefaultListOfVm(dispatcher: NoteDispatcher) : List<NoteViewModel> {
    return listOf(NoteViewModel(title = "Title", content = "Content", dispatcher = dispatcher))
  }

  @Provides
  @Singleton
  fun providesNoteDispatcher(eventSub: Subject<NoteEvent>) : NoteDispatcher {
    return NoteDispatcher(eventSub)
  }

  @Provides
  @Singleton
  fun providesState(list: List<NoteViewModel>) : State {
    return State(list)
  }

  @Provides
  @Singleton
  fun providesStore(stateSub: Subject<State>, eventObs: Subject<NoteEvent>, reducer: NoteReducer, state: State) : Store {
    return Store(stateSub, eventObs.hide(), reducer, state)
  }

  @Provides
  @Singleton
  fun providesReducer() : NoteReducer {
    return NoteReducer()
  }

  @Provides
  @Singleton
  fun providesStateSub(state : State) : Subject<State> {
    return BehaviorSubject.createDefault(state)
  }

  @Provides
  @Singleton
  fun providesStateObs(stateSub: Subject<State>) : Observable<State> {
    return stateSub.hide()
  }

  @Provides
  @Singleton
  fun providesNoteEventSub() : Subject<NoteEvent> {
    return PublishSubject.create()
  }

//  @Provides
//  @Singleton
//  fun providesNoteEventObs(eventSub: Subject<NoteEvent>) : Observable<NoteEvent> {
//    return eventSub.hide()
//  }

}
