package com.example.apelsintask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.apelsintask.databinding.FragmentScannerBinding
import androidx.core.app.ActivityCompat.startActivityForResult

import io.card.payment.CardIOActivity

import android.content.Intent
import android.util.Log
import io.card.payment.CreditCard


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ScannerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScannerFragment : Fragment() {
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

    private var _binding: FragmentScannerBinding? = null
    private val binding get() = _binding!!
    private val TAG = "ScannerFragment"

    private val SCAN_RESULT = 100
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScannerBinding.inflate(inflater, container, false)
        binding.apply {
            btn2.setOnClickListener {
                val scanIntent = Intent(requireContext(), CardIOActivity::class.java)

                // customize these values to suit your needs.
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true) // default: false
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false) // default: false
                scanIntent.putExtra(
                    CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE,
                    false
                ) // default: false

                // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
                startActivityForResult(scanIntent, SCAN_RESULT)
            }
        }
        return binding.root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SCAN_RESULT) {
            Log.d(TAG, "onActivityResult: $requestCode")
            Log.d(TAG, "onActivityResult: $data")

            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {

                val scanResult =
                    data.getParcelableExtra<CreditCard>(CardIOActivity.EXTRA_SCAN_RESULT)
                if (scanResult != null) {
                    if (scanResult.isExpiryValid) {
                        val month = scanResult.expiryMonth.toString()
                        val year = scanResult.expiryYear.toString()
                        val count = scanResult.cardType.numberLength().toString()

                        binding.tv4.text = "$count -> $year -> $month"

                    }
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ScannerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ScannerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}