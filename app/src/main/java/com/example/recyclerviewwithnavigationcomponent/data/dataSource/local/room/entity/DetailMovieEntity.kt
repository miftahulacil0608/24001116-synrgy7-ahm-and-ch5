package com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "DetailMovieEntity")
data class DetailMovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo("posterImage")
    val posterImage: String,
    @ColumnInfo(name = "titleMovie")
    val titleMovie: String,
):Parcelable
