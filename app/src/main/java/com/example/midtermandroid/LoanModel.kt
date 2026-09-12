package com.example.midtermandroid

// ---------- Model ----------
data class LoanResult(
    val monthlyPayment: Double = 0.0,
    val totalPayment: Double = 0.0,
    val totalInterest: Double = 0.0
)