package com.example.coffee_shop_online

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class CustomAdaptor4(
    private val img: Array<Int>,
    private val texts: Array<String>,
    private val price: Array<String>
) : RecyclerView.Adapter<CustomAdaptor4.CustomViewHolder>() {

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val text1: TextView = itemView.findViewById(R.id.text1)
        val text2: TextView = itemView.findViewById(R.id.text2)
        val nextArrow: ImageView = itemView.findViewById(R.id.next_arrow)

        fun bindValue(image: Int, txt: String, price: String) {
            imageView.setImageResource(image)
            text1.text = txt
            text2.text = price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return img.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindValue(img[position], texts[position], price[position])

        // Add click listener dynamically based on position
        holder.nextArrow.setOnClickListener {
            val context: Context = holder.itemView.context
            val intent = when (position) {
                0 -> Intent(context, DescYellowCake::class.java)
                1 -> Intent(context, DescRedVelvet::class.java)
                2 -> Intent(context, DescChocolate::class.java)
                3 -> Intent(context, DescVanilla::class.java)
                4 -> Intent(context, DescWhite::class.java)
                5 -> Intent(context, DescDevilsFood::class.java)
                else -> null
            }
            intent?.let { context.startActivity(it) }
        }
    }
}