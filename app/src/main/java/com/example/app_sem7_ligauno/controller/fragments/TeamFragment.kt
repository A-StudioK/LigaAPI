package com.example.app_sem7_ligauno.controller.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_sem7_ligauno.R
import com.example.app_sem7_ligauno.adapter.TeamAdapter
import com.example.app_sem7_ligauno.controller.activities.TeamDetail
import com.example.app_sem7_ligauno.models.ApiResponseHeader
import com.example.app_sem7_ligauno.models.Team
import com.example.app_sem7_ligauno.network.TeamService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TeamFragment : Fragment(), TeamAdapter.OnItemClickListener {
    var team: List<Team> = ArrayList()
    lateinit var teamRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        teamRecyclerView = view.findViewById(R.id.rvTeams)
        loadTeams(view.context)
    }

    private fun loadTeams(context: Context) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-football-v1.p.rapidapi.com/v2/teams/league/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val teamService: TeamService = retrofit.create(TeamService::class.java)

        val request = teamService.getTeams(
            "api-football-v1.p.rapidapi.com",
            "0bee96e232msh91abfc697a99ee1p1f3b1fjsn3694ca9ffc56"
        )

        request.enqueue(object : Callback<ApiResponseHeader> {
            override fun onResponse(
                call: Call<ApiResponseHeader>,
                response: Response<ApiResponseHeader>
            ) {
                if (response.isSuccessful){
                    val teams : List<Team> = response.body()!!.api.teams ?: ArrayList()
                    teamRecyclerView.layoutManager = LinearLayoutManager(context)
                    teamRecyclerView.adapter = TeamAdapter(teams, context, this@TeamFragment)
                }
            }

            override fun onFailure(call: Call<ApiResponseHeader>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onItemClicked(team: Team) {
        val intent = Intent(context, TeamDetail::class.java)
        intent.putExtra("Team", team)
        startActivity(intent)
    }
}