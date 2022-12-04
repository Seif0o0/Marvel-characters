package totersapp.marvel.task.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import totersapp.marvel.task.data.repository.CharactersRepositoryImpl
import totersapp.marvel.task.domain.repository.CharactersRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class ReposModule {
    @Binds
    @ViewModelScoped
    abstract fun provideCharactersRepo(repoImpl: CharactersRepositoryImpl): CharactersRepository
}