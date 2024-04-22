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
import com.google.devtools.ksp.symbol.KSTypeAlias
import com.google.devtools.ksp.symbol.KSTypeArgument
import com.google.devtools.ksp.symbol.KSTypeParameter
import com.google.devtools.ksp.symbol.KSTypeReference
import com.google.devtools.ksp.symbol.KSValueArgument
import com.google.devtools.ksp.symbol.KSValueParameter
import com.google.devtools.ksp.symbol.KSVisitor
import java.util.LinkedList

class ClassFinderJobVisitor(private val logger: (String) -> Unit) : KSVisitor<String, String> {

    override fun visitAnnotated(annotated: KSAnnotated, data: String): String {
        logger("visitAnnotated")
        return ""
    }

    override fun visitAnnotation(annotation: KSAnnotation, data: String): String {
        logger("visitAnnotation")
        return ""
    }

    override fun visitCallableReference(
        reference: KSCallableReference,
        data: String
    ): String {
        logger("visitCallableReference")
        return ""
    }

    override fun visitClassDeclaration(
        classDeclaration: KSClassDeclaration,
        data: String
    ): String {
        val className = classDeclaration.qualifiedName?.asString() ?: return ""
        logger("visitClassDeclaration: $className")
        if (isSuperClass(classDeclaration, data)) {
            return className
        }
        return ""
    }

    private fun isSuperClass(classDeclaration: KSClassDeclaration, targetSuper: String): Boolean {
        if (targetSuper.isEmpty()) {
            return true
        }
        val pendingList = LinkedList<KSClassDeclaration>()
        pendingList.add(classDeclaration)
        while (pendingList.isNotEmpty()) {
            val first = pendingList.removeFirst()
            first.superTypes.toList().forEach {
                val declaration = it.resolve().declaration
                val superName = declaration.qualifiedName?.asString() ?: ""
                if (superName == targetSuper) {
                    return true
                }
                if (declaration is KSClassDeclaration) {
                    pendingList.addLast(declaration)
                }
            }
        }
        return false
    }

    override fun visitClassifierReference(
        reference: KSClassifierReference,
        data: String
    ): String {
        logger("visitClassifierReference")
        return ""
    }

    override fun visitDeclaration(declaration: KSDeclaration, data: String): String {
        logger("visitDeclaration")
        return ""
    }

    override fun visitDeclarationContainer(
        declarationContainer: KSDeclarationContainer,
        data: String
    ): String {
        logger("visitDeclarationContainer")
        return ""
    }

    override fun visitDynamicReference(reference: KSDynamicReference, data: String): String {
        logger("visitDynamicReference")
        return ""
    }

    override fun visitFile(file: KSFile, data: String): String {
        logger("visitFile")
        return ""
    }

    override fun visitFunctionDeclaration(
        function: KSFunctionDeclaration,
        data: String
    ): String {
        logger("visitFunctionDeclaration")
        return ""
    }

    override fun visitModifierListOwner(
        modifierListOwner: KSModifierListOwner,
        data: String
    ): String {
        logger("visitModifierListOwner")
        return ""
    }

    override fun visitNode(node: KSNode, data: String): String {
        logger("visitNode")
        return ""
    }

    override fun visitParenthesizedReference(
        reference: KSParenthesizedReference,
        data: String
    ): String {
        logger("visitParenthesizedReference")
        return ""
    }

    override fun visitPropertyAccessor(accessor: KSPropertyAccessor, data: String): String {
        logger("visitPropertyAccessor")
        return ""
    }

    override fun visitPropertyDeclaration(
        property: KSPropertyDeclaration,
        data: String
    ): String {
        logger("visitPropertyDeclaration")
        return ""
    }

    override fun visitPropertyGetter(getter: KSPropertyGetter, data: String): String {
        logger("visitPropertyGetter")
        return ""
    }

    override fun visitPropertySetter(setter: KSPropertySetter, data: String): String {
        logger("visitPropertySetter")
        return ""
    }

    override fun visitReferenceElement(element: KSReferenceElement, data: String): String {
        logger("visitReferenceElement")
        return ""
    }

    override fun visitTypeAlias(typeAlias: KSTypeAlias, data: String): String {
        logger("visitTypeAlias")
        return ""
    }

    override fun visitTypeArgument(typeArgument: KSTypeArgument, data: String): String {
        logger("visitTypeArgument")
        return ""
    }

    override fun visitTypeParameter(typeParameter: KSTypeParameter, data: String): String {
        logger("visitTypeParameter")
        return ""
    }

    override fun visitTypeReference(typeReference: KSTypeReference, data: String): String {
        logger("visitTypeReference")
        return ""
    }

    override fun visitValueArgument(valueArgument: KSValueArgument, data: String): String {
        logger("visitValueArgument")
        return ""
    }

    override fun visitValueParameter(
        valueParameter: KSValueParameter,
        data: String
    ): String {
        logger("visitValueParameter")
        return ""
    }

    override fun visitDefNonNullReference(
        reference: KSDefNonNullReference,
        data: String
    ): String {
        logger("visitDefNonNullReference")
        return ""
    }
}