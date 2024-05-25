package com.grupo3.vinilos.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.grupo3.vinilos.R
import com.grupo3.vinilos.ui.theme.UiPadding

class Option(val _label:String, val _value: String){
    var label: String = ""
        get() {
            return this._label
        }
    var value: String = ""
        get() {
            return this._value
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownList(
    options: List<Option>,
    title:String,
    placeholder:String,
    label:String,
    onClick: (option:Option) -> Unit,
    testTag: String?
    ){
    var value by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().testTag(testTag ?: ""),
        value  = value,
        onValueChange = {},
        singleLine = true,
        readOnly = true,
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        trailingIcon = {
            IconButton(
                onClick = {
                    showDialog = true
                },
            ) {
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        }
    )
    if (showDialog) {

        DatePickerDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {},
            dismissButton = {
                Button(
                    onClick = { showDialog = false }
                ) {
                    Text(text = "Cancel")
                }
            },
        ) {
            Box(modifier = Modifier
                .padding(10.dp)
                .height(250.dp),  contentAlignment = Alignment.Center, ) {
                Column (modifier = Modifier.fillMaxWidth()) {
                    Text(text = title, style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                    LazyColumn(
                        Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(
                                top = UiPadding.medium,
                                start = UiPadding.large,
                                end = UiPadding.large,
                            ),
                        verticalArrangement = Arrangement.Center
                    ){
                        items(options.size){ index ->
                            var option = options.get(index = index)
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)) {
                                TextButton(onClick = { value = option.label; showDialog = false; onClick(option) }, colors =  ButtonDefaults.textButtonColors(contentColor = Color.Black) ) {
                                    Text (modifier = Modifier.fillMaxWidth(), text = option.label, textAlign = TextAlign.Center)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}