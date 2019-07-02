package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.humanizeDiff
import java.util.*

class TextMessage (
    from: User?,
    chat: Chat,
    isIncoming: Boolean = false,
    date: Date = Date(),
    var text: String?
) : BaseMessage(from, chat, date, isIncoming) {

    override fun formatMessage(): String = "${from?.firstName}" +
            "${ if (isIncoming) " получил" else " отправил" } сообщение \"$text\" ${date.humanizeDiff()}"

}