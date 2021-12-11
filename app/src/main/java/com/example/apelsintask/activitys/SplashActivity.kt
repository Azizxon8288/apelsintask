package com.example.apelsintask.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.core.content.ContextCompat
import com.example.apelsintask.R
import com.example.apelsintask.databinding.ActivitySplashBinding
import com.example.apelsintask.ui.HomeFragment
import java.util.concurrent.Executor

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var handler: Handler
    private lateinit var animTranslate: Animation

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: androidx.biometric.BiometricPrompt
    private lateinit var promptInfo: androidx.biometric.BiometricPrompt.PromptInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handler = Handler(Looper.getMainLooper())


        animTranslate = AnimationUtils.loadAnimation(this, R.anim.set)
        val loadAnimation = AnimationUtils.loadAnimation(this, R.anim.set_tv)
        val loadAnimation1 = AnimationUtils.loadAnimation(this, R.anim.from_bottom)
        val loadAnimation2 = AnimationUtils.loadAnimation(this, R.anim.anim_layout)

        binding.apply {
            image.startAnimation(animTranslate)
            tv.startAnimation(loadAnimation)
            tv2.startAnimation(loadAnimation1)
            binding.layout.startAnimation(loadAnimation2)
        }
        handler.postDelayed({
            binding.tv.visibility = View.VISIBLE
        }, 100)


//        handler.postDelayed({
//        }, 1800)


        handler.postDelayed({
            Toast.makeText(this, "Activity", Toast.LENGTH_SHORT).show()
            binding.layout.visibility = View.GONE
        }, 2000)


        handler.postDelayed({
            if (auth()) {
                biometricPrompt.authenticate(promptInfo)
            }
        }, 2100)





        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = androidx.biometric.BiometricPrompt(
            this,
            executor,
            object : androidx.biometric.BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(this@SplashActivity, "$errString", Toast.LENGTH_SHORT).show()
                }


                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                }

                override fun onAuthenticationSucceeded(result: androidx.biometric.BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    startActivity(Intent(this@SplashActivity, WorkActivity::class.java))
                    finish()

                }
            })

        promptInfo = androidx.biometric.BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Auth")
            .setSubtitle("Login using fingerprint or face")
            .setNegativeButtonText("cancel")
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.BIOMETRIC_WEAK)
            .build()


    }

    private fun auth(): Boolean {

        val biometricManager = BiometricManager.from(this)
        return when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.BIOMETRIC_WEAK)) {
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