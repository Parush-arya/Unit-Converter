package com.example.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sample.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    greeting()
                }
            }
        }
    }
}

@Composable
fun greeting() {
    val context = LocalContext.current
    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf(1.0) }
    var outputUnit by remember { mutableStateOf(1.0) }
    var inputExpanded by remember { mutableStateOf(false) }
    var outputExpanded by remember { mutableStateOf(false) }
    var conversionFactor = 1.0; // = remember { mutableStateOf(1.0) }
    conversionFactor = inputUnit / outputUnit
    if (input.equals("")) {
        output = ""
    } else {
        output = (input.toDouble() * conversionFactor).toString()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(value = input.toString(), onValueChange = { input = it })
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(value = output.toString(), onValueChange = { output = it }, readOnly = true)

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                Box(modifier = Modifier.padding(top = 10.dp)) {
                    Button(onClick = { inputExpanded = true }) {
                        Text("Unit 1")
                        Icon(Icons.Default.Home, "")
                        DropdownMenu(
                            expanded = inputExpanded,
                            onDismissRequest = { inputExpanded = false },
                        ) {
                            DropdownMenuItem(
                                text = { Text("cm") },
                                onClick = {
                                    inputUnit = 1.0
                                    inputExpanded = false
                                },
                            )
                            DropdownMenuItem(
                                text = { Text("m") },
                                onClick = {
                                    inputUnit = 100.0
                                    inputExpanded = false
                                },
                            )
                            DropdownMenuItem(
                                text = { Text("km") },
                                onClick = {
                                    inputUnit = 100000.0
                                    inputExpanded = false
                                },
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(10.dp))

                Box(modifier = Modifier.padding(top = 10.dp)) {
                    Button(onClick = {
                        outputExpanded = true
                    }) {
                        Text("Unit 2")
                        Icon(Icons.Default.Home, "")
                        DropdownMenu(
                            expanded = outputExpanded,
                            onDismissRequest = { outputExpanded = false },
                        ) {
                            DropdownMenuItem(text = { Text("cm") }, onClick = {
                                outputUnit = 1.0
                                outputExpanded = false
                            })
                            DropdownMenuItem(text = { Text("m") }, onClick = {
                                outputUnit = 100.0
                                outputExpanded = false
                            })
                            DropdownMenuItem(text = { Text("km") }, onClick = {
                                outputUnit = 100000.0
                                outputExpanded = false
                            })
                        }
                    }
                }
            }

            Box {
                Text(text = "Result: ")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun greetingPreview() {
    greeting()
}
