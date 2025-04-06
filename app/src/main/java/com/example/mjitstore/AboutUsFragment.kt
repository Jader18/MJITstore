package com.example.mjitstore

import android.animation.ObjectAnimator
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import com.example.mjitstore.databinding.FragmentAboutUsBinding

class AboutUsFragment : Fragment() {

    private lateinit var binding: FragmentAboutUsBinding
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAboutUsInfo()
        setupButton()
        startScrollAnimation()
        playCreditsSound() // Reproducir el audio al mostrar el fragment
    }

    private fun setupAboutUsInfo() {
        binding.tvMembers.text = "Integrantes:\nMathius Zamora\nJader Mendoza"
        binding.tvCareer.text = "Carrera:\nIngeniería en Sistemas"
        binding.tvAppName.text = "Nombre de la aplicación:\nMJITStore"
        binding.tvPurpose.text = "Propósito:\nMJITStore es una tienda virtual diseñada para ofrecer productos tecnológicos de forma rápida y segura."
        binding.tvSkills.text = "Tecnologías:\nDesarrollada con Kotlin y ViewBinding en Android Studio."
        binding.tvCreationDate.text = "Fecha de creación:\n02/04/2025 - Versión 1.0"
    }

    private fun setupButton() {
        binding.bBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun startScrollAnimation() {
        binding.scrollView.post {
            val scrollView = binding.scrollView
            val totalHeight = binding.infoContainer.height
            val animator = ObjectAnimator.ofInt(scrollView, "scrollY", 0, totalHeight).apply {
                duration = 10000 // 10 segundos por ciclo
                interpolator = LinearInterpolator()
                repeatCount = ObjectAnimator.INFINITE // Repetir infinitamente
                repeatMode = ObjectAnimator.RESTART // Reiniciar desde el inicio
                start()
            }
            // Permitir interacción manual mientras se anima
            scrollView.isVerticalScrollBarEnabled = true
            scrollView.setOnTouchListener { _, _ ->
                animator.pause() // Pausar animación al tocar
                scrollView.postDelayed({ animator.resume() }, 2000) // Reanudar después de 2 segundos
                false // Permitir que el ScrollView maneje el toque
            }
        }
    }

    private fun playCreditsSound() {
        // Crear el MediaPlayer con el archivo de audio en res/raw
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.credits)
        mediaPlayer?.setOnCompletionListener {
            // Liberar recursos cuando el audio termine
            mediaPlayer?.release()
            mediaPlayer = null
        }
        mediaPlayer?.start() // Iniciar la reproducción
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Liberar el MediaPlayer si aún está activo
        mediaPlayer?.release()
        mediaPlayer = null
    }
}