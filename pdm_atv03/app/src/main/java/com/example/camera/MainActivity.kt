package com.example.camera

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {}

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

            requestPermissionLauncher.launch( Manifest.permission.CAMERA)
        }


        button_capture.setOnClickListener({
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 1)
        })


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 1 && resultCode == RESULT_OK ){
            val extras: Bundle? = data?.extras;
            val imageBitmap = extras?.get("data") as Bitmap?
            imageView.setImageBitmap(imageBitmap)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


}