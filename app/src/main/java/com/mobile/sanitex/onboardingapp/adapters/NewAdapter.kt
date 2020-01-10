package com.mobile.sanitex.onboardingapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobile.sanitex.onboardingapp.GlideApp
import com.mobile.sanitex.onboardingapp.R
import com.mobile.sanitex.onboardingapp.data.NewData
import kotlinx.android.synthetic.main.adapter_new.view.*

class NewAdapter(private var data: List<NewData>, private val listener: OnNewClick): RecyclerView.Adapter<NewAdapter.ViewHolder>() {

    interface OnNewClick{
        fun onNewClick(position: Int, data: NewData)
    }

    fun updateData(data: List<NewData>){
        val oldSize = this.data.size
        this.data = data
        when {
            oldSize == 0 -> notifyItemRangeInserted(0, data.size)
            oldSize == data.size -> notifyItemRangeChanged(0, oldSize)
            oldSize > data.size -> {
                notifyItemRangeRemoved(data.size, oldSize - data.size)
                notifyItemRangeChanged(0, data.size)
            }
            oldSize < data.size -> {
                notifyItemRangeInserted(oldSize, data.size - oldSize)
                notifyItemRangeChanged(0, oldSize)
            }
            else -> notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_new, parent, false))

    override fun getItemCount(): Int =
        data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data.getOrNull(position)?.apply {
            GlideApp.with(holder.itemView.context).load(image).centerInside().into(holder.image)
            holder.title.text = title
            holder.date.text = getFormattedDate()
        }
    }

    inner class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        val image: ImageView = v.image
        val title: TextView = v.title
        val date: TextView = v.date
        init {
            v.setOnClickListener {
                data.getOrNull(adapterPosition)?.let {
                    listener.onNewClick(adapterPosition, it)
                }
            }
        }
    }

}