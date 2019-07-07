package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {

        if (fullName.isNullOrBlank())
            return Pair(null, null)

        val parts: List<String>? = fullName.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

        return firstName to lastName
    }


    /**
     * TODO ОШИБКА
     */
    fun transliteration(fullName: String?, divider: String = " "): String {

        val dictionary = mutableMapOf(
            "а" to "a", "А" to "A",
            "б" to "b", "Б" to "B",
            "в" to "v", "В" to "V",
            "г" to "g", "Г" to "G",
            "д" to "d", "Д" to "D",
            "е" to "e", "Е" to "E",
            "ё" to "e", "Ё" to "E",
            "ж" to "zh", "Ж" to "Zh",
            "з" to "z", "З" to "Z",
            "и" to "i", "И" to "I",
            "й" to "i", "Й" to "I",
            "к" to "k", "К" to "K",
            "л" to "l", "Л" to "L",
            "м" to "m", "М" to "M",
            "н" to "n", "Н" to "N",
            "о" to "o", "О" to "O",
            "п" to "p", "П" to "P",
            "р" to "r", "Р" to "R",
            "с" to "s", "С" to "S",
            "т" to "t", "Т" to "T",
            "у" to "u", "У" to "U",
            "ф" to "f", "Ф" to "F",
            "х" to "h", "Х" to "H",
            "ц" to "c", "Ц" to "C",
            "ч" to "ch", "Ч" to "Ch",
            "ш" to "sh", "Ш" to "Sh",
            "щ" to "sh'", "Щ" to "Sh'",
            "ъ" to "", "Ъ" to "",
            "ы" to "i", "Ы" to "I",
            "ь" to "", "Ь" to "",
            "э" to "e", "Э" to "E",
            "ю" to "yu", "Ю" to "Yu",
            "я" to "ya", "Я" to "Ya"
        )

        val (firstName, lastName) = parseFullName(fullName)

        var transFirstName = ""
        var transLastName = ""

        if (!firstName.isNullOrBlank()) {

            for (it in firstName.toCharArray()) {
                if (dictionary.containsKey(it.toString())) {
                    transFirstName += dictionary.get(it.toString())
                }
                else {
                    transFirstName += it.toString()
                }
            }
        }

        if (!lastName.isNullOrBlank()) {

            for (it in lastName.toCharArray()) {
                if (dictionary.containsKey(it.toString())) {
                    transLastName += dictionary.get(it.toString())
                }
                else {
                    transLastName += it.toString()
                }
            }
        }

        if (transFirstName.isBlank() || transLastName.isBlank())
            return "$transFirstName$transLastName"

        return "$transFirstName$divider$transLastName"
    }


    fun toInitials(firstName: String?, lastName: String?): String? {
        var initials = ""

        if (!firstName.isNullOrBlank()) {
            initials += "${firstName.capitalize().get(0)}"
        }
        if (!lastName.isNullOrBlank()) {
            initials += "${lastName.capitalize().get(0)}"
        }
        if (firstName.isNullOrBlank() && lastName.isNullOrBlank())
            return null

        return initials
    }
}