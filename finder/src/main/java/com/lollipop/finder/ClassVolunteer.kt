package com.lollipop.finder

abstract class ClassVolunteer<T : Any>(private val children: List<Class<out T>>) :
    List<Class<out T>> by children