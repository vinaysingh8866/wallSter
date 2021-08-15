package com.jav1001.vinaysingh.wallster.ui


import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.jav1001.vinaysingh.wallster.databinding.ActivityDetailsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.*

class DetailsActivity : AppCompatActivity() {

    private fun saveImage(image: Bitmap): String? {
        val uuid = UUID.randomUUID()
        var savedImagePath: String? = null
        val imageFileName = "JPEG_" + uuid + ".jpg"
        val storageDir = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                .toString() + "/YOUR_FOLDER_NAME"
        )
        var success = true
        if (!storageDir.exists()) {
            success = storageDir.mkdirs()
        }
        if (success) {
            val imageFile = File(storageDir, imageFileName)
            savedImagePath = imageFile.getAbsolutePath()
            try {
                val fOut: OutputStream = FileOutputStream(imageFile)
                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
                fOut.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            // Add the image to the system gallery
            galleryAddPic(savedImagePath)
            //Toast.makeText(this, "IMAGE SAVED", Toast.LENGTH_LONG).show() // to make this working, need to manage coroutine, as this execution is something off the main thread
        }
        return savedImagePath
    }
    private fun galleryAddPic(imagePath: String?) {
        imagePath?.let { path ->
            val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
            val f = File(path)
            val contentUri: Uri = Uri.fromFile(f)
            mediaScanIntent.data = contentUri
            sendBroadcast(mediaScanIntent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url=intent.getStringExtra("url").toString()

        val binding = ActivityDetailsBinding.inflate(layoutInflater)
        binding.textViewRes.text = intent.getStringExtra("res")
        binding.textViewType.text = intent.getStringExtra("type")
        binding.textViewSource.text = intent.getStringExtra("source")
        setContentView(binding.root)
        Glide.with(this@DetailsActivity).load(url).centerInside().into(binding.imageDetail)
        //binding.imageDetail.setImageResource(1)
        binding.downloadBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                saveImage(
                    Glide.with(this@DetailsActivity)
                        .asBitmap()
                        .load(url) // sample image
                        .placeholder(android.R.drawable.progress_indeterminate_horizontal) // need placeholder to avoid issue like glide annotations
                        .error(android.R.drawable.stat_notify_error) // need error to avoid issue like glide annotations
                        .submit()
                        .get())
            }
        }
    }

}