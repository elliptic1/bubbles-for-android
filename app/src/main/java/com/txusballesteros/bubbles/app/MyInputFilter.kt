package com.txusballesteros.bubbles.app

import android.text.InputFilter
import android.text.Spanned


/**
 * Created by toddsmith on 2019-12-01.
 * Copyright TBSE 2017
 */
class MyInputFilter(private val min: Int, private val max: Int): InputFilter {
    override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence? {
        try {
            val input = (dest.toString() + source.toString()).toInt()
            if (input in min..max) return null
        } catch (nfe: NumberFormatException) {
        }
        return ""
    }
}