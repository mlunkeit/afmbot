package dev.mlunkeit.afm.model

import java.awt.Color
import java.util.regex.Pattern

fun Color.createString(): String
{
    return "%02X%02X%02X".format(this.red, this.green, this.blue)
}

fun String.isColor(): Boolean
{
    val pattern = Pattern.compile("([0-9A-F]{6})")
    return pattern.matcher(this).matches()
}

fun String.toColor(): Color
{
    val hex = toInt(16)
    return Color(hex)
}