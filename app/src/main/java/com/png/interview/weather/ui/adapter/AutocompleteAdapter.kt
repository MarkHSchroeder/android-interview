package com.png.interview.weather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.png.interview.R
import com.png.interview.weather.ui.listener.AutocompleteOnClickListener
import com.png.interview.weather.ui.viewholder.AutocompleteViewHolder

class AutocompleteAdapter: RecyclerView.Adapter<AutocompleteViewHolder>() {
    private val _items = mutableListOf<String>()

    var listener: AutocompleteOnClickListener? = null
    var autocompleteLocations: List<String>? get() = _items.toList()
        set(value) {
            _items.clear()
            if (value != null) {
                _items.addAll(value)
            }

            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutocompleteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_autocomplete_row, parent, false)
        return AutocompleteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return _items.size
    }

    override fun onBindViewHolder(holder: AutocompleteViewHolder, position: Int) {
        holder.bind(_items[position], listener)
    }
}