package com.example.recyclerviewwithnavigationcomponent.data.dataSource.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.dao.LeagueDao
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.dao.TeamsDao
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueEntity
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.TeamsEntity
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.remote.retrofit.MovieService
import com.example.recyclerviewwithnavigationcomponent.data.model.dataClass.DataItemCollections
import com.example.recyclerviewwithnavigationcomponent.data.model.dataClass.DetailMovie
import com.example.recyclerviewwithnavigationcomponent.data.model.dataClass.Movies
import com.example.recyclerviewwithnavigationcomponent.data.model.toDataItemCollections
import com.example.recyclerviewwithnavigationcomponent.data.model.toDetailMovie
import com.example.recyclerviewwithnavigationcomponent.data.model.toMovies
import com.example.recyclerviewwithnavigationcomponent.data.repository.RemoteMovieDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteMovieImpl(
    private val leagueDao: LeagueDao,
    private val teamsDao: TeamsDao,
    private val movieService: MovieService
) : RemoteMovieDataSource {
    override suspend fun insertLeagueData(listDataLeague: List<LeagueEntity>) {
        leagueDao.insertLeagueData(listDataLeague)
    }

    override suspend fun insertTeamsData(listDataTeams: List<TeamsEntity>) {
        teamsDao.insertDataTeam(listDataTeams)
    }

    override suspend fun deleteTeams() {
        teamsDao.deleteDataTeams()
    }

    override suspend fun getAllDataMovieNowPlaying(): LiveData<List<Movies>> {
        val movieList = movieService.getNowPlayingMovies().results
        return MutableLiveData(movieList.map {
            it.toMovies()
        })
    }

    override suspend fun setDetailMovie(movieId: Int): DetailMovie {
        val detailMovieResponse = movieService.getMovieDetail(movieId)
        return detailMovieResponse.toDetailMovie()
    }

    override suspend fun getDetailCollections(collectionId: Int): List<DataItemCollections> {
        return movieService.getCollectionDetail(collectionId).parts.map {
                    it.toDataItemCollections()
                }
    }
}