package dehnavi.sajjad.mvpfirst.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import dehnavi.sajjad.mvpfirst.utils.TABLE_NOTE

@Entity(tableName = TABLE_NOTE)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String = "",
    var description: String = "",
    var category: String = "",
    var priority: String = "",
)