package com.tenqube.reward

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tenqube.reward.bridge.dto.request.*

class MainViewModel : ViewModel() {
    private val _url = MutableLiveData<String>()
    val url: LiveData<String> = _url

    fun start(url: String) {
        _url.value = url
    }

    fun finish() {

    }

    fun onClickSound() {

    }

    fun onPageLoaded() {

    }

    fun setRefreshEnabled(enabled: Boolean) {

    }

    fun showToast(message: String) {

    }

    fun showConfirm(request: ShowConfirmDto) {

    }

    fun showSelectBox(request: ShowSelectBoxDto) {

    }

    fun openNewView(request: OpenNewViewDto) {

    }

    fun showDatePicker(request: ShowDatePickerDto) {

    }

    fun showTimePicker(request: ShowTimePickerDto) {

    }
}