package com.jav1001.vinaysingh.wallster.ui

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jav1001.vinaysingh.wallster.Main.MainViewModel
import com.jav1001.vinaysingh.wallster.data.MainRepository
import com.jav1001.vinaysingh.wallster.data.WallPaperInfo
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class FavoriteViewModel(private val repository: MainRepository):ViewModel() {
    val wallPapers:LiveData<List<WallPaperInfo>> = repository.wallpaperInfos

    //private val _navigateToDetails = MutableLiveData<WallPaperInfo>()
    //val navigateToDetails: LiveData<WallPaperInfo?> get() = _navigateToDetails

    init {
        viewModelScope.launch {

            //Log.d("wallaperApi Return",repository.wallpaperInfos.toString())
        }

    }
    fun loadWallpaper(){
        viewModelScope.launch {
            Log.d("incor","In co Routine")
            repository.loadWallpaper()
        }
    }
}



class FavoriteViewModelFactory(private val repository: MainRepository): ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(repository) as T
        }
        throw IllegalArgumentException("Invalid View Model")
    }
}

