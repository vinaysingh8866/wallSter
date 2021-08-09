package com.jav1001.vinaysingh.wallster.Main

import android.util.Log
import androidx.lifecycle.*
import com.jav1001.vinaysingh.wallster.data.MainRepository
import com.jav1001.vinaysingh.wallster.data.WallPaperInfo
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class MainViewModel (
    private val repository: MainRepository
    ) : ViewModel() {

    val wallPapers = repository.wallpaperInfos
    private val _navigateToDetails = MutableLiveData<WallPaperInfo>()
    val navigateToDetails: LiveData<WallPaperInfo?> get() = _navigateToDetails

    init {
        viewModelScope.launch {
            repository.loadWallpaper()
            Log.d("wallaperApi Return",repository.wallpaperInfos.toString())

        }

    }
}
class MainViewModelFactory(private val repository: MainRepository): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }

        throw IllegalArgumentException("Invalid View Model")
    }
}

