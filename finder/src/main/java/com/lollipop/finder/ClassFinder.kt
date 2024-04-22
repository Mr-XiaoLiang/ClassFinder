package com.lollipop.finder

object ClassFinder {

    private val finderImpl = HashMap<String, ClassVolunteer<*>>()

    inline fun <reified T : Any> find(): ClassVolunteer<T> {
        return findByName(T::class.java.name)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> findByName(simpleName: String): ClassVolunteer<T> {
        try {
            if (finderImpl.containsKey(simpleName)) {
                return finderImpl[simpleName] as ClassVolunteer<T>
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        val finderName = FinderHelper.finderName(simpleName)
        val fullPath = FinderHelper.FINDER_PACKAGE_NAME + "." + finderName
        try {
            val finderClass = Class.forName(fullPath)
            val field = finderClass.getDeclaredField("INSTANCE")
            val instance = field.get(null)
            val finder = instance as ClassVolunteer<T>
            finderImpl[simpleName] = finder
            return finder
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return EmptyFinder()
    }

    class EmptyFinder<T : Any> : ClassVolunteer<T>(emptyList())

}
