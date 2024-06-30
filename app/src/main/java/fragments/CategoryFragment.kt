package fragments

import adapters.CategoryAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.alpha.housepulse.R
import com.alpha.housepulse.databinding.FragmentCategoryBinding
import models.Category

class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categories = listOf(
            Category(R.drawable.drill_machine, "Carpenter", 6),
            Category(R.drawable.house_cleaning, "Cleaner", 5),
            Category(R.drawable.paint_roller, "Painter", 2),
            Category(R.drawable.electrician_tools, "Electrician", 1),
            Category(R.drawable.air_conditioner, "AC Repair", 3),
            Category(R.drawable.plumber, "Plumber", 2),
            Category(R.drawable.salon, "Men's Salon", 5),
            Category(R.drawable.tv_repair, "TV Repair", 5),
            Category(R.drawable.window_cleaner, "Glass Cleaning", 5),
        )

        val adapter = CategoryAdapter(categories)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
