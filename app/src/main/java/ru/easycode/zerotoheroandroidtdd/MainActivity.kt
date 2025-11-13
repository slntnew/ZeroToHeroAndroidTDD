package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var repository: Repository
    private lateinit var wrapper: LiveDataWrapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repository = Repository.Base()
        wrapper = LiveDataWrapper.Base()
        mainViewModel = MainViewModel(
            wrapper,
            repository
        )
        val textView = findViewById<TextView>(R.id.titleTextView)
        val button = findViewById<Button>(R.id.actionButton)
        val progress = findViewById<ProgressBar>(R.id.progressBar)
        button.setOnClickListener {
            mainViewModel.load()
        }
        mainViewModel.liveData().observe(this) { uiState ->
            uiState.apply(textView, button, progress)
        }

    }
}