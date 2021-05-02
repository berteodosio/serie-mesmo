package com.berteodosio.seriemesmo.presentation.base.view

import androidx.appcompat.app.AppCompatActivity
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

abstract class BaseAppCompatActivity : AppCompatActivity(), KodeinAware {

    private val applicationKodein by closestKodein()

    override val kodein: Kodein = Kodein.lazy {
        extend(applicationKodein)
    }

}