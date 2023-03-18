package com.sugarspoon.chatgpt.components.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sugarspoon.chatgpt.R
import com.sugarspoon.chatgpt.anim.AnimatedPlaceholder
import com.sugarspoon.chatgpt.theme.DarkGrey
import com.sugarspoon.chatgpt.theme.Dimens.RadiusLarge
import com.sugarspoon.chatgpt.theme.Dimens.SpacingXS
import com.sugarspoon.chatgpt.theme.Dimens.SpacingXXS
import com.sugarspoon.chatgpt.theme.Dimens.SpacingXXXS
import com.sugarspoon.chatgpt.theme.Honeydew
import com.sugarspoon.chatgpt.theme.Pistachio
import com.sugarspoon.chatgpt.theme.Typography

@Composable
fun TextFieldGpt(
    modifier: Modifier,
    textInput: String,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        backgroundColor = Honeydew
    ),
    onSendClicked: () -> Unit,
    onValueChange: (String) -> Unit,
) {
    val backgroundColor = if(MaterialTheme.colors.isLight) {
        Color.White
    } else {
        DarkGrey
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .wrapContentHeight()
            .shadow(
                elevation = 24.dp,
                spotColor = MaterialTheme.colors.primaryVariant
            )
            .background(backgroundColor)
    ) {
        AnimatedPlaceholder(
            hints = listOf(
                stringResource(id = R.string.question_title_first),
                stringResource(id = R.string.question_title_last),
            )
        ) { text ->
            Text(
                text = text,
                modifier = Modifier.padding(start = SpacingXXXS, top = SpacingXS, end = SpacingXXXS),
                style = Typography.h2.copy(
                    color = MaterialTheme.colors.primary,
                    fontSize = 14.sp
                )
            )
        }

        OutlinedTextField(
            value = textInput,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = SpacingXXS,
                    end = SpacingXXS,
                    top = SpacingXS,
                    bottom = SpacingXXS,
                )
                .wrapContentHeight(),
            shape = RoundedCornerShape(RadiusLarge),
            trailingIcon = {
                IconButton(onClick = onSendClicked) {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        "",
                        tint = Pistachio,
                        modifier = Modifier
                            .padding(bottom = 12.dp)
                            .rotate(-45f)
                    )
                }
            },
            colors = colors,
            maxLines = 8,
            textStyle = Typography.body1.copy(
                textAlign = TextAlign.Left,
                color = DarkGrey
            )
        )
    }
}

@Composable
@Preview
fun ChatPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val message = remember {
            mutableStateOf("")
        }

        TextFieldGpt(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(0.dp),
            textInput = message.value,
            onValueChange = {
                message.value = it
            },
            onSendClicked = {}
        )

    }
}
