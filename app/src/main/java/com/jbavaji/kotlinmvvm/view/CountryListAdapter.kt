package com.jbavaji.kotlinmvvm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.jbavaji.kotlinmvvm.R
import com.jbavaji.kotlinmvvm.model.Country
import com.jbavaji.kotlinmvvm.util.getProgressDrawable
import com.jbavaji.kotlinmvvm.util.loadImage
import kotlinx.android.synthetic.main.item_country.view.*

class CountryListAdapter(var countries: ArrayList<Country>) :
    RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    fun updateCountries(newCountries: List<Country>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val countryName: TextView? = view.name
        private val capital: TextView = view.capital
        private val flag: ImageView = view.flag
        private val progressDrawable: CircularProgressDrawable = getProgressDrawable(view.context)

        fun bind(country: Country) {
            countryName?.text = country.countryName
            capital.text = country.capital
            flag.loadImage(country.flag, progressDrawable)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        );
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position]);
    }

    override fun getItemCount(): Int {
        return countries.size
    }
}