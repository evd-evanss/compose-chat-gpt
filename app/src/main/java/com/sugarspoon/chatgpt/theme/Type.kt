package com.sugarspoon.chatgpt.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.style.TextAlign
import com.sugarspoon.chatgpt.R
import com.sugarspoon.chatgpt.theme.Dimens.FontBig
import com.sugarspoon.chatgpt.theme.Dimens.FontMedium
import com.sugarspoon.chatgpt.theme.Dimens.FontVerySmall

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Font(R.font.poppins_medium).toFontFamily(),
        fontSize = FontMedium,
        textAlign = TextAlign.Left,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        )
    ),
    body2 = TextStyle(
        fontFamily = Font(R.font.poppins_medium).toFontFamily(),
        fontSize = FontMedium,
        textAlign = TextAlign.Left,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        )
    ),
    h1 = TextStyle(
        fontFamily = Font(R.font.poppins_bold).toFontFamily(),
        fontSize = FontBig,
        textAlign = TextAlign.Left,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        )
    ),
    h2 = TextStyle(
        fontFamily = Font(R.font.poppins_bold).toFontFamily(),
        fontSize = FontMedium,
        textAlign = TextAlign.Left,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        )
    ),
    h3 = TextStyle(
        fontFamily = Font(R.font.poppins_bold).toFontFamily(),
        fontSize = FontVerySmall,
        textAlign = TextAlign.Left,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        )
    )
)