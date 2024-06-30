package com.alpha.homeservice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alpha.housepulse.R
import models.Service

class ServiceAdapter(private val services: List<Service>) : RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.booking_card, parent, false)
        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = services[position]
        holder.imageService.setImageResource(service.imageRes)
        holder.textServiceName.text = service.serviceName
        holder.textReviews.text = "(${service.reviews} Reviews)"
        holder.textPrice.text = service.price
        holder.textOriginalPrice.text = service.originalPrice
        holder.textProviderName.text = service.providerName
        holder.textProviderRole.text = service.providerRole
        holder.imageProvider.setImageResource(service.providerImage)
    }

    override fun getItemCount(): Int {
        return services.size
    }

    class ServiceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageService: ImageView = view.findViewById(R.id.image_service)
        val textServiceName: TextView = view.findViewById(R.id.text_service_name)
        val textReviews: TextView = view.findViewById(R.id.text_reviews)
        val textPrice: TextView = view.findViewById(R.id.text_price)
        val textOriginalPrice: TextView = view.findViewById(R.id.text_original_price)
        val textProviderName: TextView = view.findViewById(R.id.text_provider_name)
        val textProviderRole: TextView = view.findViewById(R.id.text_provider_role)
        val imageProvider: ImageView = view.findViewById(R.id.image_provider)
    }
}
