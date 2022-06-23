package com.tenqube.reward.domain.ui

import com.tenqube.webui.dto.OpenNewViewDto
import com.tenqube.webui.dto.ShowConfirmDto
import com.tenqube.webui.dto.ShowDatePickerDto
import com.tenqube.webui.dto.ShowSelectBoxDto
import com.tenqube.webui.dto.ShowTimePickerDto

interface UIService {
    fun finish()

    fun onClickSound()

    fun onPageLoaded()

    fun setRefreshEnabled(enabled: Boolean)

    fun showToast(msg: String)

    fun showConfirm(request: ShowConfirmDto)

    fun showSelectBox(request: ShowSelectBoxDto)

    fun openNewView(request: OpenNewViewDto)

    fun showDatePicker(request: ShowDatePickerDto)

    fun showTimePicker(request: ShowTimePickerDto)
}