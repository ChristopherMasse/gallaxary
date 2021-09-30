package com.christophermasse.spaceshowcase.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.christophermasse.spaceshowcase.data.ImageWrapper
import com.christophermasse.spaceshowcase.databinding.VhGalleryImageBinding
import com.squareup.picasso.Picasso
import timber.log.Timber

class GalleryImageAdapter(private var itemSet: List<ImageWrapper>, private val listener:ImageClickListener):
    RecyclerView.Adapter<GalleryImageAdapter.SpaceImageViewHolder>()  {

    inner class SpaceImageViewHolder(val binding: VhGalleryImageBinding): RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener {
                listener.onImageClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpaceImageViewHolder {
        val binding = VhGalleryImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SpaceImageViewHolder(binding)
    }

    override fun onBindViewHolder(holderSpaceImage: SpaceImageViewHolder, position: Int) {
        val item = itemSet[position]
        Picasso.get().load(item.url).resize(200, 200).centerCrop().into(holderSpaceImage.binding.imageView)
        holderSpaceImage.binding.tvTitle.text = item.title

    }

    override fun getItemCount(): Int {
        return itemSet.size
    }

    fun setList(list: List<ImageWrapper>){
        itemSet = list
        Timber.d("Set list size of ${list.size}")
        notifyItemRangeChanged(0, list.size)
    }


    interface ImageClickListener{
        fun onImageClick(pos:Int)
    }

}