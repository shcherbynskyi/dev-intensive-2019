package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

        return firstName to lastName
    }

    fun transliteration(fullName: String, divider: String = " "): String {
        val literas = mutableMapOf(Pair(String(), String()))

        literas["а"] = "a"
        literas["б"] = "b"
        literas["в"] = "v"
        literas["г"] = "g"
        literas["д"] = "d"
        literas["е"] = "e"
        literas["ё"] = "e"
        literas["ж"] = "zh"
        literas["з"] = "z"
        literas["и"] = "i"
        literas["й"] = "i"
        literas["к"] = "k"
        literas["л"] = "l"
        literas["м"] = "m"
        literas["н"] = "n"
        literas["о"] = "o"
        literas["п"] = "p"
        literas["р"] = "r"
        literas["с"] = "s"
        literas["т"] = "t"
        literas["у"] = "u"
        literas["ф"] = "f"
        literas["х"] = "h"
        literas["ц"] = "c"
        literas["ч"] = "ch"
        literas["ш"] = "sh"
        literas["щ"] = "sh'"
        literas["ъ"] = ""
        literas["ы"] = "i"
        literas["ь"] = ""
        literas["э"] = "e"
        literas["ю"] = "yu"
        literas["я"] = "ya"

        val (firstName, lastName) = parseFullName(fullName)

        firstName?.decapitalize()
        lastName?.decapitalize()

        var transFirstName = ""
        var transLastName = ""

        for (it in firstName!!.toCharArray()) {
            if (literas.containsKey(it.toLowerCase().toString())) {
                transFirstName += literas.get(it.toLowerCase().toString())
            }
            else {
                transFirstName += it.toLowerCase().toString()
            }
        }

        for (it in lastName!!.toCharArray()) {
            if (literas.containsKey(it.toLowerCase().toString())) {
                transLastName += literas.get(it.toLowerCase().toString())
            }
            else {
                transLastName += it.toLowerCase().toString()
            }
        }

        transFirstName = transFirstName.capitalize()
        transLastName = transLastName.capitalize()

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