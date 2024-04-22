package com.lollipop.finder

import java.util.Locale

internal object FinderHelper {

    const val FINDER_PACKAGE_NAME = "com.lollipop.finder"

    fun finderName(clazz: Class<*>): String {
        return finderName(clazz.name)
    }

    fun finderName(path: String): String {
        return getClassName(path, "Finder")
    }

    private fun getClassName(path: String, suffix: String): String {
        val nameList = path.split(".")
        if (nameList.size < 2) {
            return path.firstUpCase() + suffix
        }
        val builder = StringBuilder()
        nameList.forEach {
            builder.append(it.firstUpCase())
        }
        builder.append(suffix)
        return builder.toString()
    }

    private fun String.firstUpCase(): String {
        val value = this
        return value.replaceFirstChar {
            if (it.isLowerCase()) {
                it.titlecase(Locale.getDefault())
            } else {
                it.toString()
            }
        }
    }

}