package com.example.managefile
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var fileListView: ListView
    private var currentPath: File = Environment.getExternalStorageDirectory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fileListView = findViewById(R.id.fileListView)

        displayFiles(currentPath)

        fileListView.setOnItemClickListener { _, _, position, _ ->
            val selectedFile = currentPath.listFiles()?.get(position)

            if (selectedFile != null) {
                if (selectedFile.isDirectory) {
                    // Navigate into the directory
                    currentPath = selectedFile
                    displayFiles(currentPath)
                } else if (selectedFile.isFile && selectedFile.extension == "txt") {
                    // Open file content in another activity
                    val intent = Intent(this, FileViewActivity::class.java)
                    intent.putExtra("filePath", selectedFile.absolutePath)
                    startActivity(intent)
                }
            }
        }
    }

    private fun displayFiles(directory: File) {
        val files = directory.listFiles() ?: return
        val fileNames = files.map { it.name }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, fileNames)
        fileListView.adapter = adapter
    }
}
