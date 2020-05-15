package com.example.ing_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabasePost::class], version = 1)
abstract class PostsDatabase : RoomDatabase() {
    abstract val postsDao: PostsDao
}

private lateinit var INSTANCE: PostsDatabase

fun getDatabase(context: Context): PostsDatabase {
    synchronized(PostsDatabase::class.java) {
        if(!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                PostsDatabase::class.java,
                "posts").build()
        }
    }
    return INSTANCE
}