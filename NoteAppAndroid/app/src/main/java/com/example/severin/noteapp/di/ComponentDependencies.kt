package com.example.severin.noteapp.di

import com.example.severin.noteapp.global.GlobalApplication
import com.example.severin.noteapp.global.State
import com.example.severin.noteapp.note.NoteActivity
import com.example.severin.noteapp.note.NoteDispatcher
import com.example.severin.noteapp.note.NoteEvent
import com.example.severin.noteapp.note.NoteViewModel
import dagger.Component
import io.reactivex.Observable
import io.reactivex.subjects.Subject
import javax.inject.Singleton

class ComponentDependencies {


  @Component(modules = arrayOf(GlobalModule::class))
  @Singleton
  interface GlobalComponent {
    fun exploseStateObs() : Observable<State>
    fun exploseStateSub() : Subject<State>
    fun exposeState() : State
    fun exposeListOfVm() : List<NoteViewModel>
    fun exposeEventSub() : Subject<NoteEvent>
//    fun exposeEventObs() : Observable<NoteEvent>
    fun exposeDispatcher() : NoteDispatcher

    fun inject(activity: NoteActivity)
  }

//  @Component(modules = arrayOf(NoteModule::class), dependencies = arrayOf(GlobalComponent::class))
//  @Singleton
//  interface NoteComponent {
//
//
//
//  }

}


