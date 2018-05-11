package ru.helen.shoppinglist.di

import dagger.Component
import ru.helen.shoppinglist.ui.main.MainActivity
import ru.helen.shoppinglist.ui.product.ProductActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(productActivity: ProductActivity)
}