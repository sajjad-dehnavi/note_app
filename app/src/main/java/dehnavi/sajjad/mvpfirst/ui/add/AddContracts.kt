package dehnavi.sajjad.mvpfirst.ui.add

import dehnavi.sajjad.mvpfirst.data.model.NoteEntity
import dehnavi.sajjad.mvpfirst.ui.base.BasePresenter

interface AddContracts {
    interface View {
        fun close()
        fun showNote(entity: NoteEntity)
    }

    interface Presenter : BasePresenter {
        fun saveNote(entity: NoteEntity)
        fun updateNote(entity: NoteEntity)
        fun getNoteFromDb(id: Int)
    }
}