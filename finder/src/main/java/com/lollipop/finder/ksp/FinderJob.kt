package com.lollipop.finder.ksp


import com.google.devtools.ksp.containingFile
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.validate
import com.lollipop.finder.FinderHelper

class FinderJob(
    val superClass: String,
    val annotationName: String
) {

    fun process(resolver: Resolver, environment: SymbolProcessorEnvironment): List<KSAnnotated> {
        if (superClass.isEmpty() || annotationName.isEmpty()) {
            return emptyList()
        }
        val finderList = resolver.getSymbolsWithAnnotation(annotationName).toList()
        if (finderList.isEmpty()) {
            return emptyList()
        }
        val resultList = ArrayList<KSAnnotated>()
        val classList = ArrayList<TargetInfo>()
        finderList.forEach { annotated ->
            if (annotated.validate()) {
                val name = annotated.accept(
                    ClassFinderJobVisitor {
                        environment.logger.warn(it)
                    },
                    superClass
                )
                if (name.isNotEmpty()) {
                    classList.add(
                        TargetInfo(
                            className = name,
                            containingFile = annotated.containingFile
                        )
                    )
                }
            } else {
                resultList.add(annotated)
            }
        }
        environment.logger.warn("find class Super is $superClass")
        classList.forEach {
            environment.logger.warn("find class child is $it")
        }
        createFinderResultFile(environment, classList)
        return resultList
    }

    private fun createFinderResultFile(
        environment: SymbolProcessorEnvironment,
        classList: List<TargetInfo>
    ) {
        val array = classList.filter {
            it.containingFile != null
        }.map {
            it.containingFile!!
        }.toTypedArray()

        val packageName = FinderHelper.FINDER_PACKAGE_NAME

        val className = FinderHelper.finderName(superClass)
        val outFile = environment.codeGenerator.createNewFile(
            Dependencies(
                aggregating = true,
                *array
            ),
            packageName,
            className
        )
        outFile.write("package $packageName \n\n".toByteArray())//写入文件
        outFile.write("object $className : BaseFinder<${superClass}>(listOf(".toByteArray())
        val comma = ",".toByteArray()
        val clazz = "::class.java".toByteArray()
        for (index in classList.indices) {
            if (index > 0) {
                outFile.write(comma)
            }
            val childName = classList[index].className
            outFile.write(childName.toByteArray())
            outFile.write(clazz)
        }
        outFile.write("))\n".toByteArray())
        outFile.flush()
        outFile.close()
    }

    private class TargetInfo(
        val className: String,
        val containingFile: KSFile?
    )

}