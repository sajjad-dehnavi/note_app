package dehnavi.sajjad.mvpfirst.ui.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.room.Index
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import dehnavi.sajjad.mvpfirst.R
import dehnavi.sajjad.mvpfirst.data.model.NoteEntity
import dehnavi.sajjad.mvpfirst.data.repository.add.AddNoteRepository
import dehnavi.sajjad.mvpfirst.databinding.FragmentNoteBinding
import dehnavi.sajjad.mvpfirst.utils.*
import javax.inject.Inject

@AndroidEntryPoint
class NoteFragment : BottomSheetDialogFragment(), AddContracts.View {
    //binding
    private lateinit var binding: FragmentNoteBinding

    @Inject
    lateinit var noteEntity: NoteEntity

    @Inject
    lateinit var repository: AddNoteRepository

    @Inject
    lateinit var presenter: AddPresenter

    //Other
    private lateinit var categoryList: List<String>
    private var selectedCategory = ""
    private lateinit var priorityList: List<String>
    private var selectedPriority = ""
    private var noteId: Int = -1
    private var type = NEW
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNoteBinding.inflate(layoutInflater)

        //get data
        arguments?.let {
            noteId = it.getInt(KEY_ID)
            if (noteId != -1)
                type = EDIT
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //loaded entity
        if (type == EDIT)
            presenter.getNoteFromDb(noteId)
        //Init Views
        binding.apply {
            //Close
            closeImg.setOnClickListener { dismiss() }

            //save note
            saveNote.setOnClickListener {
                val title = titleEdt.text.toString()
                val desc = descriptionEdt.text.toString()
                //Entity
                if (type == NEW)
                    noteEntity.id = 0
                else
                    noteEntity.id = noteId
                noteEntity.title = title
                noteEntity.description = desc
                noteEntity.category = selectedCategory
                noteEntity.priority = selectedPriority

                if (type == NEW) {
                    //save
                    presenter.saveNote(entity = noteEntity)
                } else {
                    //update
                    presenter.updateNote(entity = noteEntity)
                }
            }
        }
        //set up spinners
        categoriesSpinnerItems()
        prioritySpinnerItems()
    }

    private fun categoriesSpinnerItems() {
        categoryList = arrayListOf(WORK, HOME, EDUCATION, HEALTH)
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categoryList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategory.adapter = adapter
        binding.spinnerCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    p3: Long
                ) {
                    selectedCategory = categoryList[position]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }


            }
    }


    private fun prioritySpinnerItems() {
        priorityList = arrayListOf(HIGH, NORMAL, LOW)
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, priorityList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerPriority.adapter = adapter
        binding.spinnerPriority.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    p3: Long
                ) {
                    selectedPriority = priorityList[position]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }


            }
    }

    override fun close() {
        dismiss()
    }


    override fun showNote(entity: NoteEntity) {
        if (isAdded)
            requireActivity().runOnUiThread {
                binding.apply {
                    titleEdt.setText(entity.title)
                    descriptionEdt.setText(entity.description)
                    spinnerCategory.setSelection(getIndex(categoryList, entity.category))
                    spinnerPriority.setSelection(getIndex(priorityList, entity.priority))

                }
            }
    }

    private fun getIndex(list: List<String>, item: String): Int {
        return list.indexOf(item)
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }

}