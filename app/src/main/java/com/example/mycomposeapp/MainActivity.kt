package com.example.mycomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycomposeapp.ui.theme.MyComposeAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            // image card ui
            val painter = painterResource(id = R.drawable.snow)
            val contentDescription = "This is bird image"
            val title = "Hello, this is my first ui using compose"
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(.7f)
                        .padding(16.dp)
                ) {
                    ImageCard(
                        painter = painter,
                        contentDescription = contentDescription,
                        title = title
                    )
                }

                Spacer(Modifier.height(20.dp))

                // textview styling
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF101010))
                        .padding(10.dp)
                ) {
                    Greeting("Jetpack Compose")
                }
                Spacer(Modifier.height(20.dp))

                EnterText()
            }

        }
    }
}

@Composable
fun EnterText(){
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    var textFieldState by remember {
        mutableStateOf("")
    }
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        scaffoldState = scaffoldState)
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
        )
        {
            TextField(
                value = textFieldState,
                label = {
                        Text("Enter your name")
                },
                onValueChange = {
                    textFieldState = it
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(5.dp))

            Button(onClick = {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Hello $textFieldState")
                }

            }) {
                Text("Click me")

            }

        }
    }
}


@Composable
fun ImageCard(
    painter : Painter,
    contentDescription : String,
    title : String,
    modifier : Modifier = Modifier
){
   Card(
       modifier = modifier.fillMaxWidth(),
       shape = RoundedCornerShape(15.dp),
       elevation = 5.dp
   ){
       Box(modifier = Modifier.height(250.dp)){
           Image(
               painter = painter,
               contentDescription = contentDescription,
               contentScale = ContentScale.Crop
           )
           Box(modifier = Modifier
               .fillMaxSize()
               .background(
                   Brush.verticalGradient(
                       colors = listOf(
                           Color.Transparent,
                           Color.Black
                       ),
                       startY = 300f
                   )
               ))
           Box(
               modifier = Modifier
                   .fillMaxSize()
                   .padding(12.dp),
               contentAlignment = Alignment.BottomStart
           )
           {
               Text(title,style = TextStyle(color = Color.White, fontSize = 16.sp) )

           }

       }

   }

}

@Composable
fun Greeting(name: String) {
    Text(
        text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color.Green,
                                            fontSize = 50.sp
                                        )
                                    ){
                                        append("J")
                                    }
            append("etpack ")
            withStyle(
                style = SpanStyle(
                    color = Color.Green,
                    fontSize = 50.sp
                )
            ){
                append("C")
            }
            append("ompose ")

        },
    color = Color.White,
    fontSize = 30.sp,
    fontFamily = FontFamily(Font(R.font.opensans_bold)),
    fontStyle = FontStyle.Italic,
    fontWeight = FontWeight.Bold,
    textDecoration = TextDecoration.Underline
        )
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//        Greeting("Android")
//}