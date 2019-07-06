package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR


fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}


/**
 * Extension function to format data into human-friendly type
 */
fun Date.humanizeDiff(date: Date = Date()): String {

    val diff = (date.time - this.time)

    return when (Math.abs(diff / SECOND).toInt()) {

        0 -> "только что"

        in 1..45 ->
            if (diff / SECOND > 0) "несколько секунд назад"
            else "через несколько секунд"

        in 45..75 ->
            if (diff / SECOND > 0) "минуту назад"
            else "через минуту"

        else -> {
            when (Math.abs(diff / MINUTE).toInt()) {

                in 1..44 ->
                    if (diff / MINUTE > 0) "${TimeUnits.MINUTE.plural((diff / MINUTE).toInt())} назад"
                    else "через ${TimeUnits.MINUTE.plural(-(diff / MINUTE).toInt())}"

                in 45..75 ->
                    if (diff / MINUTE > 0) "час назад"
                    else "через час"

                else -> {
                    when (Math.abs(diff / HOUR).toInt()) {

                        in 1..21 ->
                            if (diff / HOUR > 0) "${TimeUnits.HOUR.plural((diff / HOUR).toInt())} назад"
                            else "через ${TimeUnits.HOUR.plural(-(diff / HOUR).toInt())}"

                        in 22..26 ->
                            if (diff / HOUR > 0) "день назад"
                            else "через день"

                        else -> {
                            when (Math.abs(diff / DAY).toInt()) {

                                in 1..359 ->
                                    if (diff / DAY > 0) "${TimeUnits.DAY.plural((diff / DAY).toInt())} назад"
                                    else "через ${TimeUnits.DAY.plural(-(diff / DAY).toInt())}"

                                else ->
                                    if (diff / DAY > 0) "более года назад"
                                    else "более чем через год"
                            }
                        }
                    }
                }
            }
        }

    }
}


fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}


enum class TimeUnits {

    SECOND, MINUTE, HOUR, DAY;

    fun plural(value: Int): String {
        return when (value % 100) {
            in 10..19 -> "$value ${
            when (this) {
                SECOND -> "секунд"
                MINUTE -> "минут"
                HOUR -> "часов"
                DAY -> "дней"
            }
            }"
            else -> {
                when (value % 10) {
                    0, in 5..9 -> "$value ${
                    when (this) {
                        SECOND -> "секунд"
                        MINUTE -> "минут"
                        HOUR -> "часов"
                        DAY -> "дней"
                    }
                    }"

                    in 2..4 -> "$value ${
                    when (this) {
                        SECOND -> "секунды"
                        MINUTE -> "минуты"
                        HOUR -> "часа"
                        DAY -> "дня"
                    }
                    }"

                    else -> "$value ${
                    when (this) {
                        SECOND -> "секунду"
                        MINUTE -> "минуту"
                        HOUR -> "час"
                        DAY -> "день"
                    }
                    }"
                }
            }
        }
    }

}