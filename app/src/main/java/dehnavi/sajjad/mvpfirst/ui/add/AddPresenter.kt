package dehnavi.sajjad.mvpfirst.ui.add

import dehnavi.sajjad.mvpfirst.data.model.NoteEntity
import dehnavi.sajjad.mvpfirst.data.repository.add.AddNoteRepository
import dehnavi.sajjad.mvpfirst.ui.base.BasePresenterImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class AddPresenter @Inject constructor(
    private val repository: AddNoteRepository,
    private val view: AddContracts.View
) : AddContracts.Presenter, BasePresenterImpl() {
    override fun saveNote(entity: NoteEntity) {
        disposable = repository.saveNote(entity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.close()
            }
    }

    override fun updateNote(entity: NoteEntity) {
        disposable = repository.updateNote(entity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.close()
            }
    }

    override fun getNoteFromDb(id: Int) {
        disposable = repository.getNote(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.showNote(entity = it)
            }
    }

}