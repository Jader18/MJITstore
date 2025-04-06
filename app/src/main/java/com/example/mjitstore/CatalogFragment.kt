package com.example.mjitstore

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mjitstore.databinding.FragmentCatalogBinding

class CatalogFragment : Fragment() {
    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Verificar si los productos están en stock y mostrar/ocultar CheckBoxes
        binding.checkboxCpu.visibility = if (isProductInStock("cpu")) View.VISIBLE else View.GONE
        binding.checkboxRam.visibility = if (isProductInStock("ram")) View.VISIBLE else View.GONE
        binding.checkboxGpu.visibility = if (isProductInStock("gpu")) View.VISIBLE else View.GONE
        binding.checkboxSsd.visibility = if (isProductInStock("ssd")) View.VISIBLE else View.GONE
        binding.checkboxKeyboard.visibility = if (isProductInStock("keyboard")) View.VISIBLE else View.GONE
        binding.checkboxMouse.visibility = if (isProductInStock("mouse")) View.VISIBLE else View.GONE
        binding.checkboxHeadphones.visibility = if (isProductInStock("headphones")) View.VISIBLE else View.GONE
        binding.checkboxUsb.visibility = if (isProductInStock("usb")) View.VISIBLE else View.GONE
        binding.checkboxMonitor.visibility = if (isProductInStock("monitor")) View.VISIBLE else View.GONE
        binding.checkboxPsu.visibility = if (isProductInStock("psu")) View.VISIBLE else View.GONE

        // Acción al presionar el botón para enviar al WhatsApp
        binding.btnSendToWhatsapp.setOnClickListener {
            val selectedProducts = mutableListOf<String>()

            // Verificar qué productos están seleccionados
            if (binding.checkboxCpu.isChecked) selectedProducts.add("CPU Intel i7")
            if (binding.checkboxRam.isChecked) selectedProducts.add("RAM 16GB")
            if (binding.checkboxGpu.isChecked) selectedProducts.add("GPU RTX 3060")
            if (binding.checkboxSsd.isChecked) selectedProducts.add("SSD 1TB")
            if (binding.checkboxKeyboard.isChecked) selectedProducts.add("Teclado Mecánico RGB")
            if (binding.checkboxMouse.isChecked) selectedProducts.add("Mouse Gaming 16000 DPI")
            if (binding.checkboxHeadphones.isChecked) selectedProducts.add("Audífonos Inalámbricos")
            if (binding.checkboxUsb.isChecked) selectedProducts.add("USB 64GB")
            if (binding.checkboxMonitor.isChecked) selectedProducts.add("Monitor 27\" 144Hz")
            if (binding.checkboxPsu.isChecked) selectedProducts.add("Fuente de Poder 650W")

            if (selectedProducts.isNotEmpty()) {
                val message = "Hola MJITStore, quisiera comprar los siguientes productos:\n" + selectedProducts.joinToString("\n")
                val phoneNumber = "+50576571646" // Número de teléfono de WhatsApp
                val url = "https://wa.me/$phoneNumber?text=${Uri.encode(message)}"

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

                // Verificar si WhatsApp está instalado
                val packageManager = requireActivity().packageManager
                val activities = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
                if (activities.isNotEmpty()) {
                    startActivity(intent)
                } else {
                    Toast.makeText(requireContext(), "WhatsApp no está instalado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Selecciona al menos un producto", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Simulación de la función que verifica si el producto está en stock
    private fun isProductInStock(product: String): Boolean {
        return when (product) {
            "cpu" -> true
            "ram" -> true
            "gpu" -> true
            "ssd" -> true
            "keyboard" -> true
            "mouse" -> true
            "headphones" -> true
            "usb" -> true
            "monitor" -> true
            "psu" -> true
            else -> false
        }
    }
}