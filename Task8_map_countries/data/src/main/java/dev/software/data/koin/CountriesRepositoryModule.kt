package dev.software.data.koin

import dev.software.data.repository.CountriesInfoRemoteRepositoryImpl
import dev.software.data.repository.CountriesLocalRepositoryImpl
import dev.software.domain.repository.CountriesLocRepository
import dev.software.domain.repository.CountriesRemRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val countriesRepositoryModule = module {
    singleOf(::CountriesInfoRemoteRepositoryImpl) {
        bind<CountriesRemRepository>()
    }

    singleOf(::CountriesLocalRepositoryImpl) {
        bind<CountriesLocRepository>()
    }
}
