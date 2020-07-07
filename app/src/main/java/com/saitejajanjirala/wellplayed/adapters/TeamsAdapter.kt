package com.saitejajanjirala.wellplayed.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.saitejajanjirala.wellplayed.R
import com.saitejajanjirala.wellplayed.activities.main.MainActivityViewModel
import com.saitejajanjirala.wellplayed.database.Selected

class TeamsAdapter(val context:Context, val viewmodel: MainActivityViewModel, val team:String)
    :RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder>(){
    val options=arrayOf("Batsman","WicketKeeper","Bowler","AllRounder","Cancel")
    var batsmancount =0
        set(value) {
            field=value
        }
    var bowlercount=0
        set(value){
            field=value
        }
    var wicketkeepercount=0
        set(value) {
            field=value
        }
    var allroundercount=0
        set(value){
            field=value
        }
    var totalcount=0
        set(value){
            field=value
        }
    var teamslist= listOf<String>()
        set(value){
            field=value
            notifyDataSetChanged()
        }
    class TeamsViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        val playername:TextView=itemview.findViewById(R.id.playername)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.teamslayout,parent,false)
        return TeamsViewHolder(view)
    }
    override fun getItemCount()=teamslist.size
    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val dialog=AlertDialog.Builder(context)
                    dialog.setTitle("Select one")
                    dialog.setItems(options, DialogInterface.OnClickListener { dialogInterface, i ->
                        if(totalcount!=11) {
                            when (i) {
                                0 -> {
                                        if(batsmancount!=6){
                                            val select=Selected()
                                            select.name=teamslist[position]
                                            select.team=team
                                            select.type=options[i]
                                            viewmodel.insert(select)
                                        }
                                        else{
                                            showtoast(6,"for batsmen")
                                        }
                                }
                                1 -> {
                                    if(wicketkeepercount!=4){
                                        val select=Selected()
                                        select.name=teamslist[position]
                                        select.team=team
                                        select.type=options[i]
                                        viewmodel.insert(select)
                                    }
                                    else{
                                        showtoast(4,"for WicketKeeper")
                                    }
                                }
                                2 -> {
                                    if(bowlercount!=6){
                                        val select=Selected()
                                        select.name=teamslist[position]
                                        select.team=team
                                        select.type=options[i]
                                        viewmodel.insert(select)
                                    }
                                    else{
                                        showtoast(6,"for Bowler")
                                    }

                                }
                                3 -> {
                                    if(allroundercount!=4){
                                        val select=Selected()
                                        select.name=teamslist[position]
                                        select.team=team
                                        select.type=options[i]
                                        viewmodel.insert(select)
                                    }
                                    else{
                                        showtoast(4,"for AllRounder")
                                    }
                                }
                            }
                        }
                        else{
                            showtoast(11)
                        }
                    })
                    dialog.create()
                    dialog.show()
        }
       holder.playername.text=teamslist[position]
    }
    fun showtoast(data:Any,msg:String=""){
        Toast.makeText(context,"$data are filled $msg",Toast.LENGTH_LONG).show()
    }
}