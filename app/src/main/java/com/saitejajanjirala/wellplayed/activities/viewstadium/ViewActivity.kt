package com.saitejajanjirala.wellplayed.activities.viewstadium

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.saitejajanjirala.wellplayed.R
import com.saitejajanjirala.wellplayed.database.SelectedDatabase
import com.saitejajanjirala.wellplayed.databinding.ActivityViewBinding

class ViewActivity : AppCompatActivity() {
    private lateinit var binding:ActivityViewBinding
    private lateinit var viewactivityviewmodel:ViewActivityViewModel
    private lateinit var viewActivtyviewModelFactory: ViewActivtyviewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application= requireNotNull(this).application
        val datasource= SelectedDatabase.getInstance(application).selectedDao
        binding=DataBindingUtil.setContentView(this,R.layout.activity_view)
        viewActivtyviewModelFactory= ViewActivtyviewModelFactory(datasource,application)
        viewactivityviewmodel=ViewModelProviders.of(this,viewActivtyviewModelFactory)
            .get(ViewActivityViewModel::class.java)
        binding.lifecycleOwner=this
        viewactivityviewmodel.wicketkeeperslist.observe(this, Observer {
            val layout:LinearLayout=binding.wicketkeeperslayout
            for(i in it){
                val view=LayoutInflater.from(this).inflate(R.layout.playerlayout,layout,
                    false)
                view.findViewById<TextView>(R.id.playername).text=i.name
                layout.addView(view)
            }
        })
        viewactivityviewmodel.allrounderslist.observe(this, Observer {
            val layout:LinearLayout=binding.allrounderslayout
            for(i in it){
                val view=LayoutInflater.from(this).inflate(R.layout.playerlayout,layout,
                    false)
                view.findViewById<TextView>(R.id.playername).text=i.name
                layout.addView(view)
            }
        })
        viewactivityviewmodel.batsmanlist.observe(this, Observer {
            val layout1:LinearLayout=binding.batsman1layout
            val layout2:LinearLayout=binding.batsman2layout
            for(i in it.indices){
                if(i>3){
                    val view = LayoutInflater.from(this).inflate(
                        R.layout.playerlayout, layout2,
                        false
                    )
                    view.findViewById<TextView>(R.id.playername).text = it[i].name
                    layout2.addView(view)
                }
                else{
                    val view=LayoutInflater.from(this).inflate(R.layout.playerlayout,layout1,
                        false)
                    view.findViewById<TextView>(R.id.playername).text=it[i].name
                    layout1.addView(view)
                }
            }

        })
        viewactivityviewmodel.bowlerslist.observe(this, Observer {
            val layout1:LinearLayout=binding.bowler1layout
            val layout2:LinearLayout=binding.bowlers2layout
            for(i in it.indices){
                if(i>3){
                    val view = LayoutInflater.from(this).inflate(
                        R.layout.playerlayout, layout2,
                        false
                    )
                    view.findViewById<TextView>(R.id.playername).text = it[i].name
                    layout2.addView(view)
                }
                else{
                    val view=LayoutInflater.from(this).inflate(R.layout.playerlayout,layout1,
                        false)
                    view.findViewById<TextView>(R.id.playername).text=it[i].name
                    layout1.addView(view)
                }
            }
        })

    }

}