package com.example.mytets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SchoolAdapter(private val schooles: List<school>) :
    RecyclerView.Adapter<SchoolAdapter.CountryViewHolder>() {

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameRegionCode: TextView = itemView.findViewById(R.id.nameRegionCode)
        val capital: TextView = itemView.findViewById(R.id.capital)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_school, parent, false)
        return CountryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val school = schooles[position]

        // Format the text as per the requirement
        val formattedText = """
            "${school.name}", ${school.region}
        """.trimIndent()

        holder.nameRegionCode.text = formattedText
        holder.capital.text = ""
    }

    override fun getItemCount(): Int = schooles.size
}
