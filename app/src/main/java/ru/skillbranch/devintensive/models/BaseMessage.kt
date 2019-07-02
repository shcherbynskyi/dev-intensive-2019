package ru.skillbranch.devintensive.models

import java.util.*

abstract class BaseMessage(
    val from: User?,
    val chat: Chat,
    val date: Date = Date(),
    val isIncoming: Boolean = false
) {

    abstract fun formatMessage(): String

    companion object abstractFactory {

        var lastId = -1

        fun makeMessage(from: User?, chat: Chat, date: Date = Date(), payload: Any?, type: String = "text", isIncoming: Boolean = false): BaseMessage {
            lastId++
            return when (type) {
                "image" -> ImageMessage(from, chat, date = date, image = payload as String, isIncoming = isIncoming)
                else -> TextMessage(from, chat, date = date, text = payload as String, isIncoming = isIncoming)
            }
        }
    }
}