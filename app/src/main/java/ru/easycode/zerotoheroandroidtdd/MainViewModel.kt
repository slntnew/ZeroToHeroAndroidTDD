package ru.easycode.zerotoheroandroidtdd

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainViewModel(private val liveDataWrapper: LiveDataWrapper, private val repository: Repository) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    fun liveData() = liveDataWrapper.liveData()
    fun  load()  {
        coroutineScope.launch {
            liveDataWrapper.update(UiState.ShowProgress)
            repository.load()
            liveDataWrapper.update(UiState.ShowData)
        }
    }
}