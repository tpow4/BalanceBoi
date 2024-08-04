package com.balance.boi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.balance.boi.database.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {

    val allAccounts = repository.getAllAccounts().asLiveData()
}