package ru.helen.shoppinglist

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import ru.helen.shoppinglist.di.AppComponent
import ru.helen.shoppinglist.di.AppModule
import ru.helen.shoppinglist.di.DaggerAppComponent


class App : Application() {
    lateinit var appComponent: AppComponent

    companion object {
        lateinit var instance: App
    }


    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        instance = this
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(applicationContext))
                .build()
    }


}