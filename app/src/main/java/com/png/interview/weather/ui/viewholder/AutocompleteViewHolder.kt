package com.png.interview.weather.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.png.interview.weather.ui.listener.AutocompleteOnClickListener
import kotlinx.android.synthetic.main.view_autocomplete_row.view.*

class AutocompleteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(location: String, autocompleteOnClickListener: AutocompleteOnClickListener?) {
        itemView.tv_autocomplete_item.text = location

        itemView.setOnClickListener {
            autocompleteOnClickListener?.onItemClick(location)
        }
    }
}
