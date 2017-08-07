package com.example.severin.noteapp.note

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.severin.noteapp.databinding.ItemNoteBinding
import com.example.severin.noteapp.global.State
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

class NoteAdapter(val stateObs: Observable<State>, dispatcher: NoteDispatcher) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

  companion object {
    var disposable : Disposable? = null
    var models : List<NoteViewModel> = listOf()

    fun setupSubscription(stateObs: Observable<State>, adapter: NoteAdapter?) {
      disposable?.dispose()
      disposable = stateObs.subscribe {
        models = it.notes
      }
    }
  }

  init {
    setupSubscription(stateObs, this)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val context = parent.context
    val inflator = LayoutInflater.from(context)
    val itemBinding = ItemNoteBinding.inflate(inflator, parent, false)
    return ViewHolder(itemBinding)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(models[position])
  }

  override fun getItemCount(): Int {
    return models.size
  }

  class ViewHolder(val binding : ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(vm : NoteViewModel) {
      binding.vm = vm
      binding.executePendingBindings()
    }
  }
}
