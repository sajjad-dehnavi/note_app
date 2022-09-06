package dehnavi.sajjad.mvpfirst.ui.main

import dehnavi.sajjad.mvpfirst.data.model.NoteEntity
import dehnavi.sajjad.mvpfirst.data.repository.main.MainRepository
import dehnavi.sajjad.mvpfirst.ui.base.BasePresenterImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val repository: MainRepository,
    private val view: MainContracts.View
) : MainContracts.Presenter, BasePresenterImpl() {
    override fun loadAllNotes() {
        disposable = repository.getAllNotes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.isEmpty())
                    view.showEmpty()
                else
                    view.showAllNotes(it)
            }
    }

    override fun deleteNote(entity: NoteEntity) {
        disposable = repository.deleteNote(entity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.deleteNoteMessage()
            }
    }

    override fun filterPriority(priority: String) {
        disposable = repository.filterByPriority(priority)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.isEmpty())
                    view.showEmpty()
                else
                    view.showAllNotes(it)
            }
    }

    override fun searchNote(search: String) {
        disposable = repository.searchNote(search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.isEmpty())
                    view.showEmpty()
                else
                    view.showAllNotes(it)
            }
    }

}