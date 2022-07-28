package com.astroanastariq.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astroanastariq.shoestore.models.Shoe
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ShoesViewModel : ViewModel() {
    private val _navigateUp = MutableSharedFlow<Unit>()
    val navigateUp = _navigateUp.asSharedFlow()

    val currentShoe = Shoe("", "", "", "")

    private val _shoesList = MutableLiveData<List<Shoe>>()

    val shoesList: LiveData<List<Shoe>>
        get() = _shoesList

    private fun saveShoe() {
        val list = _shoesList.value?.toMutableList() ?: mutableListOf()
        list.add(currentShoe.copy())
        _shoesList.value = list
        currentShoe.clear()
    }

    // User Actions
    fun onBtnSaveClick() {
        saveShoe()
        viewModelScope.launch { _navigateUp.emit(Unit) }
    }

    fun onBtnCancelClick() {
        viewModelScope.launch { _navigateUp.emit(Unit) }
    }

    fun onEditorActionDone() {
        saveShoe()
        viewModelScope.launch { _navigateUp.emit(Unit) }
    }
}