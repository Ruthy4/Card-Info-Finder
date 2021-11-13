package com.example.cardinfofinder.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.example.cardinfofinder.databinding.ActivityMainBinding
import com.example.cardinfofinder.util.Constants.REQUEST_CODE
import com.example.cardinfofinder.util.Status
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.text.TextBlock
import com.google.android.gms.vision.text.TextRecognizer
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CardInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var cardNumberEditText: TextInputEditText
    private val cardInfoViewModel: CardInfoViewModel by viewModels()
    private lateinit var textRecognizer: TextRecognizer
    private lateinit var mCameraSource: CameraSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cardNumberEditText = binding.cardNumberEditText

        /*Set Status bar Color*/
        val window: Window = this.window
        val background = ContextCompat.getDrawable(
            this,
            com.example.cardinfofinder.R.drawable.toolbar_background
        )
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = this.resources.getColor(android.R.color.transparent)
        window.setBackgroundDrawable(background)

        observeCardInfo()

        /*take input from the user and make the call to get card details*/
        binding.checkButton.setOnClickListener {
            val cardNumber = cardNumberEditText.text.toString().trim()

            if (cardNumber.isNotEmpty() && cardNumber.length >= 8) {
                cardInfoViewModel.getCardDetails(cardNumber)
            } else {
                Toast.makeText(this, "Enter Card Number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

        /*scan card onclick of the scan button*/
        binding.ocrScanButton.setOnClickListener {
            requestForPermission()
            ocrScan()
        }
    }


    override fun onResume() {
        super.onResume()
        validateSignUpFieldsOnTextChange()
    }


    /*Method to Validate input Field onText Change*/
    private fun validateSignUpFieldsOnTextChange(): Boolean {
        var isValidated = true

        cardNumberEditText.doOnTextChanged { _, _, _, _ ->
            when {
                cardNumberEditText.text.toString().trim().isEmpty() -> {
                    binding.cardNumberTextInputLayout.error =
                        getString(com.example.cardinfofinder.R.string.enter_card_number)
                    isValidated = false
                }
                cardNumberEditText.text.toString().trim().length < 8 -> {
                    binding.cardNumberTextInputLayout.error =
                        getString(com.example.cardinfofinder.R.string.card_number_should_be_at_least_8_digits)
                    isValidated = false
                }
                else -> {
                    binding.cardNumberTextInputLayout.error = null
                    isValidated = true
                }
            }
        }
        return isValidated
    }


    /*Method to observe live data and handle the response for success, failure and loading*/
    private fun observeCardInfo() {
        cardInfoViewModel.cardInfoLiveData.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT)
                        .show()
                    binding.progress.visibility = View.GONE
                    CardInfoResultBottomSheet().apply {
                        show(supportFragmentManager, tag)
                    }
                }
                Status.ERROR -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(this, "An error occurred, please try again", Toast.LENGTH_SHORT)
                        .show()
                }
                Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                }
            }
        }

        )
    }

    private fun ocrScan() {
        //  Create text Recognizer
        textRecognizer = TextRecognizer.Builder(this).build()
        val surfaceCameraPreview = binding.surfaceCameraPreview
        if (!textRecognizer.isOperational) {
            return
        }
        //  Initialize camera source to use high resolution and auto focus
        mCameraSource = CameraSource.Builder(applicationContext, textRecognizer)
            .setFacing(CameraSource.CAMERA_FACING_BACK)
            .setRequestedPreviewSize(1280, 1024)
            .setAutoFocusEnabled(true)
            .setRequestedFps(2.0f)
            .build()
        surfaceCameraPreview.holder.addCallback(object : SurfaceHolder.Callback {
            @SuppressLint("MissingPermission")

            override fun surfaceCreated(p0: SurfaceHolder) {
                try {
                    if (isCameraPermissionGranted()) {
                        mCameraSource.start(surfaceCameraPreview.holder)
                    } else {
                        requestForPermission()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@CardInfoActivity, "${e.message}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {}
            override fun surfaceDestroyed(p0: SurfaceHolder) {
                mCameraSource.stop()
            }
        })

        textRecognizer.setProcessor(object : Detector.Processor<TextBlock> {
            override fun release() {}

            override fun receiveDetections(detections: Detector.Detections<TextBlock>) {
                val items = detections.detectedItems
                if (items.size() <= 0) {
                    return
                }
                cardNumberEditText.post {
                    val stringBuilder = StringBuilder()
                    for (i in 0 until items.size()) {
                        val item = items.valueAt(i)
                        stringBuilder.append(item.value)
                        stringBuilder.append("\n")
                    }
                    cardNumberEditText.setText(stringBuilder.toString())
                }
            }
        })
    }


    /* requestPermission for user permission to access camera*/
    private fun requestForPermission() {

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.CAMERA
                )
            ) {
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.CAMERA
                    ),
                    REQUEST_CODE
                )
            }
        }
    }

    private fun isCameraPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    //for handling permissions
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                } else {
                    requestForPermission()
                }
                return
            }
            else -> {
            }
        }
    }
}