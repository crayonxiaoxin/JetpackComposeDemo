package com.github.crayonxiaoxin.composematerial3

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideHiltRepository(@ApplicationContext context: Context): HiltRepository {
        return HiltRepository(context)
    }
}
