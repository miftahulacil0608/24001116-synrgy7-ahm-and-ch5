package com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.FavoriteMovieEntity

@Dao
interface FavoriteMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieToFavorite(dataMovie: FavoriteMovieEntity)

    @Query("SELECT * FROM FavoriteMovieEntity")
    fun getAllFavoriteMovie(): LiveData<List<FavoriteMovieEntity>>

    @Query("DELETE FROM FavoriteMovieEntity WHERE id = :id")
    suspend fun deleteMovieFromFavorite(id: Int)

    @Query("SELECT EXISTS (SELECT * FROM FavoriteMovieEntity WHERE id = :id)")
    suspend fun isExistsMovieFavorite(id: Int): Boolean
}