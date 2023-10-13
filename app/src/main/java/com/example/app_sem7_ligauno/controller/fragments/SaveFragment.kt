package com.example.app_sem7_ligauno.controller.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_sem7_ligauno.R
import com.example.app_sem7_ligauno.adapter.TeamAdapter
import com.example.app_sem7_ligauno.database.TeamDB
import com.example.app_sem7_ligauno.models.Team


class SaveFragment : Fragment(), TeamAdapter.OnItemClickListener {
    var team: List<Team> = ArrayList()
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_save, container, false)
    }

    //Ctrl + o
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        team = TeamDB.getInstance(view.context).getTeamDAO().getAllTeams()
        recyclerView = view.findViewById(R.id.rvTeamSave)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = TeamAdapter(team, view.context, this)
    }

    override fun onItemClicked(team: Team) {
        TODO("Not yet implemented")
    }
}