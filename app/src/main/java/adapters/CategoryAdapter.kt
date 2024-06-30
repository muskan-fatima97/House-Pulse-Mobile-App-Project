package adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alpha.housepulse.databinding.CategoryCardviewBinding
import models.Category

class CategoryAdapter(private val categories: List<Category>) :

        RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

        inner class CategoryViewHolder(private val binding: CategoryCardviewBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(category: Category) {
                binding.categoryIcon.setImageResource(category.iconResId)
                binding.categoryName.text = category.name
                binding.categoryServicesCount.text = "${category.servicesCount} Services"
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
            val binding = CategoryCardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CategoryViewHolder(binding)
        }

        override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
            holder.bind(categories[position])
        }

        override fun getItemCount(): Int {
            return categories.size
        }
    }

