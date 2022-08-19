package com.odogwudev.benshiposts.data.local.database

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users_table")
data class User(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    val id: Long? = null,
    val number: String? = null,
)