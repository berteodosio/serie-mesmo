package com.berteodosio.seriemesmo.presentation.base.view

import androidx.fragment.app.Fragment
import com.berteodosio.seriemesmo.AppApplication
import com.berteodosio.seriemesmo.presentation.base.presenter.BasePresenter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

abstract class BaseFragment<T : BasePresenter> : Fragment(), KodeinAware {

    private val nonGenericsPresenter by instance<BasePresenter>()
    protected val presenter by lazy { nonGenericsPresenter as T }       // TODO: talvez isso mude

    override val kodein: Kodein = Kodein.lazy {
        extend(AppApplication.kodein)           // TODO: check this
        import(fragmentModule())
    }

    protected open fun fragmentModule(): Kodein.Module = Kodein.Module("General Fragment Module") {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}