package com.sugarspoon.chatgpt.components.message

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.sugarspoon.chatgpt.components.shape.BubbleShape
import com.sugarspoon.chatgpt.components.text.Body2
import com.sugarspoon.chatgpt.theme.Dimens.CornerBubble
import com.sugarspoon.chatgpt.theme.Dimens.FontSmall
import com.sugarspoon.chatgpt.theme.Dimens.SpacingX
import com.sugarspoon.chatgpt.theme.Dimens.SpacingXS
import com.sugarspoon.chatgpt.theme.Dimens.SpacingXXS
import com.sugarspoon.chatgpt.theme.FountainBlue
import com.sugarspoon.chatgpt.theme.Typography

/**
 * BubbleMessage
 *
 * @author Evandro Costa
 *
 * @param message Model of message [MessageModel]
 *
 */
@Composable
fun BubbleMessage(
    message: MessageModel
) {
    val backgroundColor = if (message.isResponse) FountainBlue else MaterialTheme.colors.secondary
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(horizontal = SpacingXXS, vertical = SpacingXS)
            .clip(BubbleShape(CornerBubble, message.isResponse))
            .background(backgroundColor)
    ) {
        Row(modifier = Modifier.wrapContentWidth()) {
            Text(
                text = message.name,
                style = Typography.h1.copy(fontSize = FontSmall),
                modifier = Modifier.padding(
                    start = SpacingXXS,
                    end = SpacingXXS,
                    top = SpacingXS
                )
            )
            Text(
                text = message.time,
                style = Typography.body2.copy(fontSize = FontSmall),
                modifier = Modifier.padding(
                    start = SpacingXXS,
                    end = SpacingXXS,
                    top = SpacingX
                )
            )
        }
        Body2(
            text = message.text,
            modifier = Modifier.padding(
                start = SpacingXXS,
                end = SpacingXXS,
                bottom = SpacingX,
                top = SpacingX,
            )
        )

    }
}

data class MessageModel(
    val text: String = "Meu nome é Tulio",
    val time: String = "",
    val name: String = "User",
    val isResponse: Boolean = false
)

@Preview(
    showBackground = true
)
@Composable
fun BubblePreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        BubbleMessage(MessageModel())
        BubbleMessage(MessageModel())
    }
}