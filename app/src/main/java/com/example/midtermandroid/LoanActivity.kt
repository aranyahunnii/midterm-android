package com.example.midtermandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.midtermandroid.ui.theme.MidtermAndroidTheme
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

class LoanActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MidtermAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoanCalculatorScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// ---------- View ----------
@Composable
fun LoanCalculatorScreen(
    modifier: Modifier = Modifier,
    viewModel: LoanViewModel = viewModel()
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF6A5AE0), Color(0xFF8E7CF5), Color(0xFFB9A9FF))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.88f)
                .padding(16.dp),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 32.dp, horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "คำนวณค่างวดผ่อนชำระ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2D2D2D)
                )

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = viewModel.price,
                    onValueChange = viewModel::onPriceChange,
                    label = { Text("ราคาสินค้า (บาท)") },
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF6A5AE0),
                        focusedLabelColor = Color(0xFF6A5AE0)
                    ),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "อัตราดอกเบี้ยต่อเดือน: ${"%.2f".format(viewModel.interestRate)} %",
                    color = Color(0xFF2D2D2D)
                )
                Slider(
                    value = viewModel.interestRate,
                    onValueChange = viewModel::onInterestRateChange,
                    valueRange = 0f..10f,
                    colors = SliderDefaults.colors(
                        thumbColor = Color(0xFF6A5AE0),
                        activeTrackColor = Color(0xFF6A5AE0)
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "จำนวนเดือนที่ผ่อน: ${viewModel.months.toInt()} เดือน",
                    color = Color(0xFF2D2D2D)
                )
                Slider(
                    value = viewModel.months,
                    onValueChange = viewModel::onMonthsChange,
                    valueRange = 1f..60f,
                    colors = SliderDefaults.colors(
                        thumbColor = Color(0xFF6A5AE0),
                        activeTrackColor = Color(0xFF6A5AE0)
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { viewModel.calculate() },
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A5AE0)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("คำนวณ", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                }

                Spacer(modifier = Modifier.height(24.dp))

                // แสดงผลลัพธ์ในกล่องสีม่วงอ่อน
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF0EDFF), RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    ResultRow("ค่างวดต่อเดือน", viewModel.result.monthlyPayment)
                    Spacer(modifier = Modifier.height(6.dp))
                    ResultRow("ยอดรวมที่ต้องจ่าย", viewModel.result.totalPayment)
                    Spacer(modifier = Modifier.height(6.dp))
                    ResultRow("ดอกเบี้ยรวม", viewModel.result.totalInterest)
                }
            }
        }
    }
}

@Composable
fun ResultRow(label: String, value: Double) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = Color.Gray, fontSize = 14.sp)
        Text(
            text = "${"%,.2f".format(value)} บาท",
            color = Color(0xFF6A5AE0),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoanCalculatorScreenPreview() {
    MidtermAndroidTheme {
        LoanCalculatorScreen()
    }
}