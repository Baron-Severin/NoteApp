package com.example.severin.noteapp.note

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import com.example.severin.noteapp.R
import com.example.severin.noteapp.di.*
import com.example.severin.noteapp.global.GlobalApplication
import com.example.severin.noteapp.global.Store
import com.example.severin.noteapp.global.State
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*
import javax.inject.Inject


class NoteActivity : AppCompatActivity() {
  
  @Inject lateinit var dispatcher: NoteDispatcher
  @Inject lateinit var stateObs : Observable<State>
  @Inject lateinit var stateSub : Subject<State>
  @Inject lateinit var introState : State
  @Inject lateinit var store: Store

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)

    val globApp = application as GlobalApplication
    globApp.globalComponent.inject(this)

    rv_notes.adapter = NoteAdapter(stateObs, dispatcher)
    rv_notes.layoutManager = LinearLayoutManager(this)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }
}


