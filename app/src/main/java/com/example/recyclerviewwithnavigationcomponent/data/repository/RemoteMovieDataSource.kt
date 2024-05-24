package com.example.recyclerviewwithnavigationcomponent.data.repository

import androidx.lifecycle.LiveData
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueEntity
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.TeamsEntity
import com.example.recyclerviewwithnavigationcomponent.data.model.dataClass.DataItemCollections
import com.example.recyclerviewwithnavigationcomponent.data.model.dataClass.DetailMovie
import com.example.recyclerviewwithnavigationcomponent.data.model.dataClass.Movies

interface RemoteMovieDataSource {
    suspend fun insertLeagueData(listDataLeague : List<LeagueEntity>)
    suspend fun insertTeamsData(listDataTeams : List<TeamsEntity>)
    suspend fun deleteTeams()

    suspend fun getAllDataMovieNowPlaying(): LiveData<List<Movies>>
    suspend fun setDetailMovie(movieId: Int): DetailMovie
    suspend fun getDetailCollections(collectionId:Int): List<DataItemCollections>
}