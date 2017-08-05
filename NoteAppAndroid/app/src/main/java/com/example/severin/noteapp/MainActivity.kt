package com.example.severin.noteapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*


class MainActivity : AppCompatActivity() {

  companion object {
    val store: NoteStore
    val dispatcher: NoteDispatcher

    init {
      val eventSub: Subject<Event> = PublishSubject.create()
      val stateSub: Subject<NoteState> = BehaviorSubject.create()
      val reducer = NoteReducer()
      store = NoteStore(stateSub, eventSub.hide(), reducer)
      dispatcher = NoteDispatcher(eventSub)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)


    rv_notes.adapter = NoteAdapter(store.state)
    rv_notes.layoutManager = LinearLayoutManager(this)

    store.state = NoteState(listOf(NoteViewModel("TestTitle", "TestContent")))
    rv_notes.adapter.notifyDataSetChanged()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }
}


