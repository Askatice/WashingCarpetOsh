package com.example.washingcarpetosh.ui.base

import android.util.Patterns

fun String.isEmail(): Boolean{
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}