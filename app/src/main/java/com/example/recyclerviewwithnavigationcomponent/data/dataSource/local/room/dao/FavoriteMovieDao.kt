package com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.DetailMovieEntity

@Dao
interface FavoriteMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieToFavorite(dataMovie: DetailMovieEntity)

    @Update
    fun updateMovieFromFavorite(dataMovie: DetailMovieEntity)

    @Query("SELECT * FROM DetailMovieEntity")
    fun getAllFavoriteMovie(): List<DetailMovieEntity>

    //masih belum fix
    @Delete
    fun deleteMovieFromFavorite(dataMovie: DetailMovieEntity)
}