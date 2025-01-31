package com.example.crud02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crud02.ui.theme.Crud02Theme
import com.example.crud02.ui.view.ItemScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Crud02Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = {paddingValues ->
                        ItemScreen(
                            modifier = Modifier
                                .padding(paddingValues )
                                .padding(top = 16.dp)
                        )

                    }
                )
            }
        }
    }
}