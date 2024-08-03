package com.balance.boi

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.balance.boi.database.Account
import kotlin.random.Random

class AccountViewModel : ViewModel() {
    private val _accounts = getTestAccounts().toMutableStateList()
    val accounts: List<Account>
        get() = _accounts

    fun remove(item: Account) {
        _accounts.remove(item)
    }
}

private fun getTestAccounts() =
    List(30) { i ->
        Account(name = "Test $i", institutionName =  "Bank $i", balance = Random.nextInt())
    }
