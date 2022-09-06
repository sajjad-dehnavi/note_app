package dehnavi.sajjad.mvpfirst.data.repository.add

import dehnavi.sajjad.mvpfirst.data.db.NoteDao
import dehnavi.sajjad.mvpfirst.data.model.NoteEntity
import javax.inject.Inject

class AddNoteRepository @Inject constructor(private val dao: NoteDao) {

    fun saveNote(noteEntity: NoteEntity) = dao.saveNote(noteEntity)

    fun getNote(id: Int) = dao.getNote(id)

    fun updateNote(entity: NoteEntity) = dao.updateNote(entity)
}