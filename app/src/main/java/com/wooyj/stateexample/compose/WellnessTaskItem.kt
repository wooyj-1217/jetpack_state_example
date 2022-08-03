package com.wooyj.stateexample.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wooyj.stateexample.data.WellnessTask

/**
 *
 * Created on 2022/08/03.
 *
 * Description:
 *
 * @author wooyj
 * @since 3.1.4
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WellnessTaskItem(
    taskName: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = taskName
        )
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}

//@Composable
//fun WellnessTaskItem(
//    taskName: String,
//    modifier: Modifier = Modifier,
//    onChecked: () -> Unit,
//    onClose: () -> Unit
//) {
//
//    var checkedState by rememberSaveable {
//        mutableStateOf(false)
//    }
//
//    WellnessTaskItem(
//        taskName = taskName,
//        checked = checkedState,
//        onCheckedChange = { newValue: Boolean -> checkedState = newValue },
//        onClose = onClose,
//        modifier = modifier
//    )
//
//}

//@Composable
//fun WellnessTasksList(
//    modifier: Modifier = Modifier,
//    list: List<WellnessTask> = remember { getWellnessTasks() }
//) {
//    LazyColumn(
//        modifier = modifier
//    ) {
//        items(list) { task ->
//            WellnessTaskItem(taskName = task.label)
//        }
//    }
//}


//private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }

@Composable
fun WellnessTasksList(
    modifier: Modifier = Modifier,
    list: List<WellnessTask>,
    onCheckedTask: (WellnessTask, Boolean) -> Unit,
    onCloseTask: (WellnessTask) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = list,
            key = { task -> task.id }
        ) { task ->
            WellnessTaskItem(
                taskName = task.label,
                checked = task.checked,
                onCheckedChange = { checked -> onCheckedTask(task, checked) },
                onClose = { onCloseTask(task) })
        }
    }
}