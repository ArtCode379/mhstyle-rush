package mhstore.apparel.mhstylerush.di

import mhstore.apparel.mhstylerush.data.datastore.JNVNJOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { JNVNJOnboardingPrefs(androidContext()) }
}