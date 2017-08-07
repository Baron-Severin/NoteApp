package com.example.severin.noteapp.global

import android.app.Application
import com.example.severin.noteapp.di.ComponentDependencies
import com.example.severin.noteapp.di.DaggerComponentDependencies_GlobalComponent

class GlobalApplication : Application() {

  lateinit var globalComponent : ComponentDependencies.GlobalComponent

  override fun onCreate() {
    super.onCreate()
    globalComponent = DaggerComponentDependencies_GlobalComponent.create()
  }
}