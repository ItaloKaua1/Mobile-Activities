package com.example.msgapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.msgapp.repository.MessageRepository


class MessageViewModelFactory(private val repository: MessageRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //return super.create(modelClass)

        if(modelClass.isAssignableFrom(MessageViewModel::class.java)){
            @Suppress("UNCHECKED_CASE")
            return MessageViewModel(repository) as T
        }
        throw IllegalArgumentException("Erro ao acessar o viewmodel")
    }
}