package dev.software.data.koin
import dev.software.domain.usecase.GetCountryInfoUseCase
import org.koin.core.module.dsl.factoryOf

import org.koin.dsl.module

internal val useCaseModule = module {
    factoryOf(::GetCountryInfoUseCase)
}