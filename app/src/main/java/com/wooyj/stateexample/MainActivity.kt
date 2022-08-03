@file:OptIn(ExperimentalMaterial3Api::class)

package com.wooyj.stateexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wooyj.stateexample.ui.theme.StateExampleTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wooyj.stateexample.compose.WellnessTasksList
import com.wooyj.stateexample.data.WellnessTask
import com.wooyj.stateexample.viewmodel.WellnessViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WellnessScreen()
                }
            }
        }
    }
}

/**
 *
 * "State is. Event happen."
 *
 * update state, display state, event가 loop를 돔.
 *
 * - Event - An event is generated by the user or another part of the program.
 * - Update State - An event handler changes the state that is used by the UI.
 * - Display State - The UI is updated to display the new state.
 *
 */
@Composable
fun WaterCounter(modifier: Modifier = Modifier) {

    Column(modifier = modifier.padding(16.dp)) {
//        var count by remember { mutableStateOf(0) }
        // [rememberSaveable]
        // - retained across configuration changes
        // - rememberSaveable automatically saves any value that can be saved in a Bundle
        var count by rememberSaveable { mutableStateOf(0) }
        if (count > 0) {
            Text("$count 잔을 마셨습니다!")
        }
        Button(onClick = { count++ }, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("추가하기")
        }
    }


    // - var count = 0
    // - remember : composition의 하나의 객체.
//    Column(modifier = modifier.padding(16.dp)) {
//        var count by remember { mutableStateOf(0) }
//
//        if (count > 0) {
//            var showTask by remember { mutableStateOf(true) }
//            if (showTask) {
//                WellnessTaskItem(taskName = "오늘 15분 걸으셨나요?", onClose = { showTask = false })
//            }
//            Text(
//                text = "$count 잔을 마셨습니다!",
//            )
//        }
///
//        Row(modifier = Modifier.padding(top = 8.dp)) {
//            Button(onClick = { count++ }, enabled = count < 10) {
//                Text(text = "추가하기")
//            }
//            Button(onClick = { count = 0 }, Modifier.padding(start = 8.dp)) {
//                Text("초기화")
//            }
//        }
//
//    }
}


@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("$count 잔을 마셨습니다!")
        }
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("추가하기")
        }
    }
}


@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
//    var count by rememberSaveable {
//        mutableStateOf(0)
//    }
//    StatelessCounter(count = count, onIncrement = { count++ }, modifier)
    var waterCounter by rememberSaveable {
        mutableStateOf(0)
    }
//    var juiceCounter by rememberSaveable {
//        mutableStateOf(0)
//    }
    Column {
        StatelessCounter(count = waterCounter, onIncrement = { waterCounter++ })
//        StatelessCounter(count = juiceCounter, onIncrement = { juiceCounter++ })
    }
}


@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    wellnessViewModel: WellnessViewModel = viewModel()
) {
//    WaterCounter(modifier)
    Column(modifier = modifier) {
        StatefulCounter(modifier = modifier)
//        val list = remember { getWellnessTasks().toMutableStateList() }
        //  rememberSaveable은 bundle에 저장할 수있는 객체만 저장가능하다.
        //  => custom saver를 만들거나 viewModel로 state를 저장
        WellnessTasksList(
            list = wellnessViewModel.tasks,
            onCheckedTask = { task, checked ->
                wellnessViewModel.changeTaskChecked(task, checked)
            },
            onCloseTask = { task -> wellnessViewModel.remove(task) })
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StateExampleTheme {
        WellnessScreen()
    }
}