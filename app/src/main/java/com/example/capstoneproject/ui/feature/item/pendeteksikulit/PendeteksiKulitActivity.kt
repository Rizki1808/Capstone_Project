package com.example.capstoneproject.ui.feature.item.pendeteksikulit

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.data.tools.ViewModelFactory
import com.example.capstoneproject.databinding.ActivityPendeteksiKulitBinding
import com.example.capstoneproject.ui.feature.item.getImageUri
import com.yalantis.ucrop.UCrop
import java.io.File
import androidx.lifecycle.Observer
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.ByteArrayOutputStream
import java.io.InputStream

class PendeteksiKulitActivity : AppCompatActivity() {

    private val viewModel by viewModels<KulitViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityPendeteksiKulitBinding
    private var currentImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPendeteksiKulitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set the ActionBar title
        val actionBar = supportActionBar
        actionBar?.title = "Selamat Datang"

        binding.btnCamera.setOnClickListener { startCamera() }
        binding.btnGalery.setOnClickListener { startGallery() }

        binding.ivHistoryKulit.setOnClickListener {
            val intent = Intent(this, HistoryKulitActivity::class.java)
            startActivity(intent)
        }

        binding.btnAnalisis.setOnClickListener {
            currentImageUri?.let { uri ->
                val requestBody = createRequestBody(uri)
                viewModel.uploadSkin(requestBody)
            }
        }

        viewModel.uploadSkin.observe(this, Observer { result ->
            result.onSuccess {
                // Handle success response
                Log.d("UploadSkin", "Success: $it")
                val intent = Intent(this, HasilKulitActivity::class.java).apply {
                    putExtra("RESULT", it.result)  // Pass the result
                    putExtra("IMAGE_URI", currentImageUri.toString())  // Pass the image URI as a string
                }
                startActivity(intent)
            }.onFailure {
                // Handle error response
                Log.e("UploadSkin", "Failure: ${it.message}")
            }
        })
    }

    private fun convertImageToBase64(uri: Uri): String {
        val inputStream: InputStream? = contentResolver.openInputStream(uri)
        var bitmap = BitmapFactory.decodeStream(inputStream)
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 30, outputStream) // Lowering the quality to 30
        var bytes = outputStream.toByteArray()

        while (bytes.size > 1048576) { // Reduce the image size until it's below the limit
            val scaleFactor = Math.sqrt((1048576.0 / bytes.size).toDouble())
            val newWidth = (bitmap.width * scaleFactor).toInt()
            val newHeight = (bitmap.height * scaleFactor).toInt()
            bitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)

            outputStream.reset()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 30, outputStream)
            bytes = outputStream.toByteArray()
        }

        val base64Image = Base64.encodeToString(bytes, Base64.DEFAULT)
        Log.d("Base64Image", "Image size: ${base64Image.length}")
        return base64Image
    }

    private fun createRequestBody(uri: Uri): MultipartBody.Part {
        val file = File(uri.path)
        val requestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("image", file.name, requestBody)
    }

    // Camera
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

    // Gallery
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

    // Show Image
    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.ivPreviewImageKulit.setImageURI(it)
        }
    }
}