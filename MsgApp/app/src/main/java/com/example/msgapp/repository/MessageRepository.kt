package com.example.msgapp.repository

import com.example.msgapp.data.local.dao.MessageDao
import com.example.msgapp.model.Message
import kotlinx.coroutines.flow.Flow

class MessageRepository(private val dao: MessageDao) {
    val allMessages: Flow<List<Message>> = dao.getAllMessages()

    suspend fun addMessage(content: String){
        val message = Message(content = content, timesTamp = System.currentTimeMillis())
        dao.insertMessage(message)
    }
}