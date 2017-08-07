package com.example.severin.noteapp.di

import com.example.severin.noteapp.global.State
import com.example.severin.noteapp.global.Store
import com.example.severin.noteapp.note.NoteEvent
import com.example.severin.noteapp.note.NoteReducer
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import javax.inject.Singleton

@Module
class GlobalModule(val state: State) {

  @Provides
  fun providesState() : State {
    return state
  }

  @Provides
  @Singleton
  fun providesStore(stateSub: Subject<State>, eventObs: Observable<NoteEvent>, reducer: NoteReducer, state: State) : Store {
    return Store(stateSub, eventObs, reducer, state)
  }

  @Provides
  @Singleton
  fun providesReducer() : NoteReducer {
    return NoteReducer()
  }

  @Provides
  @Singleton
  fun providesStateSub() : Subject<State> {
    return BehaviorSubject.create()
  }

  @Provides
  fun providesStateObs(stateSub: Subject<State>) : Observable<State> {
    return stateSub.hide()
  }

}


//companion object {
//  private val store: Store
//  private val dispatcher: NoteDispatcher
//  private val stateObs : Observable<State>
//
//  init {
//    val eventSub: Subject<NoteEvent> = PublishSubject.create()
//    val reducer = NoteReducer()
//    val stateSub: Subject<State> = BehaviorSubject.create()
//    dispatcher = NoteDispatcher(eventSub)
//    store = Store(stateSub, eventSub.hide(), reducer, State(listOf(NoteViewModel(title = "Title", content = "Content", dispatcher = dispatcher))))
//    stateObs = stateSub.hide()
//  }
//}