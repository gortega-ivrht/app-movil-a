package com.example.sesion01.pantallas

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sesion01.data.model.User
import com.example.sesion01.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCursores (viewModel: UserViewModel){
    var nameFiter by remember { mutableStateOf("") }
    val users by viewModel.userList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, top = 32.dp,end=16.dp, bottom = 16.dp)
    ) {

        //Campo de entrada
        TextField(
            value = nameFiter,
            onValueChange = { nameFiter = it },
            label = { Text("Filtra por nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        //Botón para aplicr el filtro
        Button(
            onClick = { viewModel.filterUsersByName(nameFiter)},
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Buscar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(users){
                user -> UserRow(user)
            }
        }

    }
}

@Composable
fun UserRow(user: User) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(8.dp, Color.Gray)
            .padding(8.dp)
    ){
        Text(text = user.id.toString(), modifier = Modifier.weight(1f))
        Text(text = user.name, modifier = Modifier.weight(2f))
    }
}
