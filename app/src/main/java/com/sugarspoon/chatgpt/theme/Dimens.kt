package com.sugarspoon.chatgpt.theme

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object Dimens {

    @FieldGuideline("32dp")
    val SpacingXXXS = 32.dp

    @FieldGuideline("16dp")
    val SpacingXXS = 16.dp
    @FieldGuideline("8dp")
    val SpacingXS = 8.dp
    @FieldGuideline("4dp")
    val SpacingX = 4.dp

    @FieldGuideline("8sp")
    val FontVerySmall = 8.sp

    @FieldGuideline("10sp")
    val FontSmall = 10.sp

    @FieldGuideline("16sp")
    val FontMedium = 16.sp

    @FieldGuideline("20sp")
    val FontBig = 20.sp

    @FieldGuideline("32f")
    val CornerBubble = 32f

    @FieldGuideline("24dp")
    val RadiusLarge = 24.dp
}

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.TYPE,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER,
)
annotation class FieldGuideline(val info: String)