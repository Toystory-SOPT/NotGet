package com.toystory.notget.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey val id: Int,
    @ColumnInfo val title: String,
    @ColumnInfo val startTime: String,
    @ColumnInfo val endTime: String
)
