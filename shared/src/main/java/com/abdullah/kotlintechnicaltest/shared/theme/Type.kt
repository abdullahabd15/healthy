package com.abdullah.kotlintechnicaltest.shared.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.abdullah.kotlintechnicaltest.shared.R

val Fonts = FontFamily(
    Font(R.font.gilroy_black),
    Font(R.font.gilroy_blackitalic, style = FontStyle.Italic),
    Font(R.font.gilroy_bold, weight = FontWeight.Bold),
    Font(R.font.gilroy_bolditalic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(R.font.gilroy_extrabold, weight = FontWeight.ExtraBold),
    Font(R.font.gilroy_extrabolditalic, weight = FontWeight.ExtraBold, style = FontStyle.Italic),
    Font(R.font.gilroy_light, weight = FontWeight.Light),
    Font(R.font.gilroy_lightitalic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(R.font.gilroy_medium, weight = FontWeight.Medium),
    Font(R.font.gilroy_mediumitalic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(R.font.gilroy_semibold, weight = FontWeight.SemiBold),
    Font(R.font.gilroy_semibolditalic, weight = FontWeight.SemiBold, style = FontStyle.Italic),
    Font(R.font.gilroy_thin, weight = FontWeight.Thin),
    Font(R.font.gilroy_thinitalic, weight = FontWeight.Thin, style = FontStyle.Italic)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Fonts,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 1.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)