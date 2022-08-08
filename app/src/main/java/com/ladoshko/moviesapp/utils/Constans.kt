package com.ladoshko.moviesapp.utils

import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat

class Constans {
    object Screens{
        const val SPLASH_SCREEN = "splash_screen"
        const val MAIN_SCREEN = "main_screen"
        const val DETAILS_SCREEN = "details_screen"
    }
}
@Composable
fun HtmlText(html: String, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context -> TextView(context) },
        update = { it.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT) }
    )
}