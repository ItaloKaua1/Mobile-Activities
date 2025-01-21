package com.example.apppost2.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material.MaterialTheme
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextButton
import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.apppost2.data.models.Post

@Composable
fun PostItem(post: Post, onDelete: (Int)-> Unit, onEdit: (Post)-> Unit){
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ){
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = post.title, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.padding(4.dp))


            Text(text = post.content, style = MaterialTheme.typography.body2)
            Spacer(modifier = Modifier.padding(4.dp))

            Row (modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { showDialog = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                    ),
                    modifier = Modifier.padding(8.dp)
                )
                {
                    Text(
                        text = "Deletar",
                        color = Color.White
                    )
                }
                Button(
                    onClick = { onEdit(post) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6200EA), // Purple
                    ),
                    modifier = Modifier.padding(8.dp)

                ) {
                    Text(
                        text = "Editar",
                        color = Color.White
                    )
                }
            }
        }
    }

    if(showDialog){
        AlertDialog(
            onDismissRequest = {showDialog = false},
            title = {Text(text = "Confirmar Exclusão")},
            text = { Text(text = "Tem certeza que deseja deletar este post ?")},
            confirmButton = {
                TextButton(
                    onClick = {onDelete(post.id)
                        showDialog = false })
                {
                    Text(text = "Sim")
                }
            },
            dismissButton = {
                TextButton(onClick ={ showDialog = false}) {
                    Text(text = "Cancelar")
                }
            }
        )
    }
}