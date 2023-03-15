package com.yukaida.exampleapplication.image

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yukaida.exampleapplication.R

class MainTypeImageAdapter(private val context: Context, private val dataList: MutableList<String>) :
    RecyclerView.Adapter<MainTypeImageAdapter.TypeImageHolder>() {

    class TypeImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeImageHolder {
        return TypeImageHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_main_image, null)
        )
    }

    override fun onBindViewHolder(holder: TypeImageHolder, position: Int) {
        with(holder) {
            tvName.text = dataList[position]
        }

        holder.itemView.setOnClickListener {
            when (dataList[position]) {
                "倒影" -> {
                    context.startActivity(Intent(context, ImageActivity::class.java))
                }

            }
        }
    }


    override fun getItemCount(): Int = dataList.size

}