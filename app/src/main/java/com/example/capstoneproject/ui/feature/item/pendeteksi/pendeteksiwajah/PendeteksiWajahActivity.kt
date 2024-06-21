package com.example.capstoneproject.ui.feature.item.pendeteksi.pendeteksiwajah

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.capstoneproject.data.tools.ViewModelFactory
import com.example.capstoneproject.databinding.ActivityPendeteksiWajahBinding
import com.example.capstoneproject.ui.feature.item.getImageUri
import com.example.capstoneproject.ui.feature.item.pendeteksi.history.HistoryWajahActivity
import com.yalantis.ucrop.UCrop
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream

class PendeteksiWajahActivity : AppCompatActivity() {

    private val viewModel by viewModels<WajahViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityPendeteksiWajahBinding
    private var currentImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPendeteksiWajahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set the ActionBar title
        val actionBar = supportActionBar
        actionBar?.title = "Selamat Datang"

        binding.btnCamera.setOnClickListener { startCamera() }
        binding.btnGalery.setOnClickListener { startGallery() }

        binding.ivHistoryKulit.setOnClickListener {
            val intent = Intent(this, HistoryWajahActivity::class.java)
            startActivity(intent)
        }

        binding.btnAnalisis.setOnClickListener {
            currentImageUri?.let { uri ->
                val requestBody = createRequestBody(uri)
                viewModel.uploadFace(requestBody)
            }
        }

        viewModel.uploadFace.observe(this, Observer { result ->
            result.onSuccess {
                // Handle success response
                Log.d("UploadFace", "Success: $it")
                val intent = Intent(this, HasilWajahActivity::class.java).apply {
                    putExtra("RESULT", it.result)
                    putExtra("IMAGE_URI", currentImageUri.toString())
                }
                startActivity(intent)
            }.onFailure {
                // Handle error response
                Log.e("UploadFace", "Failure: ${it.message}")
            }
        })
    }

    private fun createRequestBody(uri: Uri): MultipartBody.Part {
        val inputStream: InputStream? = contentResolver.openInputStream(uri)
        var bitmap = BitmapFactory.decodeStream(inputStream)
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 30, outputStream)
        var bytes = outputStream.toByteArray()

        while (bytes.size > 1048576) {
            val scaleFactor = Math.sqrt((1048576.0 / bytes.size).toDouble())
            val newWidth = (bitmap.width * scaleFactor).toInt()
            val newHeight = (bitmap.height * scaleFactor).toInt()
            bitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)

            outputStream.reset()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 30, outputStream)
            bytes = outputStream.toByteArray()
        }

        val file = File(cacheDir, "temp_image.jpg")
        file.writeBytes(bytes)
        val requestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("image", file.name, requestBody)
    }

    private fun startCamera() {
        currentImageUri = getImageUri(this)
        launcherIntentCamera.launch(currentImageUri!!)
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
        }
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            showImage()
            startCrop(uri)
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun startCrop(uri: Uri) {
        val destinationUri = Uri.fromFile(File(cacheDir, "cropped_${System.currentTimeMillis()}"))
        currentImageUri = destinationUri
        UCrop.of(uri, destinationUri)
            .start(this)
    }

    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK) {
            currentImageUri = UCrop.getOutput(data!!)
            showImage()
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.ivPreviewImageWajah.setImageURI(it)
        }
    }
}