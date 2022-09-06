package dehnavi.sajjad.mvpfirst.data.db

import androidx.room.*
import dehnavi.sajjad.mvpfirst.data.model.NoteEntity
import dehnavi.sajjad.mvpfirst.utils.TABLE_NOTE
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNote(noteEntity: NoteEntity): Completable

    @Delete
    fun deleteNote(noteEntity: NoteEntity): Completable

    @Update
    fun updateNote(noteEntity: NoteEntity): Completable

    @Query("SELECT * FROM $TABLE_NOTE ORDER BY id")
    fun getAllNote(): Observable<List<NoteEntity>>

    @Query("SELECT * FROM $TABLE_NOTE WHERE id==:id")
    fun getNote(id: Int): Observable<NoteEntity>

    @Query("SELECT * FROM $TABLE_NOTE WHERE priority=:priority")
    fun filterPriority(priority: String): Observable<List<NoteEntity>>

    @Query("SELECT * FROM $TABLE_NOTE WHERE title LIKE '%'||:search||'%'")
    fun searchNote(search: String): Observable<List<NoteEntity>>

}