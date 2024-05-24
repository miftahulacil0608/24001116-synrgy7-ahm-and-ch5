package com.example.recyclerviewwithnavigationcomponent.data

import androidx.lifecycle.LiveData
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueEntity
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueWithTeamsList
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.TeamsEntity
import com.example.recyclerviewwithnavigationcomponent.data.model.dataClass.DataItemCollections
import com.example.recyclerviewwithnavigationcomponent.data.model.dataClass.DetailMovie
import com.example.recyclerviewwithnavigationcomponent.data.model.dataClass.Movies
import com.example.recyclerviewwithnavigationcomponent.data.repository.LocalLeagueDataSource
import com.example.recyclerviewwithnavigationcomponent.data.repository.RemoteMovieDataSource
import com.example.recyclerviewwithnavigationcomponent.domain.MovieRepository

class MovieRepositoryImpl(private val remoteMovieDataSource: RemoteMovieDataSource, private val localLeagueDataSource: LocalLeagueDataSource) : MovieRepository {
    override suspend fun insertLeagueData(leagueData: List<LeagueEntity>) {
        remoteMovieDataSource.insertLeagueData(leagueData)
    }

    override suspend fun insertTeamsData(teamsData: List<TeamsEntity>) {
        remoteMovieDataSource.insertTeamsData(teamsData)
    }

    override suspend fun updateLeagueData(leagueData: LeagueEntity) {
        localLeagueDataSource.updateDataLeague(leagueData)
    }

    override fun getAllDataLeague(): LiveData<List<LeagueWithTeamsList>> {
        return localLeagueDataSource.getAllDataLeague()
    }

    override fun getAllDataFavorite(): LiveData<List<LeagueWithTeamsList>> {
        return localLeagueDataSource.getAllDataFavorite()
    }

    override suspend fun delete() {
        localLeagueDataSource.delete()
    }

    override suspend fun isFavorite(title: String):Boolean {
        return localLeagueDataSource.isFavorite(title)
    }

    override suspend fun deleteTeams() {
        remoteMovieDataSource.deleteTeams()
    }

    override suspend fun getAllDataMoviesNowPlaying(): LiveData<List<Movies>> {
       return remoteMovieDataSource.getAllDataMovieNowPlaying()
    }

    override suspend fun setDetailMovie(movieId: Int): DetailMovie {
        return remoteMovieDataSource.setDetailMovie(movieId)
    }

    override suspend fun getDetailCollections(collectionId: Int): List<DataItemCollections> {
        return remoteMovieDataSource.getDetailCollections(collectionId)
    }


}