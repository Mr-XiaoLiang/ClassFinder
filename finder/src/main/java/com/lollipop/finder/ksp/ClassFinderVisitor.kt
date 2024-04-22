package com.lollipop.finder.ksp


import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSCallableReference
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSClassifierReference
import com.google.devtools.ksp.symbol.KSDeclaration
import com.google.devtools.ksp.symbol.KSDeclarationContainer
import com.google.devtools.ksp.symbol.KSDefNonNullReference
import com.google.devtools.ksp.symbol.KSDynamicReference
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSModifierListOwner
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.symbol.KSParenthesizedReference
import com.google.devtools.ksp.symbol.KSPropertyAccessor
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSPropertyGetter
import com.google.devtools.ksp.symbol.KSPropertySetter
import com.google.devtools.ksp.symbol.KSReferenceElement
import com.google.devtools.ksp.symbol.KSType
import com.google.devtools.ksp.symbol.KSTypeAlias
import com.google.devtools.ksp.symbol.KSTypeArgument
import com.google.devtools.ksp.symbol.KSTypeParameter
import com.google.devtools.ksp.symbol.KSTypeReference
import com.google.devtools.ksp.symbol.KSValueArgument
import com.google.devtools.ksp.symbol.KSValueParameter
import com.google.devtools.ksp.symbol.KSVisitor

class ClassFinderVisitor(private val logger: (String) -> Unit) : KSVisitor<Unit, List<FinderJob>> {

    override fun visitAnnotated(annotated: KSAnnotated, data: Unit): List<FinderJob> {
        logger("visitAnnotated")
        return emptyList()
    }

    override fun visitAnnotation(annotation: KSAnnotation, data: Unit): List<FinderJob> {
        logger("visitAnnotation")
        return emptyList()
    }

    private fun visitAnnotation(clazz: KSClassDeclaration): FinderJob? {
        val qualifierName = clazz.qualifiedName?.asString() ?: ""
        var superClass = ""
        val annotations = clazz.annotations
        for (a in annotations) {
            if (a.shortName.asString() == "ClassFinder") {
                a.arguments.forEach {
                    logger("visitAnnotation arguments:  ${it.name?.getShortName()} = ${it.value}")
                    val argName = it.name?.getShortName() ?: ""
                    val argValue = it.value
                    when (argName) {
                        "superClass" -> {
                            if (argValue is KSType) {
                                superClass = argValue.declaration.qualifiedName?.asString() ?: ""
                            }
                        }
                    }
                }
                break
            }
        }
        if (qualifierName.isNotEmpty() && superClass.isNotEmpty()) {
            return FinderJob(superClass, qualifierName)
        }
        return null
    }

    override fun visitCallableReference(
        reference: KSCallableReference,
        data: Unit
    ): List<FinderJob> {
        logger("visitCallableReference")
        return emptyList()
    }

    override fun visitClassDeclaration(
        classDeclaration: KSClassDeclaration,
        data: Unit
    ): List<FinderJob> {
        logger("visitClassDeclaration: ${classDeclaration.qualifiedName?.asString()}")
        val finderJob = visitAnnotation(classDeclaration) ?: return emptyList()
        return listOf(finderJob)
    }

    override fun visitClassifierReference(
        reference: KSClassifierReference,
        data: Unit
    ): List<FinderJob> {
        logger("visitClassifierReference")
        return emptyList()
    }

    override fun visitDeclaration(declaration: KSDeclaration, data: Unit): List<FinderJob> {
        logger("visitDeclaration")
        return emptyList()
    }

    override fun visitDeclarationContainer(
        declarationContainer: KSDeclarationContainer,
        data: Unit
    ): List<FinderJob> {
        logger("visitDeclarationContainer")
        return emptyList()
    }

    override fun visitDynamicReference(reference: KSDynamicReference, data: Unit): List<FinderJob> {
        logger("visitDynamicReference")
        return emptyList()
    }

    override fun visitFile(file: KSFile, data: Unit): List<FinderJob> {
        logger("visitFile")
        return emptyList()
    }

    override fun visitFunctionDeclaration(
        function: KSFunctionDeclaration,
        data: Unit
    ): List<FinderJob> {
        logger("visitFunctionDeclaration")
        return emptyList()
    }

    override fun visitModifierListOwner(
        modifierListOwner: KSModifierListOwner,
        data: Unit
    ): List<FinderJob> {
        logger("visitModifierListOwner")
        return emptyList()
    }

    override fun visitNode(node: KSNode, data: Unit): List<FinderJob> {
        logger("visitNode")
        return emptyList()
    }

    override fun visitParenthesizedReference(
        reference: KSParenthesizedReference,
        data: Unit
    ): List<FinderJob> {
        logger("visitParenthesizedReference")
        return emptyList()
    }

    override fun visitPropertyAccessor(accessor: KSPropertyAccessor, data: Unit): List<FinderJob> {
        logger("visitPropertyAccessor")
        return emptyList()
    }

    override fun visitPropertyDeclaration(
        property: KSPropertyDeclaration,
        data: Unit
    ): List<FinderJob> {
        logger("visitPropertyDeclaration")
        return emptyList()
    }

    override fun visitPropertyGetter(getter: KSPropertyGetter, data: Unit): List<FinderJob> {
        logger("visitPropertyGetter")
        return emptyList()
    }

    override fun visitPropertySetter(setter: KSPropertySetter, data: Unit): List<FinderJob> {
        logger("visitPropertySetter")
        return emptyList()
    }

    override fun visitReferenceElement(element: KSReferenceElement, data: Unit): List<FinderJob> {
        logger("visitReferenceElement")
        return emptyList()
    }

    override fun visitTypeAlias(typeAlias: KSTypeAlias, data: Unit): List<FinderJob> {
        logger("visitTypeAlias")
        return emptyList()
    }

    override fun visitTypeArgument(typeArgument: KSTypeArgument, data: Unit): List<FinderJob> {
        logger("visitTypeArgument")
        return emptyList()
    }

    override fun visitTypeParameter(typeParameter: KSTypeParameter, data: Unit): List<FinderJob> {
        logger("visitTypeParameter")
        return emptyList()
    }

    override fun visitTypeReference(typeReference: KSTypeReference, data: Unit): List<FinderJob> {
        logger("visitTypeReference")
        return emptyList()
    }

    override fun visitValueArgument(valueArgument: KSValueArgument, data: Unit): List<FinderJob> {
        logger("visitValueArgument")
        return emptyList()
    }

    override fun visitValueParameter(
        valueParameter: KSValueParameter,
        data: Unit
    ): List<FinderJob> {
        logger("visitValueParameter")
        return emptyList()
    }

    override fun visitDefNonNullReference(
        reference: KSDefNonNullReference,
        data: Unit
    ): List<FinderJob> {
        logger("visitDefNonNullReference")
        return emptyList()
    }
}