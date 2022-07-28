package com.astroanastariq.shoestore.instructions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.astroanastariq.shoestore.R
import com.astroanastariq.shoestore.databinding.FragmentInstructionsBinding


class InstructionsFragment : Fragment() {
    private lateinit var binding: FragmentInstructionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            layoutInflater, R.layout.fragment_instructions, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonShoesList.setOnClickListener {
            findNavController().navigate(InstructionsFragmentDirections.actionInstructionsFragmentToListingFragment())
        }
    }
}