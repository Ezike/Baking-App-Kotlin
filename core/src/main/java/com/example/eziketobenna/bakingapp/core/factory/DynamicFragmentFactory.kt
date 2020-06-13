package com.example.eziketobenna.bakingapp.core.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class DynamicFragmentFactory @Inject constructor(
    private val providers: Map<Class<out Fragment>,
@JvmSuppressWildcards Provider<Fragment>>
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val fragmentClass: Class<out Fragment> = loadFragmentClass(classLoader, className)
        val provider: @JvmSuppressWildcards Provider<Fragment>? = providers[fragmentClass]
        return provider?.get() ?: super.instantiate(classLoader, className)
    }
}
