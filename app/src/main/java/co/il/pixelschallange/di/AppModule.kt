package co.il.pixelschallange.di

import co.il.pixelschallange.repo.MainRepository
import co.il.pixelschallange.utils.DFSUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesDFSUtil() = DFSUtil()

    @Singleton
    @Provides
    fun providesMainRepository(dfsUtil: DFSUtil) = MainRepository(dfsUtil)
}