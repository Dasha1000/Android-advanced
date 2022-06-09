package dev.software.data.koin

import dev.software.data.repository.TVPersonsLocalRepositoryImpl
import dev.software.data.repository.TVPersonsRemoteRepositoryImpl
import dev.software.domain.repository.TVPersonsLocRepository
import dev.software.domain.repository.TVPersonsRemRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val tvRepositoryModule = module {
    //single<TVPersonsRemRepository> { TVPersonsRemoteRepositoryImpl(get()) }
    singleOf(::TVPersonsRemoteRepositoryImpl) { //сделать так
        bind<TVPersonsRemRepository>()
        //named("remote")
    }
    singleOf(::TVPersonsLocalRepositoryImpl) {//сделать так
        bind<TVPersonsLocRepository>()
        //named("local")
    }
}


/*val tvLocalRepositoryModule = module {

    kotlin.runCatching {
        single<TVPersonsLocRepository> { TVPersonsLocalRepositoryImpl(get()) }
    }.fold(
        onSuccess = {
            println("ok")
        },
        onFailure = {
            println("-------------------------->errors1")
            println(it.message)
        }
    )
}*/

/*    singleOf(::TVPersonsRemoteRepositoryImpl) { //сделать так
        bind<TVPersonsRemRepository>()
        named("remote")
    }*/
/*    singleOf(::TVPersonsLocalRepositoryImpl) {//сделать так
        bind<TVPersonsLocRepository>()
        named("local")
    }*/
//single<TVPersonsRepository> { TVPersonsRepositoryImpl(get()) }
