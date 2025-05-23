package com.example.mjitstore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.mjitstore.R
import com.example.mjitstore.databinding.FragmentMainMenuBinding
import android.view.MenuItem
import android.widget.PopupMenu


class MainMenuFragment : Fragment() {

    private lateinit var binding: FragmentMainMenuBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startApp()
        binding.btnMenu.setOnClickListener { view ->
            val popup = PopupMenu(requireContext(), view)
            popup.menu.add("Admin")

            popup.setOnMenuItemClickListener { item: MenuItem ->
                when (item.title) {
                    "Admin" -> {
                        val navOptions = NavOptions.Builder()
                            .setEnterAnim(R.anim.slide_in_right)
                            .setExitAnim(R.anim.slide_out_left)
                            .setPopEnterAnim(R.anim.slide_in_left)
                            .setPopExitAnim(R.anim.slide_out_right)
                            .setLaunchSingleTop(true)
                            .build()
                        // Navegar a AdminFragment
                        findNavController().navigate(R.id.deMenuaLogin, null, navOptions)
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }
    }

    private fun startApp(){

        val navOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_right)
            .setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.slide_in_left)
            .setPopExitAnim(R.anim.slide_out_right)
            .setLaunchSingleTop(true)
            .build()


        binding.btnAbout.setOnClickListener{

            findNavController().navigate(R.id.deMenutoAbout,null,navOptions)
        }

        binding.btnCatalogo.setOnClickListener{

            findNavController().navigate(R.id.deMenuaCatalogo,null,navOptions)
        }


    }
}