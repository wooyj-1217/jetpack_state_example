package com.wooyj.stateexample.viewmodel

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.wooyj.stateexample.data.WellnessTask

/**
 *
 * Created on 2022/08/03.
 *
 * wellness list값을 저장하기 위한 viewmodel
 *
 * @author wooyj
 *
 */
class WellnessViewModel : ViewModel() {

    private val _tasks = getWellnessTasks().toMutableStateList()
    val tasks: List<WellnessTask>
        get() = _tasks

    fun remove(item: WellnessTask) {
        _tasks.remove(item)
    }

    fun changeTaskChecked(item: WellnessTask, checked: Boolean) =
        tasks.find { it.id == item.id }?.let { task -> task.checked = checked }

    private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }

}