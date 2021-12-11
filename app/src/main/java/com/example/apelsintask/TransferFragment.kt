package com.example.apelsintask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.apelsintask.adapters.TransfersAdapter
import com.example.apelsintask.databinding.FragmentTransferBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TransferFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TransferFragment : Fragment() {
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

    private var _binding: FragmentTransferBinding? = null
    private val binding get() = _binding!!
    private lateinit var transfersAdapter: TransfersAdapter
    private val list = listOf(
        "Kartaga o'tkazma",
        "Hamyonga o'tkazma",
        "Hisob raqamga o'tkazma",
        "Rekvizitlarga o'tkazma",
        "Mablag' yig'ish",
        "Raketa"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransferBinding.inflate(inflater, container, false)
        binding.apply {
            transfersAdapter = TransfersAdapter(list, object : TransfersAdapter.OnItemClick {
                override fun onItemClick(str: String) {
                    Toast.makeText(requireContext(), "$str", Toast.LENGTH_SHORT).show()
                }

            })
            rv.adapter = transfersAdapter
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
         * @return A new instance of fragment TransferFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TransferFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}