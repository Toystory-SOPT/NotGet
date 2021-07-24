package com.toystory.notget.data

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.time.LocalTime

class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    val allNotes: LiveData<List<Note>> = noteRepository.allNotes.asLiveData()
    private val _startTime = MutableLiveData(LocalTime.now())
    val startTime: LiveData<LocalTime>
        get() = _startTime
    private val _endTime = MutableLiveData(LocalTime.now())
    val endTime: LiveData<LocalTime>
        get() = _endTime

    fun setStartTime(hour: Int, minute: Int) {
        val newStartTime = LocalTime.of(hour, minute, 0)
        _startTime.value = if (newStartTime.isBefore(_endTime.value)) newStartTime
        else _startTime.value
    }

    fun setEndTime(hour: Int, minute: Int) {
        val newEndTime = LocalTime.of(hour, minute, 0)
        _endTime.value = if (newEndTime.isAfter(_startTime.value)) newEndTime
        else _endTime.value
    }

    private fun makeTimeString(data: LocalTime) =
        String.format("%02d", data.hour) + ":" + String.format("%02d", data.minute)

    fun insert(title: String) = viewModelScope.launch {
        val note = Note(
            title,
            makeTimeString(requireNotNull(_startTime.value)),
            makeTimeString(requireNotNull(_endTime.value))
        )
        noteRepository.insert(note)
    }
}

class NoteViewModelFactory(private val noteRepository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(noteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
