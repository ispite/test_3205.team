package ru.skillbox.test_3205team.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.skillbox.test_3205team.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Test_3205team)
        setContentView(R.layout.activity_main)
    }
}
