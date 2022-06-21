package dev.software.data.koin

import dev.software.data.services.LocationService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val serviceModule = module {
    singleOf (::LocationService )
}