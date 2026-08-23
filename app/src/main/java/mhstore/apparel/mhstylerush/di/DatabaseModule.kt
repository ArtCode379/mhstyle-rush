package mhstore.apparel.mhstylerush.di

import androidx.room.Room
import mhstore.apparel.mhstylerush.data.database.JNVNJDatabase
import org.koin.dsl.module

private const val DB_NAME = "jnvnj_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = JNVNJDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<JNVNJDatabase>().cartItemDao() }

    single { get<JNVNJDatabase>().orderDao() }
}