package dev.software.data.koin
import dev.software.domain.usecase.GetPersonInfoUseCase
import dev.software.domain.usecase.GetPersonTVShowUseCase
import dev.software.domain.usecase.GetPersonsUseCase
import org.koin.core.module.dsl.factoryOf

import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::GetPersonsUseCase)
    factoryOf(::GetPersonInfoUseCase)
    factoryOf(::GetPersonTVShowUseCase)
}