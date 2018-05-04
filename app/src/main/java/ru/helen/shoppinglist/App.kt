package ru.helen.shoppinglist

import android.app.Application
import ru.helen.shoppinglist.di.AppComponent
import ru.helen.shoppinglist.di.AppModule
import ru.helen.shoppinglist.di.DaggerAppComponent
import ru.helen.shoppinglist.features.main.MainActivity

class App : Application() {
    lateinit var appComponent: AppComponent

    companion object {
        lateinit var instance: App
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(applicationContext))
                .build()
    }


}