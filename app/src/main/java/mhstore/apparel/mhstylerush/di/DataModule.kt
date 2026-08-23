package mhstore.apparel.mhstylerush.di

import mhstore.apparel.mhstylerush.data.repository.CartRepository
import mhstore.apparel.mhstylerush.data.repository.JNVNJOnboardingRepo
import mhstore.apparel.mhstylerush.data.repository.OrderRepository
import mhstore.apparel.mhstylerush.data.repository.ProductRepository

import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        JNVNJOnboardingRepo(
            jnvnjOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ProductRepository() }

    single {
        CartRepository(
            cartItemDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single {
        OrderRepository(
            orderDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}