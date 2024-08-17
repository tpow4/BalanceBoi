package com.balance.boi.database.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class Account(
    @PrimaryKey(autoGenerate = true) val accountId: Int = 0,
    val accountInstitutionId: Int,
    val accountName: String,
    val accountTaxType: TaxType
)

data class AccountWithBalances(
    @Embedded val account: Account,
    @Relation(parentColumn = "accountId", entityColumn = "balanceAccountId")
    val balances: List<Balance>
)

enum class TaxType {
    ORDINARY,
    BROKERAGE,
    ROTH,
    TAX_FREE
}