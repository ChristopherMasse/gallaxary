package com.christophermasse.spaceshowcase.ui.search

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.christophermasse.spaceshowcase.R
import com.christophermasse.spaceshowcase.data.ImageWrapper
import com.christophermasse.spaceshowcase.databinding.FragSearchBinding
import com.christophermasse.spaceshowcase.databinding.ScrollviewImageBinding
import com.christophermasse.spaceshowcase.ui.GalleryViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormatSymbols
import java.util.*
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class SearchFrag:Fragment(), DatePickerDialog.OnDateSetListener {


    private var _binding: FragSearchBinding? = null;

    private val binding get() = _binding!!

    private val vm: GalleryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragSearchBinding.inflate(inflater, container, false);

        binding.btnChooseDate.setOnClickListener { clickButton() }

        var cal = Calendar.getInstance()
        val dd = cal.get(Calendar.DAY_OF_MONTH)
        val mm = (cal.get(Calendar.MONTH) + 1)
        val yyyy = cal.get(Calendar.YEAR)

        vm.searchForImageAtDate("$yyyy-$mm-$dd")

        vm.getSearchedImageLiveData().observe(viewLifecycleOwner,{
            processImageModel(it)
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null;
    }

    private fun processImageModel(i:ImageWrapper){

        val x = binding.root.width

        val o = resources.getDimension(R.dimen.fullimage_margin)
        val maxSide = (x - (2*o)).toInt()

        val b: ScrollviewImageBinding  = binding.includedScrollview
        if (maxSide > 50){
            Picasso.get().load(i.hdurl).resize(maxSide, maxSide).centerCrop().into(b.ivImage)
        } else{
            Picasso.get().load(i.hdurl).into(b.ivImage)
        }
        b.tvTitle.text = i.title
        b.tvDescription.text = i.explanation
    }


    fun clickButton(){
        var d = DatePickerDialog(requireContext())
        var min:Long = 365*5

//        d.datePicker.init(2020,12, 31, null)
        d.datePicker.maxDate = System.currentTimeMillis()
        d.datePicker.minDate = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(min)
        d.setOnDateSetListener(this)
        d.show()
    }


    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dateOfMonth: Int) {
        var m:Int = month + 1

        val monthReadable:String = DateFormatSymbols.getInstance().shortMonths[month]
        val label = "$monthReadable $month, $year"
        val query = "$year-$m-$dateOfMonth"
        binding.btnChooseDate.text = label
        vm.searchForImageAtDate(query)
    }

}