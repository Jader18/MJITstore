package com.example.mjitstore

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.PopupMenu
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

        // Configurar visibilidad y estado de los CheckBoxes según el stock
        configureCheckBox(binding.checkboxCpu, "cpu", "Seleccionar")
        configureCheckBox(binding.checkboxRam, "ram", "Seleccionar")
        configureCheckBox(binding.checkboxGpu, "gpu", "Seleccionar")
        configureCheckBox(binding.checkboxSsd, "ssd", "Seleccionar")
        configureCheckBox(binding.checkboxKeyboard, "keyboard", "Seleccionar")
        configureCheckBox(binding.checkboxMouse, "mouse", "Seleccionar")
        configureCheckBox(binding.checkboxHeadphones, "headphones", "Seleccionar")
        configureCheckBox(binding.checkboxUsb, "usb", "Seleccionar")
        configureCheckBox(binding.checkboxMonitor, "monitor", "Seleccionar")
        configureCheckBox(binding.checkboxPsu, "psu", "Seleccionar")
        configureCheckBox(binding.checkboxHdd, "hdd", "Seleccionar")
        configureCheckBox(binding.checkbox2060, "rtx2060", "Seleccionar")
        configureCheckBox(binding.checkbox1080, "gtx1080", "Seleccionar")
        configureCheckBox(binding.checkboxRam32, "Ram32", "Seleccionar")
        configureCheckBox(binding.checkboxRam8, "Ram8", "Seleccionar")
        configureCheckBox(binding.checkboxCorei9, "Corei9", "Seleccionar")
        configureCheckBox(binding.checkboxCorei5, "Corei5", "Seleccionar")
        configureCheckBox(binding.checkboxRtx5090, "Rtx5090", "Seleccionar")

        // Mapa de categorías y sus layouts correspondientes
        val categoryMap = mapOf(
            "Todos" to listOf(
                binding.checkboxCpu.parent as ViewGroup,
                binding.checkboxRam.parent as ViewGroup,
                binding.checkboxGpu.parent as ViewGroup,
                binding.checkboxSsd.parent as ViewGroup,
                binding.checkboxKeyboard.parent as ViewGroup,
                binding.checkboxMouse.parent as ViewGroup,
                binding.checkboxHeadphones.parent as ViewGroup,
                binding.checkboxUsb.parent as ViewGroup,
                binding.checkboxMonitor.parent as ViewGroup,
                binding.checkboxPsu.parent as ViewGroup,
                binding.checkboxHdd.parent as ViewGroup,
                binding.checkbox2060.parent as ViewGroup,
                binding.checkbox1080.parent as ViewGroup,
                binding.checkboxRam32.parent as ViewGroup,
                binding.checkboxRam8.parent as ViewGroup,
                binding.checkboxCorei9.parent as ViewGroup,
                binding.checkboxCorei5.parent as ViewGroup,
                binding.checkboxRtx5090.parent as ViewGroup
            ),
            "Procesadores" to listOf(binding.checkboxCpu.parent as ViewGroup, binding.checkboxCorei9.parent as ViewGroup, binding.checkboxCorei5.parent as ViewGroup),
            "RAM" to listOf(binding.checkboxRam.parent as ViewGroup, binding.checkboxRam32.parent as ViewGroup, binding.checkboxRam8.parent as ViewGroup),
            "GPU" to listOf(binding.checkboxGpu.parent as ViewGroup, binding.checkbox2060.parent as ViewGroup, binding.checkbox1080.parent as ViewGroup, binding.checkboxRtx5090.parent as ViewGroup),
            "Almacenamiento" to listOf(binding.checkboxSsd.parent as ViewGroup, binding.checkboxUsb.parent as ViewGroup, binding.checkboxHdd.parent as ViewGroup),
            "Periféricos" to listOf(
                binding.checkboxKeyboard.parent as ViewGroup,
                binding.checkboxMouse.parent as ViewGroup,
                binding.checkboxHeadphones.parent as ViewGroup,
                binding.checkboxMonitor.parent as ViewGroup
            ),
            "Fuentes de Poder" to listOf(binding.checkboxPsu.parent as ViewGroup)
        )

        // Configurar el botón "Ordenar por"
        binding.btnSortBy.setOnClickListener { view ->
            val popup = PopupMenu(requireContext(), view)
            popup.menu.add("Todos")
            popup.menu.add("Procesadores")
            popup.menu.add("RAM")
            popup.menu.add("GPU")
            popup.menu.add("Almacenamiento")
            popup.menu.add("Periféricos")
            popup.menu.add("Fuentes de Poder")

            popup.setOnMenuItemClickListener { item: MenuItem ->
                val selectedCategory = item.title.toString()
                filterByCategory(categoryMap, selectedCategory)
                true
            }
            popup.show()
        }

        // Acción al presionar el botón para enviar al WhatsApp
        binding.btnSendToWhatsapp.setOnClickListener {
            val selectedProducts = mutableListOf<String>()

            if (binding.checkboxCpu.isChecked && binding.checkboxCpu.isEnabled) selectedProducts.add("CPU Intel i7")
            if (binding.checkboxRam.isChecked && binding.checkboxRam.isEnabled) selectedProducts.add("RAM 16GB")
            if (binding.checkboxGpu.isChecked && binding.checkboxGpu.isEnabled) selectedProducts.add("GPU RTX 3060")
            if (binding.checkboxSsd.isChecked && binding.checkboxSsd.isEnabled) selectedProducts.add("SSD 1TB")
            if (binding.checkboxHdd.isChecked && binding.checkboxHdd.isEnabled) selectedProducts.add("HDD 1TB")
            if (binding.checkboxKeyboard.isChecked && binding.checkboxKeyboard.isEnabled) selectedProducts.add("Teclado Mecánico RGB")
            if (binding.checkboxMouse.isChecked && binding.checkboxMouse.isEnabled) selectedProducts.add("Mouse Gaming 16000 DPI")
            if (binding.checkboxHeadphones.isChecked && binding.checkboxHeadphones.isEnabled) selectedProducts.add("Audífonos Inalámbricos")
            if (binding.checkboxUsb.isChecked && binding.checkboxUsb.isEnabled) selectedProducts.add("USB 64GB")
            if (binding.checkboxMonitor.isChecked && binding.checkboxMonitor.isEnabled) selectedProducts.add("Monitor 27 144Hz")
            if (binding.checkboxPsu.isChecked && binding.checkboxPsu.isEnabled) selectedProducts.add("Fuente de Poder 650W")
            if (binding.checkbox2060.isChecked && binding.checkbox2060.isEnabled) selectedProducts.add("Gigabyte GeForce RTX 2060")
            if (binding.checkbox1080.isChecked && binding.checkbox1080.isEnabled) selectedProducts.add("MSI NVIDIA GeForce GTX 1080")
            if (binding.checkboxRam32.isChecked && binding.checkboxRam32.isEnabled) selectedProducts.add("DDR5 RAM 32 GB (2 x 16 GB)")
            if (binding.checkboxRam8.isChecked && binding.checkboxRam8.isEnabled) selectedProducts.add("RAM DDR4 8GB (1x8GB)")
            if (binding.checkboxCorei9.isChecked && binding.checkboxCorei9.isEnabled) selectedProducts.add("Intel® Core™ i9-14900KF")
            if (binding.checkboxCorei5.isChecked && binding.checkboxCorei5.isEnabled) selectedProducts.add("Intel® Core™ i5-14600KF")
            if (binding.checkboxRtx5090.isChecked && binding.checkboxRtx5090.isEnabled) selectedProducts.add("MSI GeForce RTX 5090 32G Gaming Trio OC")

            if (selectedProducts.isNotEmpty()) {
                val message = "Hola MJITStore, quisiera comprar los siguientes productos:\n${selectedProducts.joinToString("\n")}"
                val phoneNumber = "50576571646" // Sin "+"

                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.setPackage("com.whatsapp")
                intent.putExtra(Intent.EXTRA_TEXT, message)
                intent.putExtra("jid", "$phoneNumber@s.whatsapp.net")

                try {
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "WhatsApp no está instalado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Selecciona al menos un producto en stock", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Función para filtrar por categoría
    private fun filterByCategory(categoryMap: Map<String, List<ViewGroup>>, category: String) {
        // Ocultar todos los layouts primero
        categoryMap.values.flatten().forEach { it.visibility = View.GONE }

        // Mostrar solo los layouts de la categoría seleccionada
        categoryMap[category]?.forEach { it.visibility = View.VISIBLE }
    }

    private fun configureCheckBox(checkBox: CheckBox, productKey: String, productName: String) {
        val inStock = StockManager.isProductInStock(productKey)
        checkBox.visibility = View.VISIBLE
        if (inStock) {
            checkBox.text = productName
            checkBox.isEnabled = true
        } else {
            checkBox.text = "$productName (Out of Stock)"
            checkBox.isEnabled = false
            checkBox.isChecked = false
        }
    }

    companion object StockManager {
        private val stockMap = mutableMapOf(
            "cpu" to true,
            "ram" to true,
            "gpu" to true,
            "ssd" to true,
            "keyboard" to true,
            "mouse" to true,
            "headphones" to true,
            "usb" to true,
            "monitor" to true,
            "psu" to true,
            "hdd" to true,
            "rtx2060" to true,
            "gtx1080" to true,
            "Ram32" to true,
            "Ram8" to true,
            "Corei9" to true,
            "Corei5" to true,
            "Rtx5090" to true
        )

        fun isProductInStock(product: String): Boolean {
            return stockMap[product] ?: false
        }

        fun setProductStock(product: String, inStock: Boolean) {
            stockMap[product] = inStock
        }

        fun getAllStock(): Map<String, Boolean> {
            return stockMap.toMap()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}