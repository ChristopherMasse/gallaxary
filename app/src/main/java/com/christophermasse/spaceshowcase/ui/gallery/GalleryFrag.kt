package com.christophermasse.spaceshowcase.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.christophermasse.spaceshowcase.databinding.FragGalleryBinding
import com.christophermasse.spaceshowcase.ui.GalleryViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class GalleryFrag: Fragment(), GalleryImageAdapter.ImageClickListener  {

    private var _binding: FragGalleryBinding? = null;

    private val binding get() = _binding!!

    private var adapter:GalleryImageAdapter? = null

    //    private val vm: GalleryViewModel by viewModels
    private val vm: GalleryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragGalleryBinding.inflate(inflater, container, false)

        binding.recyclerview.layoutManager = GridLayoutManager(context, 2)
        adapter = GalleryImageAdapter(ArrayList(), this)// Empty
        binding.recyclerview.adapter = adapter

        vm.getImageListLiveData().observe(
            viewLifecycleOwner,
            {
                adapter!!.setList(it)
            }
        )

        vm.isLoading().observe(
            viewLifecycleOwner, {
                showLoading(it)
            }
        )


        var cal = Calendar.getInstance()
        var dd = cal.get(Calendar.DAY_OF_MONTH)
        var mm = (cal.get(Calendar.MONTH) + 1)
        var yyyy = cal.get(Calendar.YEAR)

        val end = "$yyyy-$mm-$dd"
        cal.add(Calendar.MONTH, -1)
        dd = cal.get(Calendar.DAY_OF_MONTH)
        mm = (cal.get(Calendar.MONTH) + 1)
        yyyy = cal.get(Calendar.YEAR)
        val start = "$yyyy-$mm-$dd"



        vm.findImagesInRange(start, end)

        vm.getClickedItemLiveData().observe(viewLifecycleOwner, {
            var m = "${it.date}: ${it.title}"
            Toast.makeText(context, m, Toast.LENGTH_LONG).show()

        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null;
        adapter = null
    }

    override fun onImageClick(pos: Int) {
        vm.chooseItem(pos)
    }

    private fun showLoading(showLoadingView: Boolean){
        if (showLoadingView){
            binding.recyclerview.visibility = View.GONE
            binding.loadingView.visibility = View.VISIBLE
        } else{
            binding.loadingView.visibility = View.GONE
            binding.recyclerview.visibility = View.VISIBLE
        }
    }
}