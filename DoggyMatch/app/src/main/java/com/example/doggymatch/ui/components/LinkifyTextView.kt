package com.example.doggymatch.ui.components

import android.text.util.Linkify
import android.widget.TextView
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.util.LinkifyCompat

@Composable
fun LinkifyTextView(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    color: Color = MaterialTheme.colorScheme.secondary
) {
    val linkColor = Color(0xFF0000EE).toArgb()
    val textColor = color.toArgb()

    AndroidView(
        modifier = modifier,
        factory = { context ->
            TextView(context).apply {
                this.text = text
                this.setTextColor(textColor)
                this.textSize = style.fontSize.value
                // Set link color before applying links
                this.setLinkTextColor(linkColor)
                // Use LinkifyCompat for better compatibility
                LinkifyCompat.addLinks(this, Linkify.WEB_URLS)
                this.linksClickable = true
            }
        },
        update = { textView ->
            textView.text = text
            textView.setTextColor(textColor)
            // Reapply links in case text has changed
            LinkifyCompat.addLinks(textView, Linkify.WEB_URLS)
        }
    )
}