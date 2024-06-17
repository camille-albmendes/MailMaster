package br.com.fiap.mailmaster.service

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract.Events


fun addEvent(ctx: Context, title: String) {
    val intent = Intent(Intent.ACTION_INSERT).apply {
        data = Events.CONTENT_URI
        putExtra(Events.TITLE, title)
        putExtra(Events.ALL_DAY, true)
    }

    ctx.startActivity(intent)
}