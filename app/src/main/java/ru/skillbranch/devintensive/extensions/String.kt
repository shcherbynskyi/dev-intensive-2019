package ru.skillbranch.devintensive.extensions


/**
 * Extension function that truncates the string to the given length
 * @param length - given length of the string
 * @return truncated string
 */
fun String.truncate(length: Int = 16): String {
    if (length > this.trim().length)
        return this.trim()
    return this.substring(0, length).trim() + "..."
}


/**
 * Extension function that clears string from html tags and etc.
 * @return string without html tags and other prohibited symbols
 */
fun String.stripHtml(): String {

    var str = this

    while (str.indexOf("<") != -1 && str.indexOf(">") != -1) {
        val toBeReplaced = str.substring(str.indexOf("<"), str.indexOf(">") + 1)
        str = str.replace(toBeReplaced, "")
    }

    str = str.replace("\\s+".toRegex()," ")

    return str.trim()
}