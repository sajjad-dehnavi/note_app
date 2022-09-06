package dehnavi.sajjad.mvpfirst.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dehnavi.sajjad.mvpfirst.data.model.NoteEntity
import dehnavi.sajjad.mvpfirst.utils.VERSION_DATABASE_NOTE

@Database(entities = [NoteEntity::class], version = VERSION_DATABASE_NOTE, exportSchema = false)
abstract class NoteDatabase :RoomDatabase(){
    abstract fun noteDao():NoteDao
}