package com.example.crud02.model

data class Item (
    val id: String = generated().toString(),
    val title: String,
    val description: String
){
    companion object {
        private var currentId = 0

        fun generated(): Int{
            return currentId++
        }
    }
}