package com.zavedahmad.todolist4222025

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {
    val todoItems = mutableStateListOf(
        todoItem("this is first task", 1, System.currentTimeMillis()),
        todoItem("this is task 2", 2, System.currentTimeMillis())
    )
    val inputText =mutableStateOf("")
    fun addTodoItem(item: todoItem){
        todoItems.add(item)
    }

}
data class todoItem(val task: String, val id: Int, val createdAt: Long)