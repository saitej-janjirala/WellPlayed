package com.saitejajanjirala.wellplayed.activities.viewstadium

import android.app.Application
import androidx.lifecycle.ViewModel
import com.saitejajanjirala.wellplayed.database.SelectedDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class ViewActivityViewModel(val dataSource:SelectedDao,val application:Application): ViewModel() {
    val database=dataSource
    val viewmodeljob= Job()
    val batsmanlist=database.getselectedbytype("Batsman")
    val bowlerslist=database.getselectedbytype("Bowler")
    val wicketkeeperslist=database.getselectedbytype("WicketKeeper")
    val allrounderslist=database.getselectedbytype("AllRounder")
    val uiscope= CoroutineScope(Dispatchers.Main+viewmodeljob)
    override fun onCleared() {
        super.onCleared()
    }
}