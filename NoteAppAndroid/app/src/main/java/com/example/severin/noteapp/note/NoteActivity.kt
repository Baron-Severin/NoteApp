package com.example.severin.noteapp.note

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import com.example.severin.noteapp.R
import com.example.severin.noteapp.global.Store
import com.example.severin.noteapp.global.State
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*


class NoteActivity : AppCompatActivity() {

  companion object {
    private val store: Store
    private val dispatcher: NoteDispatcher
    private val stateObs : Observable<State>

    init {
      val eventSub: Subject<NoteEvent> = PublishSubject.create()
      val reducer = NoteReducer()
      val stateSub: Subject<State> = BehaviorSubject.create()
      dispatcher = NoteDispatcher(eventSub)
      store = Store(stateSub, eventSub.hide(), reducer, State(listOf(NoteViewModel(title = "Title", content = "Content", dispatcher = dispatcher))))
      stateObs = stateSub.hide()
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)

    rv_notes.adapter = NoteAdapter(stateObs, dispatcher)
    rv_notes.layoutManager = LinearLayoutManager(this)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }
}


