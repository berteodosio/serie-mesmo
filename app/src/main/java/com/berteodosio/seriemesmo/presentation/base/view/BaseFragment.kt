package com.berteodosio.seriemesmo.presentation.base.view

import androidx.fragment.app.Fragment
import com.berteodosio.seriemesmo.AppApplication
import com.berteodosio.seriemesmo.presentation.base.presenter.BasePresenter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

abstract class BaseFragment : Fragment(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        extend(AppApplication.kodein)           // TODO: check this
    }

}