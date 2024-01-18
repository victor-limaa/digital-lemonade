package com.example.digitallemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.digitallemonade.ui.theme.DigitalLemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigitalLemonadeTheme {
                // A surface container using the 'background' color from the theme
                DigitalLemonadeApp()
            }
        }
    }
}

@Preview
@Composable
fun DigitalLemonadeApp() {
    LemonadeTitle(modifier = Modifier
        .fillMaxWidth()
        .height(48.dp)
        .background(color = Color.Yellow))
    DigitalLemonadeContent(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))
}

@Composable
fun LemonadeTitle(modifier: Modifier = Modifier) {
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Lemonade", fontSize = 24.sp)
    }
}

@Composable
fun DigitalLemonadeContent(modifier: Modifier = Modifier) {

    var step by remember {
        mutableStateOf(1)
    }
    var manyTimesTipeLemon = (2..4).random()

    fun changeStep () {
        if(step >= 4) {
            step =  1
        } else {
            if(step === 2 && manyTimesTipeLemon > 1) {
                manyTimesTipeLemon--
                return
            }
            step++
        }
    }

    val imageResource = when (step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val imageDescription = when (step) {
        1 -> R.string.tree_image_description
        2 -> R.string.lemon_image_description
        3 -> R.string.lemonade_image_description
        else -> R.string.empty_image_description
    }

    val contentText = when (step) {
        1 -> R.string.tree_content_description
        2 -> R.string.lemon_content_description
        3 -> R.string.lemonade_content_description
        else -> R.string.empty_content_description
    }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { changeStep() }, shape = RoundedCornerShape(16.dp)) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = stringResource(imageDescription)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(contentText), fontSize = 18.sp)
    }
}