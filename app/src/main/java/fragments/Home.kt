package fragments

import adapters.ImageAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.alpha.homeservice.ServiceAdapter
import com.alpha.housepulse.R
import models.ImageItem
import models.Service
import java.util.UUID

class Home : Fragment() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var pageChangeListener: ViewPager2.OnPageChangeCallback

    private val params = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply {
        setMargins(8, 0, 8, 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager2 = view.findViewById(R.id.viewpager2_one)
        val slideDotAll = view.findViewById<LinearLayout>(R.id.slideDotAll)

        val images = arrayListOf(
            ImageItem(
                UUID.randomUUID().toString(),
                "android.resource://" + requireContext().packageName + "/" + R.drawable.slider_img_one
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                "android.resource://" + requireContext().packageName + "/" + R.drawable.slider_img_2
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                "android.resource://" + requireContext().packageName + "/" + R.drawable.slider_img_one
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                "android.resource://" + requireContext().packageName + "/" + R.drawable.slider_img_2
            ),
        )

        val imageAdapter = ImageAdapter()
        viewPager2.adapter = imageAdapter
        imageAdapter.submitList(images)

        val dotsImage = Array(images.size) { ImageView(requireContext()) }

        dotsImage.forEach {
            it.setImageDrawable(
                resources.getDrawable(R.drawable.non_active_dot, requireContext().theme)
            )
            slideDotAll.addView(it, params)
        }
        dotsImage[0].setImageDrawable(
            resources.getDrawable(R.drawable.active_dot, requireContext().theme)
        )

        pageChangeListener = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                dotsImage.forEachIndexed { index, imageView ->
                    if (position == index) {
                        imageView.setImageDrawable(
                            resources.getDrawable(R.drawable.active_dot, requireContext().theme)
                        )
                    } else {
                        imageView.setImageDrawable(
                            resources.getDrawable(R.drawable.non_active_dot, requireContext().theme)
                        )
                    }
                }
                super.onPageSelected(position)
            }
        }
        viewPager2.registerOnPageChangeCallback(pageChangeListener)

        // Set up data for first RecyclerView
        val services1 = listOf(
            Service(R.drawable.computer_repairer, "Complete PC Repairing", 222, "$30", "$80", "Tom Cruise", "Service Provider", R.drawable.tom_cruis),
            Service(R.drawable.electric_repairing_, "Socket Repairing", 215, "$50", "$90", "Tom Hiddleston", "Service Provider", R.drawable.tom_hiddlston),
            Service(R.drawable.human_plumber, "Kitchen Taps and Plumbing", 200, "$20", "$70", "Mark Anthony", "Service Provider", R.drawable.jungkook),
            Service(R.drawable.men_hair_dresser, "Complete Kitchen Cleaning", 160, "$60", "$100", "Mark Williams", "Service Provider", R.drawable.service_provider_one)
            // Add more service items here
        )

        // Set up data for second RecyclerView
        val services2 = listOf(
            Service(R.drawable.electric_repairing_, "Socket Repairing", 160, "$150", "$180", "Tom Hiddleston", "Service Provider", R.drawable.tom_hiddlston),
            Service(R.drawable.men_hair_dresser, "Barber", 222, "$100", "$120", "Steve Rogers", "Service Provider", R.drawable.service_provider_one),
            Service(R.drawable.human_plumber, "Kitchen Taps and Plumbing", 215, "$150", "$180", "Mark Anthony", "Service Provider", R.drawable.jungkook),
            Service(R.drawable.computer_repairer, "Computer Repairing", 200, "$70", "$90", "Tony Stark", "Service Provider", R.drawable.tom_cruis)
            // Add more service items here
        )

        // Set up RecyclerViews
        val recyclerView1 = view.findViewById<RecyclerView>(R.id.recyclerView1)
        val recyclerView2 = view.findViewById<RecyclerView>(R.id.recyclerView2)

        setupRecyclerView(recyclerView1, services1)
        setupRecyclerView(recyclerView2, services2)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, services: List<Service>) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = ServiceAdapter(services)
    }
}
