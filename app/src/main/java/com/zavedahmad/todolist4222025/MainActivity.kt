package com.zavedahmad.todolist4222025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.lifecycle.ViewModel
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zavedahmad.todolist4222025.ui.theme.TodoList4222025Theme
import java.text.SimpleDateFormat

import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TodoList4222025Theme {
                val viewModel : TodoViewModel = viewModel()
                TodoPage(viewModel)
            }
        }
    }
}



@Composable
fun TodoPage(viewModel: TodoViewModel ) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            OutlinedTextField(
                viewModel.inputText.value,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.weight(3f),
                onValueChange = { viewModel.inputText.value = it })

            Button(onClick = {
                val newItem= todoItem(
                    task= viewModel.inputText.value,
                    id= viewModel.todoItems.size +1,
                    createdAt= System.currentTimeMillis()
                )
                viewModel.addTodoItem(newItem)
                viewModel.inputText.value=""
            }, modifier = Modifier.weight(1f)) {
                Text(
                    "Add",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))


        LazyColumn(content={itemsIndexed(viewModel.todoItems) {index:Int, item:todoItem ->
            Column(
                modifier = Modifier

                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .fillMaxWidth()
                        .padding(10.dp)

                ) {

                    Text(

                        SimpleDateFormat(
                            "dd-MM-yyyy HH:mm:ss",
                            Locale.getDefault()
                        ).format(item.createdAt),
                        color = MaterialTheme.colorScheme.inversePrimary,
                        style = TextStyle(fontSize = 10.sp)

                    )

                    Text(
                        item.task, color = MaterialTheme.colorScheme.onPrimary
                    )


                }
            }
        }})



        Text(viewModel.todoItems[0].task,
            color = MaterialTheme.colorScheme.onSurface)

    }
}

//@Composable
//fun TodoItemRow(){
//
//}