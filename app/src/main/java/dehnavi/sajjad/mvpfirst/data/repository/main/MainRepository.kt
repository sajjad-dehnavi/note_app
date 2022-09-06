package dehnavi.sajjad.mvpfirst.data.repository.main

import dehnavi.sajjad.mvpfirst.data.db.NoteDao
import dehnavi.sajjad.mvpfirst.data.model.NoteEntity
import javax.inject.Inject

class MainRepository @Inject constructor(private val dao: NoteDao) {
    fun getAllNotes() = dao.getAllNote()
    fun deleteNote(entity: NoteEntity) = dao.deleteNote(entity)
    fun filterByPriority(priority: String) = dao.filterPriority(priority)
    fun searchNote(search: String) = dao.searchNote(search)
}