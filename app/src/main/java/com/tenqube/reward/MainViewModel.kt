package com.tenqube.reward

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tenqube.reward.bridge.dto.request.*

class MainViewModel : ViewModel() {
    private val _url = MutableLiveData<String>()
    val url: LiveData<String> = _url

    private val _finish = MutableLiveData<Unit>()
    val finish: LiveData<Unit> = _finish

    private val _click = MutableLiveData<Unit>()
    val click: LiveData<Unit> = _click

    private val _onPageLoaded = MutableLiveData<Unit>()
    val onPageLoaded: LiveData<Unit> = _onPageLoaded

    private val _refreshEnabled = MutableLiveData<Boolean>()
    val refreshEnabled: LiveData<Boolean> = _refreshEnabled

    private val _showToast = MutableLiveData<String>()
    val showToast: LiveData<String> = _showToast

    private val _showConfirm = MutableLiveData<ShowConfirmDto>()
    val showConfirm: LiveData<ShowConfirmDto> = _showConfirm

    private val _showSelectBox = MutableLiveData<ShowSelectBoxDto>()
    val showSelectBox: LiveData<ShowSelectBoxDto> = _showSelectBox

    private val _openNewView = MutableLiveData<OpenNewViewDto>()
    val openNewView: LiveData<OpenNewViewDto> = _openNewView

    private val _showDatePicker = MutableLiveData<ShowDatePickerDto>()
    val showDatePicker: LiveData<ShowDatePickerDto> = _showDatePicker

    private val _showTimePicker = MutableLiveData<ShowTimePickerDto>()
    val showTimePicker: LiveData<ShowTimePickerDto> = _showTimePicker

    fun start(url: String) {
        _url.value = url
    }

    fun finish() {
        _finish.value = Unit
    }

    fun onClickSound() {
        _click.value = Unit
    }

    fun onPageLoaded() {
        _onPageLoaded.value = Unit
    }

    fun setRefreshEnabled(enabled: Boolean) {
        _refreshEnabled.value = enabled
    }

    fun showToast(message: String) {
        _showToast.value = message
    }

    fun showConfirm(request: ShowConfirmDto) {
        _showConfirm.value = request
    }

    fun showSelectBox(request: ShowSelectBoxDto) {
        _showSelectBox.value = request
    }

    fun openNewView(request: OpenNewViewDto) {
        _openNewView.value = request
    }

    fun showDatePicker(request: ShowDatePickerDto) {
        _showDatePicker.value = request
    }

    fun showTimePicker(request: ShowTimePickerDto) {
        _showTimePicker.value = request
    }
}