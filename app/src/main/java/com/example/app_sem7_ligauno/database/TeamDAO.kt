package com.example.app_sem7_ligauno.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.app_sem7_ligauno.models.Team

@Dao
interface TeamDAO {
    @Insert
    fun insertTeam(vararg team: Team)

    @Query("SELECT * FROM teams")
    fun getAllTeams(): List<Team>

    @Delete
    fun deleteTeam(vararg team: Team)

    @Update
    fun updateTeam(vararg team: Team)
}