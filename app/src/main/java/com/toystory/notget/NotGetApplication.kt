package com.toystory.notget

import android.app.Application
import com.toystory.notget.data.NoteDataBase
import com.toystory.notget.data.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NotGetApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { NoteDataBase.getDatabase(this, applicationScope) }
    val repository by lazy { NoteRepository(database.noteDao()) }
}