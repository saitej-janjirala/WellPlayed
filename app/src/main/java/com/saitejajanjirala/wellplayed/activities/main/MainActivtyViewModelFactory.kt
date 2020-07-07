package com.saitejajanjirala.wellplayed.activities.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saitejajanjirala.wellplayed.database.SelectedDao
import java.lang.IllegalArgumentException

class MainActivtyViewModelFactory(private val dataSource: SelectedDao,
                                  private val application: Application
):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(
                dataSource,
                application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Exception")
    }
}