package com.grupo3.vinilos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.grupo3.vinilos.ui.theme.Accent
import com.grupo3.vinilos.ui.theme.Primary

@Composable
fun LogInScreen(
    navController: NavController
){
    Column(
        Modifier
            .padding(all = 16.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { navController.navigate("Home") }, colors = ButtonDefaults.buttonColors(
            containerColor = Primary,
        ),) {
            Text(text = "Visitante")
        }
       Button(
           onClick = { navController.navigate("Home?auth=${true}") },
           colors = ButtonDefaults.buttonColors(
               containerColor = Accent,
           ),
       ) {
           Text(text = "Coleccionista")
       }
        
    }
}