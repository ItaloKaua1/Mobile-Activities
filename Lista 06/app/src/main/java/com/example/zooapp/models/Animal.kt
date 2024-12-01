package com.example.zooapp.models

import com.example.zooapp.R

data class  Animal(
    val id: Int,
    val name: String,
    val species: String,
    val imageRes: Int,
    val description: String,
    val curiosities: String,
    var isFavorite: Boolean = false
)

val animalList = listOf(
    Animal(
            id = 1,
            name = "Elefante",
            species = "Elephas maximus",
            imageRes = R.drawable.elephant,
            description = "O elefante é o maior animal terrestre existente.",
            curiosities = "Os elefantes podem reconhecer-se em um espelho, mostrando sinais de autoconsciência."
    ),
    Animal(
        id = 2,
        name = "Golfinho",
        species = "Delphius delphis",
        imageRes = R.drawable.dolphin,
        description = "O golfinho é um mamífero aquático altamente inteligente.",
        curiosities = "Os golfinhos têm nomes únicos, usando assobios para se indentificar."
    )

)
