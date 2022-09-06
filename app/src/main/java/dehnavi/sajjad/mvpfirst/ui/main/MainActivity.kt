package dehnavi.sajjad.mvpfirst.ui.main

import android.opengl.Visibility
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dehnavi.sajjad.mvpfirst.R
import dehnavi.sajjad.mvpfirst.data.model.NoteEntity
import dehnavi.sajjad.mvpfirst.data.repository.main.MainRepository
import dehnavi.sajjad.mvpfirst.databinding.ActivityMainBinding
import dehnavi.sajjad.mvpfirst.ui.add.NoteFragment
import dehnavi.sajjad.mvpfirst.utils.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainContracts.View {
    //binding
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var repository: MainRepository

    @Inject
    lateinit var noteAdapter: NoteAdapter

    @Inject
    lateinit var presenter: MainPresenter

    //other
    private var positionFilter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //action view
        setSupportActionBar(binding.toolbar)
        //Init views
        binding.apply {
            //Note detail
            addNoteBtn.setOnClickListener {
                NoteFragment().show(
                    supportFragmentManager,
                    NoteFragment().tag
                )
            }
            //Click Note
            noteAdapter.setOnItemClickListener { note, state ->
                when (state) {
                    DELETE -> {
                        val noteEntity = NoteEntity(
                            note.id,
                            note.title,
                            note.description,
                            note.category,
                            note.priority
                        )
                        presenter.deleteNote(noteEntity)
                    }
                    EDIT -> {
                        val bundle = Bundle()
                        val noteFragment = NoteFragment()
                        bundle.putInt(KEY_ID, note.id)
                        noteFragment.arguments = bundle
                        noteFragment.show(supportFragmentManager, noteFragment.tag)
                    }
                }
            }
            //Menu toolbar
            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.actionFilter -> {
                        dialogFilter()
                    }
                    R.id.actionSearch -> {
                        return@setOnMenuItemClickListener false
                    }
                }
                return@setOnMenuItemClickListener true
            }
        }
        //load all notes
        presenter.loadAllNotes()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val search = menu.findItem(R.id.actionSearch)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String): Boolean {
                presenter.searchNote(p0)
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun showEmpty() {
        binding.emptyList.visibility = View.VISIBLE
        binding.noteList.visibility = View.GONE
    }

    override fun showAllNotes(list: List<NoteEntity>) {
        binding.emptyList.visibility = View.GONE
        binding.noteList.visibility = View.VISIBLE
        noteAdapter.setData(list)
        //init note list
        binding.noteList.apply {
            adapter = noteAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    override fun deleteNoteMessage() {
        Snackbar.make(binding.root, "Note deleted!", Snackbar.LENGTH_SHORT).show()
    }

    private fun dialogFilter() {
        val builder = AlertDialog.Builder(this)
        val priorities = arrayOf(ALL, HIGH, NORMAL, LOW)
        builder.setSingleChoiceItems(priorities, positionFilter) { dialog, item ->
            if (item == 0)
                presenter.loadAllNotes()
            else
                presenter.filterPriority(priorities[item])
            positionFilter = item
            //hide search when use filter
            visibilitySearchView(item==0)
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun visibilitySearchView(visibility: Boolean) {
        binding.toolbar.menu.getItem(0).isVisible = visibility
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

}