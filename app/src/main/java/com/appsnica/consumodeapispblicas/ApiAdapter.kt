package com.appsnica.consumodeapispblicas

import android.content.ClipData.Item
import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.appsnica.consumodeapispblicas.databinding.ActivityMainBinding
import com.appsnica.consumodeapispblicas.databinding.ItemApiBinding

class ApiAdapter(private var dataList: MutableList<ApiResponse>) :
    RecyclerView.Adapter<ApiAdapter.ApiViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiViewHolder {
        val binding = ItemApiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ApiViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ApiViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ApiViewHolder(private val binding: ItemApiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ApiResponse) {
            binding.textId.text = "ID: ${data.id}"
            binding.textYear.text = "Año: ${data.Year}"
            binding.textTitle.text = "Título: ${data.Title}"
            binding.textHandle.text = "Handle: ${data.handle}"
            binding.textPublisher.text = "Editorial: ${data.Publisher}"
            binding.textISBN.text = "ISBN: ${data.ISBN}"
            binding.textPages.text = "Páginas: ${data.Pages}"
        }
    }

    // Nuevo método para actualizar los datos
    fun updateData(newData: List<ApiResponse>) {
        dataList.clear() // Limpia la lista actual
        dataList.addAll(newData) // Agrega los nuevos datos
        notifyDataSetChanged() // Notifica al adaptador del cambio
    }
}
// Final del adapter