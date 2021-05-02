package com.berteodosio.seriemesmo.presentation.base.view

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.berteodosio.seriemesmo.AppApplication
import com.berteodosio.seriemesmo.presentation.base.presenter.BasePresenter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

abstract class BaseAppCompatActivityWithPresenter<T : BasePresenter> : AppCompatActivity(), KodeinAware {

    private val nonGenericsPresenter by instance<BasePresenter>()
    protected val presenter by lazy { nonGenericsPresenter as T }       // TODO: talvez isso mude

    private val applicationKodein by closestKodein()

    override val kodein: Kodein = Kodein.lazy {
        extend(AppApplication.kodein)
        import(activityModule())
    }

    protected open fun activityModule(): Kodein.Module = Kodein.Module("General Activity Module") {

    }

    protected fun putFragmentOnScreen(fragment: Fragment, @IdRes containerToBeReplaced: Int?) {
        containerToBeReplaced?.let {
            supportFragmentManager.replaceAndCommitTransaction(it, fragment, fragment.javaClass.simpleName)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}

fun FragmentManager.replaceAndCommitTransaction(
    @IdRes containerViewId: Int,
    fragment: Fragment,
    tag: String? = fragment.javaClass.simpleName
) = this
    .beginTransaction()
    .replace(containerViewId, fragment, tag)
    .commit()