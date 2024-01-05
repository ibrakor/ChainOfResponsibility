package com.ibrakor.chainofresponsibility

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ibrakor.chainofresponsibility.handlers.CharacterPasswordValidator
import com.ibrakor.chainofresponsibility.handlers.EmptyPasswordValidator
import com.ibrakor.chainofresponsibility.handlers.LengthPasswordValidator
import com.ibrakor.chainofresponsibility.handlers.LowerCasePasswordValidator
import com.ibrakor.chainofresponsibility.handlers.NumberPasswordValidator
import com.ibrakor.chainofresponsibility.handlers.UpperCasePasswordValidator
import com.ibrakor.chainofresponsibility.ui.theme.ChainOfResponsibilityTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChainOfResponsibilityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

    val handlers = listOf(
        EmptyPasswordValidator(),
        LengthPasswordValidator(),
        CharacterPasswordValidator(),
        NumberPasswordValidator(),
        LowerCasePasswordValidator(),
        UpperCasePasswordValidator()
    )
    val validatorChain = PasswordValidatorChain(handlers)



@Composable
fun MainScreen() {
    val password = remember {
        mutableStateOf("")
    }
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)

    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Es segura tu contraseña?", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(vertical = 20.dp))
                Text(
                    text = "Requisitos:" +
                            "\nMínimo 8 caracteres\nEl simbolo: @\nMínimo un número\nMínimo una mayúscula\nMínimo una minúscula",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(vertical = 50.dp, horizontal = 20.dp)
                )
                PasswordTextField(password.value) { fieldPassword ->
                    password.value = fieldPassword
                }
                //ButtonCheckPassword()
                ImageCheckPassword(password.value)
            }

        }


    }


}
@Composable
fun ImageCheckPassword(password: String) {
    if (validatorChain.validate(password)){
        Image(painter = painterResource(R.drawable.ic_true), contentDescription = "contraseña segura", Modifier.size(200.dp))
        Text(text = "Contraseña segura", color = Color.Green,style = MaterialTheme.typography.headlineSmall)
    } else {
        Image(
            painter = painterResource(R.drawable.ic_false),
            contentDescription = "contraseña no segura",
            Modifier.size(200.dp)
        )
        Text(text = "Contraseña NO segura", color = Color.Red, style = MaterialTheme.typography.headlineSmall)
    }
}

@Composable
fun PasswordTextField(
    password: String,
    onPasswordChanged: (String) -> Unit
) {
    TextField(
        value = password,
        onValueChange = onPasswordChanged,
        modifier = Modifier.wrapContentSize(),
        label = {
            Text(
                text = "Introduzca una contraseña"
            )
        })
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    ChainOfResponsibilityTheme {
        MainScreen()
    }
}