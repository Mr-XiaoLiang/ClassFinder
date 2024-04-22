package com.lollipop.mockdemo

import com.lollipop.finder.ClassFinder


object LibraryMain {

    fun init(callback: (Class<out MockBridge>) -> Unit) {
        val finder = ClassFinder.find<MockBridge>()
        println("ClassFinder: ${finder::class.java}")
        finder.forEach {
            callback(it)
        }
    }

}