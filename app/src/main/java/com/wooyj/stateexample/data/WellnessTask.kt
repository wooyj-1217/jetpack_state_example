package com.wooyj.stateexample.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 *
 * Created on 2022/08/03.
 *
 * Description:
 *
 * @author wooyj
 *
 */
class WellnessTask(
    val id: Int,
    val label: String,
    initialChecked : Boolean = false
){
    var checked by mutableStateOf(initialChecked)
}