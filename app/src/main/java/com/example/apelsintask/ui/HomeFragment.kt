package com.example.apelsintask.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.Fragment
import com.example.apelsintask.databinding.FragmentHomeBinding
import com.mikhaellopez.biometric.BiometricHelper
import com.mikhaellopez.biometric.BiometricPromptInfo
import com.mikhaellopez.biometric.BiometricType

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val biometricHelper = BiometricHelper(this)

        // BiometricType = FACE, FINGERPRINT, IRIS, MULTIPLE or NONE
        val face = BiometricType.FACE
        val biometricType: BiometricType = biometricHelper.getBiometricType()



        // Check if biometric is available on the device
        binding.btn.visibility = if (biometricHelper.biometricEnable()) View.VISIBLE else View.GONE

        binding.btn.setOnClickListener {
            biometricHelper
                .showBiometricPrompt(
                BiometricPromptInfo(
                    title = "Title", // Mandatory
                    negativeButtonText = "Cancel", // Mandatory
                    subtitle = "Subtitle",
                    description = "Description",
                    confirmationRequired = true
                )
            ) {
                Log.d("TAG", "onCreateView: $it")
            }


//
//            biometricHelper.showBiometricPrompt(promptInfo,
//                onError = { errorCode: Int, errString: CharSequence ->
//                    // Do something when error
//
//                }, onFailed = {
//                    // Do something when failed
//
//                }, onSuccess = { result: BiometricPrompt.AuthenticationResult ->
//                    // Do something when success
//
//                })
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}