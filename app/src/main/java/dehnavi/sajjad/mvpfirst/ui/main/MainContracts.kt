package dehnavi.sajjad.mvpfirst.ui.main

import dehnavi.sajjad.mvpfirst.data.model.NoteEntity
import dehnavi.sajjad.mvpfirst.ui.base.BasePresenter

interface MainContracts {
    interface View {
        fun showEmpty()
        fun showAllNotes(list: List<NoteEntity>)
        fun deleteNoteMessage()

    }

    interface Presenter : BasePresenter {
        fun loadAllNotes()
        fun deleteNote(entity: NoteEntity)
        fun filterPriority(priority: String)
        fun searchNote(search: String)
    }
}