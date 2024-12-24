package com.example.managefile;

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class FileViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_view)

        val fileContentTextView: TextView = findViewById(R.id.fileContent)
        val filePath = intent.getStringExtra("filePath")

        if (filePath != null) {
            val file = File(filePath)
            if (file.exists() && file.isFile) {
                fileContentTextView.text = file.readText()
            } else {
                fileContentTextView.text = "Không thể đọc nội dung tập tin!"
            }
        }
    }
}
