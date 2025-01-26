package com.example.msgapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.msgapp.data.local.dao.MessageDao
import com.example.msgapp.model.Message


@Database(entities = [Message::class], version = 1, exportSchema = false )
abstract class AppDataBase: RoomDatabase() {
    abstract fun messageDao():MessageDao
}