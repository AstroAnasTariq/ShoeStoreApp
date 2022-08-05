package com.astroanastariq.shoestore.listing

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.astroanastariq.shoestore.R
import com.astroanastariq.shoestore.ShoesViewModel
import com.astroanastariq.shoestore.databinding.FragmentDetailsBinding
import com.astroanastariq.shoestore.databinding.FragmentListingBinding
import com.astroanastariq.shoestore.models.Shoe


class ListingFragment : Fragment(), View.OnClickListener, MenuProvider {
    private lateinit var binding: FragmentListingBinding
    private val viewModel: ShoesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addShoeButton.setOnClickListener {
            findNavController().navigate(ListingFragmentDirections.actionListingFragmentToDetailsFragment())
        }
//        setListeners()
        setupMenu()
        observeViewModel()
    }

//    private fun setListeners() {
//        binding.add_shoe_button.setOnClickListener(this)
//    }

    private fun setupMenu() {
        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun observeViewModel() {
        viewModel.shoesList.observe(viewLifecycleOwner) { it?.let { updateShoesList(it) } }
    }

    private fun updateShoesList(shoesList: List<Shoe>) {
        binding.linearLayout.removeAllViews()
        shoesList.forEach { shoe ->
            val v = LayoutInflater.from(context)
                .inflate(R.layout.item_shoe, binding.linearLayout, false)
            v.findViewById<TextView>(R.id.itemShoeName).text = shoe.name
            v.findViewById<TextView>(R.id.itemCompany).text = shoe.company
            v.findViewById<TextView>(R.id.itemSize).text = shoe.size
            v.findViewById<TextView>(R.id.itemDescription).text = shoe.description
            binding.linearLayout.addView(v)
        }
    }

    // View.OnClickListener
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.add_shoe_button -> findNavController().navigate(ListingFragmentDirections.actionListingFragmentToDetailsFragment())
        }
    }

    // MenuProvider
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_shoes_list, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.action_logout -> {
                findNavController().navigate(ListingFragmentDirections.actionListingFragmentToLoginFragment())
                true
            }
            else -> false
        }
    }
}