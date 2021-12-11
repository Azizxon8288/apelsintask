package com.example.apelsintask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.apelsintask.adapters.ServiceAdapter
import com.example.apelsintask.databinding.FragmentPayBinding
import com.example.apelsintask.databinding.ItemServiceBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PayFragment : Fragment() {
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

    private var _binding: FragmentPayBinding? = null
    private val binding get() = _binding!!
    private lateinit var serviceAdapter: ServiceAdapter
    private val list = listOf(
        "Shartnoma",
        "Mobil aloqa",
        "Kommunal xizmatlar",
        "Internet pro",
        "Raqamli TV",
        "Internet Xiz",
        "Tashuvchi xiz",
        "Kredit so'ndirish",
        "Xayriya",
        "Telefoniya",
        "Ta'lim",
        "Sug'urta",
        "Turizm",
        "Sport",
        "Kovorking",
        "Yuridik",
        "Byudjet",
        "Umumiy ovqatlanish",
        "Brokerlar",
        "O'yin",
        "Savdo",
        "Oriflame",
        "Yonilg'i",
        "Hosting",
        "E-hamyonlar",
        "Transport"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPayBinding.inflate(inflater, container, false)
        binding.apply {
            serviceAdapter = ServiceAdapter(list, object : ServiceAdapter.OnItemClick {
                override fun onItemClick(str: String) {
                    Toast.makeText(requireContext(), str, Toast.LENGTH_SHORT).show()
                }
            })
            rv.adapter = serviceAdapter

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
         * @return A new instance of fragment PayFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PayFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}