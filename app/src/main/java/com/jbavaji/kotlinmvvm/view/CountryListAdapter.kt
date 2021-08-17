package com.jbavaji.kotlinmvvm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jbavaji.kotlinmvvm.R
import com.jbavaji.kotlinmvvm.model.Country
import kotlinx.android.synthetic.main.item_country.view.*

class CountryListAdapter(var countries: ArrayList<Country>) : RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>(){

    fun updateCountries(newCountries: List<Country>){
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    class CountryViewHolder(view: View): RecyclerView.ViewHolder(view){

      private  val countryName: TextView? = view.name

        fun bind(country: Country){
            countryName?.text = country.countryName
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