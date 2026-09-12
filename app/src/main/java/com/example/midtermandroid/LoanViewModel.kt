package com.example.midtermandroid

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

// ---------- ViewModel ----------
class LoanViewModel : ViewModel() {
    var price by mutableStateOf("")
        private set
    var interestRate by mutableStateOf(1f)
        private set
    var months by mutableStateOf(12f)
        private set
    var result by mutableStateOf(LoanResult())
        private set

    fun onPriceChange(value: String) {
        price = value
    }

    fun onInterestRateChange(value: Float) {
        interestRate = value
    }

    fun onMonthsChange(value: Float) {
        months = value
    }

    fun calculate() {
        val principal = price.toDoubleOrNull() ?: 0.0
        val monthlyRate = interestRate / 100.0
        val n = months.toInt()
        val totalInterest = principal * monthlyRate * n
        val totalPayment = principal + totalInterest
        val monthlyPayment = if (n > 0) totalPayment / n else 0.0
        result = LoanResult(monthlyPayment, totalPayment, totalInterest)
    }
}