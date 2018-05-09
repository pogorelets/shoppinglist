package ru.helen.shoppinglist.di

import dagger.Component
import ru.helen.shoppinglist.ui.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}