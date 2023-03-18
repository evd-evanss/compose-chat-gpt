package com.sugarspoon.chatgpt.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrightnessMedium
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sugarspoon.chatgpt.R
import com.sugarspoon.chatgpt.components.text.HeadingLevel1
import com.sugarspoon.chatgpt.theme.OtpViewTheme
import com.sugarspoon.chatgpt.ui.chat.ChatScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var appTheme by rememberSaveable { mutableStateOf<Boolean?>(null) }
            val appThemeFinal = appTheme ?: !isSystemInDarkTheme()

            OtpViewTheme(appThemeFinal) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                HeadingLevel1(
                                    text = stringResource(id = R.string.app_name),
                                    modifier = Modifier
                                )
                            },
                            actions = {
                                IconButton(
                                    onClick = {
                                        appTheme = !appThemeFinal
                                    }
                                ) {
                                    Icon(Icons.Default.BrightnessMedium, contentDescription = null)
                                }
                            },
                            navigationIcon = {
                                Image(
                                    contentDescription = null,
                                    modifier = Modifier.padding(start = 16.dp).size(24.dp),
                                    painter = painterResource(id = R.drawable.chat_logo)
                                )
                            }
                        )
                    },
                ) { paddingValues ->
                    paddingValues.apply { ChatScreen() }
                }
            }
        }
    }
}