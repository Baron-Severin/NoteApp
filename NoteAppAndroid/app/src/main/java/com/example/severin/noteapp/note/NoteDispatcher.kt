package com.example.severin.noteapp.note

import io.reactivex.subjects.Subject


/*
 *  At this time, the Dispatcher is very simple.  Later, however, tasks like DB
 *  access and networking will be done from here.  The reason for this is that
 *  the reduce function should be pure, and so this information must be known
 *  before an event has been created.
 */
class NoteDispatcher(val eventSub: Subject<NoteEvent>) {
  fun titleModified(id : String, newText : String) {
    eventSub.onNext(TitleChangedNoteEvent(id, newText))
  }
}