package com.example.severin.noteapp.di

import com.example.severin.noteapp.global.Store
import com.example.severin.noteapp.global.State
import com.example.severin.noteapp.note.*
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import javax.inject.Singleton


@Module
class NoteModule {

  @Provides
  @Singleton
  fun providesNoteDispatcher(eventSub: Subject<NoteEvent>) : NoteDispatcher {
    return NoteDispatcher(eventSub)
  }

  @Provides
  @Singleton
  fun providesNoteEventSub() : Subject<NoteEvent> {
    return PublishSubject.create()
  }

  @Provides
  @Singleton
  fun providesNoteEventObs(eventSub: Subject<NoteEvent>) : Observable<NoteEvent> {
    return eventSub.hide()
  }

}
