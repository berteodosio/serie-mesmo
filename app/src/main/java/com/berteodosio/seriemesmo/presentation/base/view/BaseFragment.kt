package com.berteodosio.seriemesmo.presentation.base.view

import androidx.fragment.app.Fragment
import com.berteodosio.seriemesmo.AppApplication
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

abstract class BaseFragment : Fragment(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        extend(AppApplication.kodein)           // TODO: check this
    }

}