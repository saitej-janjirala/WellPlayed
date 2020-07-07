package com.saitejajanjirala.wellplayed.activities.viewstadium

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saitejajanjirala.wellplayed.database.SelectedDao


class ViewActivtyviewModelFactory(private val dataSource: SelectedDao,
                                  private val application: Application
):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ViewActivityViewModel::class.java)){
            return ViewActivityViewModel(dataSource,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Exception")
    }

}