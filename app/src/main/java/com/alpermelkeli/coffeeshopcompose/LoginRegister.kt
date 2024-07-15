package com.alpermelkeli.coffeeshopcompose
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alpermelkeli.coffeeshopcompose.components.NextButton
import com.alpermelkeli.coffeeshopcompose.components.TextFieldValidation
import com.alpermelkeli.coffeeshopcompose.model.User
import com.alpermelkeli.coffeeshopcompose.repository.UserRepository
import com.alpermelkeli.coffeeshopcompose.ui.theme.CoffeeShopComposeTheme
import com.alpermelkeli.coffeeshopcompose.ui.theme.White50
import com.alpermelkeli.coffeeshopcompose.util.isEmailValid
import com.alpermelkeli.coffeeshopcompose.util.isPasswordValid
import com.alpermelkeli.coffeeshopcompose.viewmodel.UserViewModel

class LoginRegister : ComponentActivity() {
    private val userViewModel = UserViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoffeeShopComposeTheme {

                val loginResult = userViewModel.loginResult
                val error = userViewModel.error

                loginResult.observeAsState().value?.let { result ->
                    if (result) {
                        intent = Intent(this, HomeScreen::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
                error.observeAsState().value?.let {
                    if(it.isNotBlank()) Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                }

                LoginRegisterScreen(userViewModel)
            }
        }
    }


}

@Composable
fun LoginRegisterScreen(userViewModel: UserViewModel) {

    val email = remember{ mutableStateOf("") }

    val emailState = remember{ mutableStateOf(false) }

    val password = remember{ mutableStateOf("") }

    val passwordState = remember{ mutableStateOf(false) }



    Column(modifier = Modifier
        .fillMaxSize()
        .background(White50),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 70.dp),
            text = "Giriş yap",
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
        Image(imageVector = ImageVector.vectorResource(id = R.drawable.coffee_icon), contentDescription ="coffee icon", modifier = Modifier
            .size(100.dp)
            .padding(top = 50.dp))

        Spacer(modifier = Modifier.size(150.dp))

        TextFieldValidation(
            type = "E-mail",
            leadingIconVector = Icons.Default.Email,
            text = email.value,
            onValueChange = {email.value = it
                emailState.value = isEmailValid(it)
                            },
            isValid = emailState,
            isPrivate = false
        )
        Spacer(modifier = Modifier.size(25.dp))

        TextFieldValidation(
            type = "Password",
        leadingIconVector = Icons.Default.Lock,
        text = password.value,
        onValueChange = {password.value = it
            passwordState.value = isPasswordValid(it)
        },
        isValid = passwordState,
        isPrivate = true
        )
        Spacer(modifier = Modifier.size(25.dp))
        NextButton(onClick = {if(emailState.value && passwordState.value)userViewModel.login(email.value,password.value)}, modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(65.dp), text = "Giriş Yap",
            enabled = emailState.value && passwordState.value
            )

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoffeeShopComposeTheme {

    }
}