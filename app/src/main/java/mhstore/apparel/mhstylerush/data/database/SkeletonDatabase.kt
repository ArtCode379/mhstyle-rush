package mhstore.apparel.mhstylerush.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import mhstore.apparel.mhstylerush.data.dao.CartItemDao
import mhstore.apparel.mhstylerush.data.dao.OrderDao
import mhstore.apparel.mhstylerush.data.database.converter.Converters
import mhstore.apparel.mhstylerush.data.entity.CartItemEntity
import mhstore.apparel.mhstylerush.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class JNVNJDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}