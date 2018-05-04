package ru.helen.shoppinglist.di

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides

import ru.helen.shoppinglist.database.ShoppinglistDatabase
import ru.helen.shoppinglist.repository.LocalRepositoryImpl
import ru.helen.shoppinglist.viewmodel.MainModelFactory
import javax.inject.Singleton


@Module
class AppModule(var context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideRoomDatabase(context: Context): ShoppinglistDatabase =
            Room.databaseBuilder(context, ShoppinglistDatabase::class.java, "shoppinglist").build()

    @Provides
    @Singleton
    fun provideLocalRepository(database: ShoppinglistDatabase): LocalRepositoryImpl = LocalRepositoryImpl(database)

    @Provides
    @Singleton
    fun provideMainModelFactory(repository: LocalRepositoryImpl) = MainModelFactory(repository)


}