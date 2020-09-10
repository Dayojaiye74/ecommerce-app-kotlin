package com.dev_app.ecommercesales.database.shoe

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ShoesFromDatabase(

    @PrimaryKey(autoGenerate = true) val uid: Int?,

    @ColumnInfo val title: String,

    @ColumnInfo val price: Double
)