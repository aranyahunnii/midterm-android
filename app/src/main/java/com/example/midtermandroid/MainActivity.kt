package com.example.midtermandroid

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.midtermandroid.ui.theme.MidtermAndroidTheme

class MainActivity : ComponentActivity() {

    // เรียกเมื่อ Activity ถูกสร้างครั้งแรก
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(applicationContext, "onCreate() ถูกเรียก", Toast.LENGTH_SHORT).show()
        enableEdgeToEdge()
        setContent {
            MidtermAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProfileScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    // เรียกเมื่อ Activity กำลังจะแสดงผลบนหน้าจอ
    override fun onStart() {
        super.onStart()
        Toast.makeText(applicationContext, "onStart() ถูกเรียก", Toast.LENGTH_SHORT).show()
    }

    // เรียกเมื่อ Activity พร้อมให้ผู้ใช้โต้ตอบ
    override fun onResume() {
        super.onResume()
        Toast.makeText(applicationContext, "onResume() ถูกเรียก", Toast.LENGTH_SHORT).show()
    }

    // เรียกเมื่อ Activity กำลังจะถูกบดบังการ
    override fun onPause() {
        super.onPause()
        Toast.makeText(applicationContext, "onPause() ถูกเรียก", Toast.LENGTH_SHORT).show()
    }

    // เรียกเมื่อ Activity มองไม่เห็นแล้ว
    override fun onStop() {
        super.onStop()
        Toast.makeText(applicationContext, "onStop() ถูกเรียก", Toast.LENGTH_SHORT).show()
    }

    // เรียกเมื่อ Activity ถูกทำลายอย่างสมบูรณ์
    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext, "onDestroy() ถูกเรียก", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
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
                .fillMaxWidth(0.85f)
                .padding(16.dp),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 36.dp, horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // รูปโปรไฟล์วงกลม มีขอบไล่สี
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(Color(0xFF6A5AE0), Color(0xFFB9A9FF))
                            )
                        )
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.my_photo),
                        contentDescription = "รูปนักศึกษา",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(142.dp)
                            .clip(CircleShape)
                            .border(3.dp, Color.White, CircleShape)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "นางสาวอรัญญา คึมยะราช",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2D2D2D),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                // แคปซูลแสดงรหัสนักศึกษา
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .background(Color(0xFFF0EDFF))
                        .padding(horizontal = 18.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "รหัสนักศึกษา 67102122109",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF6A5AE0)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Android Midterm Project",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MidtermAndroidTheme {
        ProfileScreen()
    }
}