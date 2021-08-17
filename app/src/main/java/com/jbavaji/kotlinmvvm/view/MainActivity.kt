package com.jbavaji.kotlinmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jbavaji.kotlinmvvm.R
import com.jbavaji.kotlinmvvm.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    private val countryListAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        countriesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countryListAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.countries.observe(this,
            Observer { countries ->
                countries?.let {
                    countryListAdapter.updateCountries(it)
                }
            })

        viewModel.countryLoadError.observe(
            this,
            Observer { isError ->
                isError?.let {
                    list_error.visibility = if (it) View.VISIBLE else View.GONE
                }
            }
        )

        viewModel.loading.observe(this,
            Observer { isLoading ->
                isLoading?.let {
                    loading_view.visibility = if (it) View.VISIBLE else View.GONE
                    if (it) {
                        list_error.visibility = View.GONE
                        countriesList.visibility = View.GONE
                    }
                }
            })

        swipeToRefreshLayout.setOnRefreshListener{
            swipeToRefreshLayout.isRefreshing = false
        }

    }
}