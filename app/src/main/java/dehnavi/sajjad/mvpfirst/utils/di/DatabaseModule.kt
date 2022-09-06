package dehnavi.sajjad.mvpfirst.utils.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dehnavi.sajjad.mvpfirst.data.db.NoteDatabase
import dehnavi.sajjad.mvpfirst.data.model.NoteEntity
import dehnavi.sajjad.mvpfirst.utils.DATABASE_NOTE
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, NoteDatabase::class.java, DATABASE_NOTE
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideNoteDao(database: NoteDatabase) = database.noteDao()

    @Provides
    @Singleton
    fun provideNoteEntity() = NoteEntity()

}