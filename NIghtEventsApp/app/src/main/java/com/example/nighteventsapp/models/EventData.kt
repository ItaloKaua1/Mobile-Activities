package com.example.nighteventsapp.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.nighteventsapp.R

data class Event(
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val location: String,
    val isFavorite: MutableState<Boolean> = mutableStateOf(false),
    val isSubscribed: MutableState<Boolean> = mutableStateOf(false),
    val imageRes: Int
)

val eventList = listOf(
    Event(
        id = 1,
        title = "Conferência de Tecnologia 2024",
        description = "Tendências em tecnologia.",
        date = "2024-12-15",
        location = "Parque Tecnológico",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img1
    ),
    Event(
        id = 2,
        title = "Workshop de Inteligência Artificial",
        description = "Aprenda a criar modelos de IA do zero.",
        date = "2024-12-20",
        location = "Centro de Inovação",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img2
    ),
    Event(
        id = 3,
        title = "Feira de Startups 2024",
        description = "Conectando empreendedores e investidores.",
        date = "2025-01-10",
        location = "Espaço Startup Hub",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img3
    ),
    Event(
        id = 4,
        title = "Hackathon Internacional",
        description = "48 horas de programação para criar soluções inovadoras.",
        date = "2025-02-01",
        location = "Campus Universitário",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img4
    ),
    Event(
        id = 5,
        title = "Palestra sobre Sustentabilidade Tecnológica",
        description = "A tecnologia como aliada do meio ambiente.",
        date = "2025-03-05",
        location = "Auditório Verde",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img5
    )

)