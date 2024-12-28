package com.example.tasksmanagerapp

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrightnessHigh
import androidx.compose.material3.*
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(viewModel: TasksViewModel = TasksViewModel(LocalContext.current)) {
    val tasks by viewModel.tasks.collectAsState()
    val progress by viewModel.progress.collectAsState()
    val isDarkTheme by viewModel.isDarkTheme.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    MaterialTheme(
        colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
                TopAppBar(
                    title = { Text("Gerenciador de Tarefas") },
                    actions = {
                        IconButton(onClick = { viewModel.toggleTheme(context) }) {
                            Icon(Icons.Default.BrightnessHigh, contentDescription = "Alternar Tema")
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Text("Progresso das Tarefas")
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(tasks) { task ->
                        TaskItem(
                            task = task,
                            onToggleCompletion = { viewModel.toggleTaskCompletion(task) },
                            onDelete = {
                                viewModel.removeTask(task)
                                scope.launch {
                                    val result = snackbarHostState.showSnackbar(
                                        message = "Tarefa removida",
                                        actionLabel = "Desfazer",
                                        duration = SnackbarDuration.Short
                                    )
                                    if (result == SnackbarResult.ActionPerformed) {
                                        viewModel.undoDelete()
                                    }
                                }
                            }
                        )
                    }
                }
                AddTaskSection(onAddTask = { name, category, priority ->
                    viewModel.addTask(Task(name, false, category, priority))
                })
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, onToggleCompletion: () -> Unit, onDelete: () -> Unit) {
    val scale by animateFloatAsState(if (task.isCompleted) 1.05f else 1f)
    val backgroundColor = when (task.priority) {
        TaskPriority.BAIXA -> Color(0xFFC8E6C9)
        TaskPriority.MEDIA -> Color(0xFFFFF59D)
        TaskPriority.ALTA -> Color(0xFFFFCDD2)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(backgroundColor)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = task.isCompleted, onCheckedChange = { onToggleCompletion() })
        Text(
            text = task.name,
            modifier = Modifier.weight(1f),
            color = if (task.isCompleted) Color.Gray else Color.Black
        )
    }
}

@Composable
fun AddTaskSection(onAddTask: (String, TaskCategory, TaskPriority) -> Unit) {
    var taskName by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(TaskCategory.CASA) }
    var selectedPriority by remember { mutableStateOf(TaskPriority.MEDIA) }

    Column {
        OutlinedTextField(
            value = taskName,
            onValueChange = { taskName = it },
            label = { Text("Nova tarefa") },
            modifier = Modifier.fillMaxWidth()
        )
        Row {
            Text("Categoria")
            Text("Prioridade")
        }
        Button(onClick = {
            onAddTask(taskName, selectedCategory, selectedPriority)
        }) {
            Text("Adicionar")
        }
    }
}
