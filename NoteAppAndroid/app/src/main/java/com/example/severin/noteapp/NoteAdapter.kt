package com.example.severin.noteapp

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.severin.noteapp.databinding.ItemNoteBinding

class NoteAdapter(var state: NoteState) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
//  val models:  List<NoteViewModel>
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val context = parent.context
    val inflator = LayoutInflater.from(context)
    val itemBinding = ItemNoteBinding.inflate(inflator, parent, false)
    return ViewHolder(itemBinding)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(state.notes[position])
  }

  override fun getItemCount(): Int {
    return state.notes.size
  }

  class ViewHolder(val binding : ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(vm : NoteViewModel) {
      binding.vm = vm
      binding.executePendingBindings()
    }
  }
}
