package com.example.apelsintask

import android.app.ProgressDialog.show
import android.hardware.biometrics.BiometricManager
import android.hardware.biometrics.BiometricPrompt
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.biometric.BiometricFragment
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK
import androidx.biometric.BiometricManager.from
import androidx.biometric.BiometricViewModel
import androidx.core.content.ContextCompat
import com.example.apelsintask.databinding.ActivityMainBinding
import com.example.apelsintask.ui.HomeFragment
import java.util.concurrent.Executor
import javax.crypto.Mac

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: androidx.biometric.BiometricPrompt
    private lateinit var promptInfo: androidx.biometric.BiometricPrompt.PromptInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        androidx.biometric.BiometricPrompt.CryptoObject(Mac.getInstance("")).mac



        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = androidx.biometric.BiometricPrompt(
            this,
            executor,
            object : androidx.biometric.BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    binding.tv.text = "Error $errString"
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    binding.tv.text = "Auth Failed"
                }

                override fun onAuthenticationSucceeded(result: androidx.biometric.BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    binding.tv.visibility = View.GONE
                    binding.btn.visibility = View.GONE
                    supportFragmentManager.beginTransaction().add(R.id.layout, HomeFragment())
                        .commit()

                }
            })

        promptInfo = androidx.biometric.BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Auth")
            .setSubtitle("Login using fingerprint or face")
            .setNegativeButtonText("cancel")
            .setAllowedAuthenticators(BIOMETRIC_STRONG or BIOMETRIC_WEAK)
            .build()

        biometricPrompt.authenticate(promptInfo)


        if (auth()) {
            binding.btn.setOnClickListener {
                biometricPrompt.authenticate(promptInfo)
            }
        }

    }


    private fun auth(): Boolean {

        val biometricManager = from(this)
        return when (biometricManager.canAuthenticate(BIOMETRIC_STRONG or BIOMETRIC_WEAK)) {
            androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS -> {
                Toast.makeText(this, "App can Auth using biometric", Toast.LENGTH_SHORT).show()
                true
            }
            androidx.biometric.BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                Toast.makeText(this, "No biometric features", Toast.LENGTH_SHORT).show()
                false
            }
            androidx.biometric.BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                Toast.makeText(this, "Biometric features are currently ", Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }

    }
}