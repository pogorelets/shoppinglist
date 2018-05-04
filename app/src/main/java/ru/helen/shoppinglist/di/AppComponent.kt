package ru.helen.shoppinglist.di

import dagger.Component
import ru.helen.shoppinglist.features.main.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}