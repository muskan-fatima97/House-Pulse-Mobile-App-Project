package fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alpha.housepulse.ForgotPassword
import com.alpha.housepulse.R
import com.alpha.housepulse.UpdateProfile
import com.alpha.housepulse.databinding.FragmentProfileBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_IMAGE_GALLERY = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        binding.imageCamera.setOnClickListener {
            showCustomCameraDialog()
        }

        binding.profileManage.setOnClickListener{
            val intent= Intent(requireContext(),UpdateProfile::class.java)
            startActivity(intent)
        }

        binding.passwordManage.setOnClickListener{
            val intent= Intent(requireContext(),ForgotPassword::class.java)
            startActivity(intent)
        }
    }

    private fun showCustomCameraDialog() {
        val dialogView = layoutInflater.inflate(R.layout.select_camera_dialogue, null)
        val btnCamera = dialogView.findViewById<View>(R.id.btn_camera)
        val btnGallery = dialogView.findViewById<View>(R.id.btn_gallery)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(dialogView)

        btnCamera?.setOnClickListener {
            openCamera()
            bottomSheetDialog.dismiss()
        }

        btnGallery?.setOnClickListener {
            openGallery()
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.show()
    }

    private fun openCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    private fun openGallery() {
        val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickPhoto.type = "image/*"
        startActivityForResult(pickPhoto, REQUEST_IMAGE_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    val imageBitmap = data?.extras?.get("data") as? Bitmap
                    imageBitmap?.let {
                        binding.imgProfileImage.setImageBitmap(it)
                        binding.imgProfileImage.setBackgroundResource(R.drawable.custom_capture_image)
                    }
                }
                REQUEST_IMAGE_GALLERY -> {
                    data?.data?.let { uri ->
                        binding.imgProfileImage.setImageURI(uri)
                        binding.imgProfileImage.setBackgroundResource(R.drawable.custom_capture_image)
                    }
                }
            }
        }
    }


}