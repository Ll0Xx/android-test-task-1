package com.antont.android_test_task_1.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.antont.android_test_task_1.R


class HomeViewModel(private val application: Application) : AndroidViewModel(application) {
    private val _text = MutableLiveData<String>().apply {
        value = application.getString(R.string.home_fragment_text)
    }
    val text: LiveData<String> = _text
}


class HomeViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(application) as T;
    }
}