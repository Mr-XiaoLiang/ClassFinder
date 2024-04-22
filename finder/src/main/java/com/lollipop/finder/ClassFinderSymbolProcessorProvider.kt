package com.lollipop.finder

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.lollipop.finder.ksp.ClassFinderSymbolProcessor

class ClassFinderSymbolProcessorProvider : SymbolProcessorProvider {

    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        environment.logger.warn("ClassFinder: create")
        return ClassFinderSymbolProcessor(environment)
    }

}