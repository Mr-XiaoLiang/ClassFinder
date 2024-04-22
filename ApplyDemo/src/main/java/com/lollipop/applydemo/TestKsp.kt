package com.lollipop.applydemo

import com.lollipop.finder.Volunteer
import com.lollipop.mockdemo.MockBridge

interface A

interface B : A

@Bridge
class TestF : MockBridge(), B {
    override fun name(): String {
        return "TestF"
    }

    override fun age(): Int {
        return 23
    }
}

@Bridge
class TestC : MockBridge(), A {
    override fun name(): String {
        return "TestC"
    }

    override fun age(): Int {
        return 11
    }

}

@Volunteer(MockBridge::class)
annotation class Bridge
