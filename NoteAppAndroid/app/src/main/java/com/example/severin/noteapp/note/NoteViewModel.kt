package com.example.severin.noteapp.note

import android.text.Editable
import android.text.TextWatcher
import java.util.*

data class NoteViewModel(val id: String = UUID.randomUUID().toString(), val title : String, val content: String, val dispatcher: NoteDispatcher) {

  val titleChangeWatcher = object : TextWatcher {
    override fun afterTextChanged(p0: Editable?) {}
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
      dispatcher.titleModified(id, s.toString())
    }
  }


}