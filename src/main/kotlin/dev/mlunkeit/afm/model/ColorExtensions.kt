package dev.mlunkeit.afm.model

import java.awt.Color

fun Color.createString(): String
{
    return "%02X%02X%02X".format(this.red, this.green, this.blue)
}