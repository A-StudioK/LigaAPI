package com.example.app_sem7_ligauno.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.app_sem7_ligauno.R
import com.example.app_sem7_ligauno.models.Team
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class TeamAdapter(val teams: List<Team>, val context: Context, val itemClickListener: OnItemClickListener): Adapter<TeamAdapter.ViewHolder>() {
    class ViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val ivLogo = view.findViewById<ImageView>(R.id.ivLogo)
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val cvTeam = view.findViewById<CardView>(R.id.cvTeam)
    }

    interface OnItemClickListener {
        fun onItemClicked(team: Team)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.prototype_team,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamAdapter.ViewHolder, position: Int) {
        val team = teams[position]
        holder.tvName.text = team.name

        val picBuilder = Picasso.Builder(context)
        picBuilder.downloader(OkHttp3Downloader(context))
        picBuilder.build().load(team.logo)
            .placeholder(R.drawable.ic_launcher_background)//MOMENT
            .error(R.drawable.ic_launcher_background)//ERROR
            .into(holder.ivLogo)

        holder.cvTeam.setOnClickListener {
            itemClickListener.onItemClicked(team)
        }
    }

    override fun getItemCount(): Int {
        return teams.size
    }
}