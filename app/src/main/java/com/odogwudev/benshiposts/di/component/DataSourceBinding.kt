package com.odogwudev.benshiposts.di.component

import com.odogwudev.benshiposts.data.interactors.RemoteDataSource
import com.odogwudev.benshiposts.data.interactors.impl.RemoteDataSourceImpl
import com.odogwudev.benshiposts.data.network.repository.AppRepository
import com.odogwudev.benshiposts.data.network.repository.AppRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceBinding {

    @Binds
    abstract fun bindAppDataSource(repository: AppRepositoryImpl): AppRepository

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: RemoteDataSourceImpl): RemoteDataSource
}
