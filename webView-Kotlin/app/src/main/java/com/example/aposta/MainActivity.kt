package com.example.aposta

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       webViewSetup()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSetup(){
        wb.webViewClient = WebViewClient()

        wb.apply {
            loadUrl("https://estudante.ifpb.edu.br/cursos/39/")
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
        }

        buttonIFPB.setOnClickListener {
            wb.apply {
                loadUrl("https://estudante.ifpb.edu.br/cursos/39/")
                settings.javaScriptEnabled = true
                settings.safeBrowsingEnabled = true
            }
        }

        buttonGOOGLE.setOnClickListener {
            wb.apply {
                loadUrl("https://www.google.com.br/")
                settings.javaScriptEnabled = true
                settings.safeBrowsingEnabled = true
            }
        }


    }
    }