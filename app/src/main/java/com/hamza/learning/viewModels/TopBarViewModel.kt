package com.hamza.learning.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TopBarViewModel: ViewModel() {
    private var _screenTitle = MutableLiveData("")
    val screenTitle: LiveData<String>
        get() = _screenTitle

    fun setTitle(newTitle: String) {
        _screenTitle.value = newTitle
    }
}