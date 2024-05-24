package com.example.recyclerviewwithnavigationcomponent.domain

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueEntity
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueWithTeamsList
import com.example.recyclerviewwithnavigationcomponent.data.model.dataClass.DataItemCollections
import com.example.recyclerviewwithnavigationcomponent.data.model.dataClass.DetailMovie
import com.example.recyclerviewwithnavigationcomponent.data.model.dataClass.Movies
import com.example.recyclerviewwithnavigationcomponent.data.model.dataClass.UserProfileData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.log

class UseCase(
    private val movieRepository: MovieRepository,
    private val loginRepository: LoginRepository,
) {
    // ini yang akan dikonsumsi sama recyclerview kedepannya
    fun getListMovieNowPlaying(): LiveData<List<Movies>> = liveData {
        try {
            val listDataMovie = movieRepository.getAllDataMoviesNowPlaying()
            emitSource(listDataMovie)
        } catch (throwable: Throwable) {
            throw IllegalAccessException(throwable.message)
        }
    }

    suspend fun setDetailMovie(movieId: Int): DetailMovie? {
        return movieRepository.setDetailMovie(movieId)
    }

    suspend fun getDetailCollections(collectionId: Int): List<DataItemCollections> {

        return when (collectionId) {
            1 -> {
                withContext(Dispatchers.IO) {
                    listOf(
                        DataItemCollections(null, null, null)
                    )
                }
            }

            else -> {
                movieRepository.getDetailCollections(collectionId)
            }
        }

    }

    fun getFavorite(): LiveData<List<LeagueWithTeamsList>> {
        return movieRepository.getAllDataFavorite()
    }

    suspend fun setFavorite(leagueData: LeagueEntity, isFavorite: Boolean) {
        leagueData.favorite = isFavorite
        movieRepository.updateLeagueData(leagueData)
    }

    suspend fun getAllDataUserProfile(): UserProfileData{
        return loginRepository.getAllDataUserProfile()
    }

    suspend fun updateDataUserProfile(username: String, email: String, password: String){
        loginRepository.updateUserProfile(username, email, password)
    }

    suspend fun clearDataAuth() {
        loginRepository.clearDataAccount()
    }

}