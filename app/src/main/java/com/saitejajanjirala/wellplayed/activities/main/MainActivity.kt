package com.saitejajanjirala.wellplayed.activities.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.saitejajanjirala.wellplayed.R
import com.saitejajanjirala.wellplayed.activities.viewstadium.ViewActivity
import com.saitejajanjirala.wellplayed.adapters.TeamsAdapter
import com.saitejajanjirala.wellplayed.database.SelectedDatabase
import com.saitejajanjirala.wellplayed.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var teamadata:ArrayList<String>
    private val alist= ArrayList<String>()
    private val blist= ArrayList<String>()
    private lateinit var teambdata:ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application= requireNotNull(this).application
        val datasource=SelectedDatabase.getInstance(application).selectedDao
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        val mainActivtyViewModelFactory=
            MainActivtyViewModelFactory(
                datasource,
                application
            )
        mainActivityViewModel=ViewModelProviders.of(this@MainActivity
            ,mainActivtyViewModelFactory)
            .get(MainActivityViewModel::class.java)
        binding.lifecycleOwner=this
        binding.mainactivityviewmodel=mainActivityViewModel
        val teamaadapter=TeamsAdapter(this@MainActivity,mainActivityViewModel,"A")
        val teambadapter=TeamsAdapter(this@MainActivity,mainActivityViewModel,"B")
        initalist()
        initblist()
        binding.teamalist.addItemDecoration(DividerItemDecoration(this@MainActivity,
            LinearLayoutManager.VERTICAL))
        binding.teamblist.addItemDecoration(DividerItemDecoration(this@MainActivity,
            LinearLayoutManager.VERTICAL))
        mainActivityViewModel.totalselected.observe(this, Observer {
                if(it.isEmpty()){
                    initalist()
                }
                else {
                    for (i in it) {
                        if (i.team.equals("A")) {
                            alist.remove(i.name)
                        }
                    }
                }
                teamaadapter.teamslist=alist
        })
        mainActivityViewModel.totalselected.observe(this, Observer {
                if(it.isEmpty()){
                    initblist()
                }
                else {
                    for (i in it) {
                        if (i.team.equals("B")) {
                            blist.remove(i.name)
                        }
                    }
                }
                teambadapter.teamslist=blist
        })
        mainActivityViewModel.totalcount.observe(this, Observer{
            teamaadapter.totalcount=it
            teambadapter.totalcount=it
        })
        mainActivityViewModel.batsmancount.observe(this, Observer {
            teamaadapter.batsmancount=it
            teambadapter.batsmancount=it
        })
        mainActivityViewModel.bowlercount.observe(this, Observer {
            teamaadapter.bowlercount=it
            teambadapter.bowlercount=it
        })
        mainActivityViewModel.wicketkeepercount.observe(this, Observer {
            teamaadapter.wicketkeepercount=it
            teambadapter.wicketkeepercount=it
        })
        mainActivityViewModel.allroundercount.observe(this, Observer {
            teamaadapter.allroundercount=it
            teambadapter.allroundercount=it
        })
        binding.teamalist.adapter=teamaadapter
        binding.teamblist.adapter=teambadapter
        //binding.selectedrecyclerview.adapter=teamaadapter
        binding.viewplayers.setOnClickListener {
            startActivity(Intent(this@MainActivity,ViewActivity::class.java))
        }
    }
    fun initalist(){
        alist.clear()
        alist.addAll(arrayOf(
            "M.S Dhoni", "Virat Kohli", "K.L Rahul", "Jadeja", "Aswin", "Bumhra", "Bhuvneshwar",
            "Steve Smith","Duplesis","David Warner","Dale Steyn"
        ))
    }
    fun initblist(){
        blist.clear()
        blist.addAll(arrayOf(
            "D.J Bravo","Chris Gayle","Jason Holder","Gautam Gambhir", "Yuvaraj Singh", "Rohith Sharma",
            "Hardik Pandya", "Harbajan Singh","Ross Taylor","Kevin Peterson","Kieron Pollard"
        ))
    }
}