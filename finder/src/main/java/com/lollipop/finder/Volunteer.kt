package com.lollipop.finder

import kotlin.reflect.KClass

@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class Volunteer(
    val superClass: KClass<out Any> = Any::class,
)