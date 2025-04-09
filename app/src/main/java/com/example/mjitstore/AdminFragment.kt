package com.example.mjitstore

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mjitstore.databinding.FragmentAdminBinding

class AdminFragment : Fragment() {
    private var _binding: FragmentAdminBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar los Switches con el estado actual del stock desde StockManager
        binding.toggleCpu.isChecked = CatalogFragment.StockManager.isProductInStock(requireContext(), "cpu")
        binding.toggleRam.isChecked = CatalogFragment.StockManager.isProductInStock(requireContext(), "ram")
        binding.toggleGpu.isChecked = CatalogFragment.StockManager.isProductInStock(requireContext(), "gpu")
        binding.toggleSsd.isChecked = CatalogFragment.StockManager.isProductInStock(requireContext(), "ssd")
        binding.toggleKeyboard.isChecked = CatalogFragment.StockManager.isProductInStock(requireContext(), "keyboard")
        binding.toggleMouse.isChecked = CatalogFragment.StockManager.isProductInStock(requireContext(), "mouse")
        binding.toggleHeadphones.isChecked = CatalogFragment.StockManager.isProductInStock(requireContext(), "headphones")
        binding.toggleUsb.isChecked = CatalogFragment.StockManager.isProductInStock(requireContext(), "usb")
        binding.toggleMonitor.isChecked = CatalogFragment.StockManager.isProductInStock(requireContext(), "monitor")
        binding.togglePsu.isChecked = CatalogFragment.StockManager.isProductInStock(requireContext(), "psu")
        binding.toggleHdd.isChecked = CatalogFragment.StockManager.isProductInStock(requireContext(), "hdd")
        binding.toggle2060.isChecked = CatalogFragment.StockManager.isProductInStock(requireContext(), "rtx2060")
        binding.toggle1080.isChecked = CatalogFragment.StockManager.isProductInStock(requireContext(), "gtx1080")
        binding.toggleRam32.isChecked = CatalogFragment.StockManager.isProductInStock(requireContext(), "Ram32")
        binding.toggleRam8.isChecked = CatalogFragment.StockManager.isProductInStock(requireContext(), "Ram8")
        binding.toggleCorei9.isChecked = CatalogFragment.StockManager.isProductInStock(requireContext(), "Corei9")
        binding.toggleCorei5.isChecked = CatalogFragment.StockManager.isProductInStock(requireContext(), "Corei5")
        binding.toggleRtx5090.isChecked = CatalogFragment.StockManager.isProductInStock(requireContext(), "Rtx5090")

        // Configurar listeners para cada Switch
        binding.toggleCpu.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock(requireContext(), "cpu", isChecked)
        }
        binding.toggleRam.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock(requireContext(), "ram", isChecked)
        }
        binding.toggleGpu.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock(requireContext(), "gpu", isChecked)
        }
        binding.toggleSsd.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock(requireContext(), "ssd", isChecked)
        }
        binding.toggleKeyboard.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock(requireContext(), "keyboard", isChecked)
        }
        binding.toggleMouse.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock(requireContext(), "mouse", isChecked)
        }
        binding.toggleHeadphones.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock(requireContext(), "headphones", isChecked)
        }
        binding.toggleUsb.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock(requireContext(), "usb", isChecked)
        }
        binding.toggleMonitor.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock(requireContext(), "monitor", isChecked)
        }
        binding.togglePsu.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock(requireContext(), "psu", isChecked)
        }
        binding.toggleHdd.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock(requireContext(), "hdd", isChecked)
        }
        binding.toggle2060.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock(requireContext(), "rtx2060", isChecked)
        }
        binding.toggle1080.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock(requireContext(), "gtx1080", isChecked)
        }
        binding.toggleRam32.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock(requireContext(), "Ram32", isChecked)
        }
        binding.toggleRam8.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock(requireContext(), "Ram8", isChecked)
        }
        binding.toggleCorei9.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock(requireContext(), "Corei9", isChecked)
        }
        binding.toggleCorei5.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock(requireContext(), "Corei5", isChecked)
        }
        binding.toggleRtx5090.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock(requireContext(), "Rtx5090", isChecked)
        }

        // Mapa de productos y sus layouts para el buscador
        val productMap = mapOf(
            "CPU Intel i7" to binding.toggleCpu.parent as ViewGroup,
            "RAM 16GB" to binding.toggleRam.parent as ViewGroup,
            "GPU RTX 3060" to binding.toggleGpu.parent as ViewGroup,
            "SSD 1TB" to binding.toggleSsd.parent as ViewGroup,
            "Teclado Mecánico RGB" to binding.toggleKeyboard.parent as ViewGroup,
            "Mouse Gaming 16000 DPI" to binding.toggleMouse.parent as ViewGroup,
            "Audífonos Inalámbricos" to binding.toggleHeadphones.parent as ViewGroup,
            "USB 64GB" to binding.toggleUsb.parent as ViewGroup,
            "Monitor 27 144Hz" to binding.toggleMonitor.parent as ViewGroup,
            "Fuente de Poder 650W" to binding.togglePsu.parent as ViewGroup,
            "Disco Duro HDD" to binding.toggleHdd.parent as ViewGroup,
            "Gigabyte GeForce RTX 2060" to binding.toggle2060.parent as ViewGroup,
            "MSI NVIDIA GeForce GTX 1080" to binding.toggle1080.parent as ViewGroup,
            "DDR5 RAM 32 GB (2 x 16 GB)" to binding.toggleRam32.parent as ViewGroup,
            "RAM DDR4 8GB (1x8GB)" to binding.toggleRam8.parent as ViewGroup,
            "Intel® Core™ i9-14900KF" to binding.toggleCorei9.parent as ViewGroup,
            "Intel® Core™ i5-14600KF" to binding.toggleCorei5.parent as ViewGroup,
            "MSI GeForce RTX 5090 32G Gaming Trio OC" to binding.toggleRtx5090.parent as ViewGroup
        )

        // Configurar el buscador
        binding.searchByName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim().lowercase()
                filterProducts(productMap, query)
            }
        })

        // Configurar el botón "Guardar"
        binding.btnSaveChanges.setOnClickListener {
            Toast.makeText(requireContext(), "Cambios guardados exitosamente", Toast.LENGTH_SHORT).show()
        }
    }

    // Función para filtrar productos por nombre
    private fun filterProducts(productMap: Map<String, ViewGroup>, query: String) {
        productMap.forEach { (name, layout) ->
            if (query.isEmpty() || name.lowercase().contains(query)) {
                layout.visibility = View.VISIBLE
            } else {
                layout.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}