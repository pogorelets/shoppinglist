package ru.helen.shoppinglist

import android.app.Application
import ru.helen.shoppinglist.di.AppComponent
import ru.helen.shoppinglist.di.AppModule
import ru.helen.shoppinglist.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(applicationContext))
                .build()
    }
}