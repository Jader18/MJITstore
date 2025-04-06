package com.example.mjitstore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.mjitstore.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startApp()
    }

    private fun startApp() {

        val navOptions = NavOptions.Builder()
            // Animaciones principales
            .setEnterAnim(R.anim.slide_in_right)       // Entrada del nuevo fragmento
            .setExitAnim(R.anim.slide_out_left)        // Salida del fragmento actual

            .setLaunchSingleTop(true)                  // Evita múltiples instancias del destino
            .setPopUpTo(R.id.loginFragment, false)     // Limpia el backstack hasta un fragmento
            .build()

        binding.bLogin.setOnClickListener {

            val user = binding.etUser.text.toString()
            val password = binding.etPassword.text.toString()

            validateUser(user, password)

        }
        binding.bGuess.setOnClickListener{
            findNavController().navigate(R.id.deLoginaMenu,null,navOptions)
        }
    }

    private fun validateUser (user: String, password: String) {

        val navOptions = NavOptions.Builder()
            // Animaciones principales
            .setEnterAnim(R.anim.slide_in_right)       // Entrada del nuevo fragmento
            .setExitAnim(R.anim.slide_out_left)        // Salida del fragmento actual

            .setLaunchSingleTop(true)                  // Evita múltiples instancias del destino
            //.setPopUpTo(R.id.loginFragment, false)     // Limpia el backstack hasta un fragmento
            .build()

        if (user == "admin" && password == "admin"){
            Toast.makeText(context, "Login succes", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.deMenuaAdmin, null, navOptions)


        }else{
            Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()

        }
    }


}