package com.exemaple.meuprimeiroapp.offline

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Entity
data class NewsItem(@PrimaryKey val id: String, val title: String, val description: String)

@Dao
interface  NewsDao{
    @Query("SELECT * FROM NewsItem")
    fun getAll(): Flow<List<NewsItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<NewsItem>)
}

@Database(entities = [NewsItem::class], version = 1)
abstract  class NewsDatabase: RoomDatabase(){
    abstract fun newsDao(): NewsDao
}



