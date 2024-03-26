package info.hridoydas.lifecanvas.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import info.hridoydas.lifecanvas.auth.data.AuthRepository
import info.hridoydas.lifecanvas.auth.data.AuthRepositoryImpl

@InstallIn(ViewModelComponent::class)
@Module
class AuthModule {
    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl
}
