package com.lollipop.finder.ksp


import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.validate
import com.lollipop.finder.Volunteer

class ClassFinderSymbolProcessor(
    private val environment: SymbolProcessorEnvironment
) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        environment.logger.warn("ClassFinder: process")
        val finderList = resolver.getSymbolsWithAnnotation(
            Volunteer::class.qualifiedName!!
        ).toList()
        if (finderList.isEmpty()) {
            return emptyList()
        }
        val resultList = ArrayList<KSAnnotated>()
        val jobList = ArrayList<FinderJob>()
        finderList.forEach { annotated ->
            environment.logger.warn("ClassFinder: annotated $annotated")
            if (annotated.validate()) {
                val finderJob = annotated.accept(
                    ClassFinderVisitor {
                        environment.logger.warn(it)
                    },
                    Unit
                )
                if (finderJob.isNotEmpty()) {
                    jobList.addAll(finderJob)
                }
            } else {
                resultList.add(annotated)
            }
        }
        jobList.forEach {
            environment.logger.warn("ClassFinder: job ${it.annotationName} -- ${it.superClass}")
            val list = it.process(resolver, environment)
            resultList.addAll(list)
        }
        return resultList
    }
}