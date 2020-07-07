package com.saitejajanjirala.wellplayed.activities.main

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saitejajanjirala.wellplayed.database.Selected
import com.saitejajanjirala.wellplayed.database.SelectedDao
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivityViewModel(data:SelectedDao,
                            application: Application):ViewModel(){
    val database=data
    val totalselected=database.getallselected()
    val totalcount=database.getcount()
    val batsmancount=database.gettypecount("Batsman")
    val bowlercount=database.gettypecount("Bowler")
    val wicketkeepercount=database.gettypecount("WicketKeeper")
    val allroundercount=database.gettypecount("AllRounder")
    var viewmodeljob= Job()
    private val uiscope= CoroutineScope(Dispatchers.Main+viewmodeljob)

    fun insert(selected: Selected){
        uiscope.launch {
            insertselected(selected)
        }
    }
    private suspend fun insertselected(selected: Selected){
            withContext(Dispatchers.IO){
                database.insert(selected)
            }
    }
    override fun onCleared() {
        super.onCleared()
        viewmodeljob.cancel()
    }

    fun clear(){
        uiscope.launch {
            cleardata()
        }
    }
    private suspend fun cleardata(){
        withContext(Dispatchers.IO){
            database.clear()
        }
    }
}