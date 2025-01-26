package com.example.msgapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.msgapp.repository.MessageRepository


class MessageViewModelFactory(private val repository: MessageRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MessageViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MessageViewModel(repository) as T
        }
        throw IllegalArgumentException("Erro ao acessar o viewmodel")
    }
}