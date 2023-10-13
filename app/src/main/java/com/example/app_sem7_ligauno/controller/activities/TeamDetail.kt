package com.example.app_sem7_ligauno.controller.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.app_sem7_ligauno.R
import com.example.app_sem7_ligauno.database.TeamDB
import com.example.app_sem7_ligauno.models.Team
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

@Suppress("DEPRECATION")
class TeamDetail : AppCompatActivity() {
    private lateinit var ivLogoDetail: ImageView
    private lateinit var tvNameDetail: TextView
    private lateinit var tvVenueName: TextView
    private lateinit var fabSave: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        ivLogoDetail = findViewById(R.id.ivLogoDetail)
        tvNameDetail = findViewById(R.id.tvNameDetail)
        tvVenueName = findViewById(R.id.tvVenueName)
        fabSave = findViewById(R.id.fabSave)

        initFields(this)
    }

    private fun initFields(context: Context) {
        val teamObject: Team ?= intent.getSerializableExtra("Team") as Team?

        val picBuilder = Picasso.Builder(context)
        picBuilder.downloader(OkHttp3Downloader(context))
        picBuilder.build().load(teamObject?.logo).into(ivLogoDetail)

        tvNameDetail.text = teamObject?.name
        tvVenueName.text = teamObject?.venueName

        fabSave.setOnClickListener {
            saveTeam(teamObject)
            finish()
        }
    }

    private fun saveTeam(teamObject: Team?) {
        if (teamObject != null) {
            TeamDB.getInstance(this).getTeamDAO().insertTeam(teamObject)
        }
    }
}