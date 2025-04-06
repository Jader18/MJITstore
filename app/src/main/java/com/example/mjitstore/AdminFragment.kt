package com.example.mjitstore

import android.os.Bundle
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
        binding.toggleCpu.isChecked = CatalogFragment.StockManager.isProductInStock("cpu")
        binding.toggleRam.isChecked = CatalogFragment.StockManager.isProductInStock("ram")
        binding.toggleGpu.isChecked = CatalogFragment.StockManager.isProductInStock("gpu")
        binding.toggleSsd.isChecked = CatalogFragment.StockManager.isProductInStock("ssd")
        binding.toggleKeyboard.isChecked = CatalogFragment.StockManager.isProductInStock("keyboard")
        binding.toggleMouse.isChecked = CatalogFragment.StockManager.isProductInStock("mouse")
        binding.toggleHeadphones.isChecked = CatalogFragment.StockManager.isProductInStock("headphones")
        binding.toggleUsb.isChecked = CatalogFragment.StockManager.isProductInStock("usb")
        binding.toggleMonitor.isChecked = CatalogFragment.StockManager.isProductInStock("monitor")
        binding.togglePsu.isChecked = CatalogFragment.StockManager.isProductInStock("psu")

        // Configurar listeners para cada Switch
        binding.toggleCpu.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock("cpu", isChecked)
        }
        binding.toggleRam.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock("ram", isChecked)
        }
        binding.toggleGpu.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock("gpu", isChecked)
        }
        binding.toggleSsd.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock("ssd", isChecked)
        }
        binding.toggleKeyboard.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock("keyboard", isChecked)
        }
        binding.toggleMouse.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock("mouse", isChecked)
        }
        binding.toggleHeadphones.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock("headphones", isChecked)
        }
        binding.toggleUsb.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock("usb", isChecked)
        }
        binding.toggleMonitor.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock("monitor", isChecked)
        }
        binding.togglePsu.setOnCheckedChangeListener { _, isChecked ->
            CatalogFragment.StockManager.setProductStock("psu", isChecked)
        }

        // Configurar el botón "Guardar"
        binding.btnSaveChanges.setOnClickListener {
            Toast.makeText(requireContext(), "Cambios guardados exitosamente", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}