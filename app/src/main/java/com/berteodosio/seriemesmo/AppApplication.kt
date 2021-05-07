package com.berteodosio.seriemesmo

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class AppApplication : Application(), KodeinAware {

    companion object {
        lateinit var kodein: Kodein
    }

    override val kodein: Kodein = Kodein.lazy {
        import(tmdbModule)
        import(repositoryModule)
        import(useCaseModule)
        import(androidModule(this@AppApplication))
        import(generalModule)
    }

    override fun onCreate() {
        super.onCreate()
        Companion.kodein = kodein
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}