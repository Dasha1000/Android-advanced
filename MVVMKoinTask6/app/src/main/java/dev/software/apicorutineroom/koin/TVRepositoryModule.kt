package dev.software.apicorutineroom.koin

import dev.software.apicorutineroom.TVPersonsRepository
import org.koin.dsl.module

val tvRepositoryModule = module {
    single { TVPersonsRepository(get()) }
}