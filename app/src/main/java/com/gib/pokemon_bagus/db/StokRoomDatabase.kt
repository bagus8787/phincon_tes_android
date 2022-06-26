package com.gib.pokemon_bagus.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gib.pokemon_bagus.db.dao.PokemonEffectDao
import com.gib.pokemon_bagus.helper.GlobalVar
import com.gib.pokemon_bagus.model.PokemonEffect

@Database(
    entities = [PokemonEffect::class],
    version = 3,
    exportSchema = false
)
//@TypeConverters(Converters::class)
abstract class StokRoomDatabase : RoomDatabase() {

    abstract fun masterPokemonEffect(): PokemonEffectDao

    companion object {
        @JvmStatic
        fun getDatabase(context: Context): StokRoomDatabase {
            if (INSTANCE == null) {
                synchronized(StokRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            StokRoomDatabase::class.java,
                            GlobalVar.DB_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }

            return INSTANCE!!
        }

        private var INSTANCE: StokRoomDatabase? = null

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
