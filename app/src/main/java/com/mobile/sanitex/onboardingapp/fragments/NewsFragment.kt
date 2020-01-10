package com.mobile.sanitex.onboardingapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.sanitex.onboardingapp.R
import com.mobile.sanitex.onboardingapp.adapters.NewAdapter
import com.mobile.sanitex.onboardingapp.data.NewData
import com.mobile.sanitex.onboardingapp.tryNavigate
import com.mobile.sanitex.onboardingapp.viewModels.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment: Fragment(), NewAdapter.OnNewClick {

    private lateinit var newsViewModel: NewsViewModel
    private val adapter = NewAdapter(ArrayList(), this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_news, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel = ViewModelProviders.of(activity!!).get(NewsViewModel::class.java)

        list.layoutManager = LinearLayoutManager(requireContext())
        list.adapter = adapter

        newsViewModel.news.observe(this, Observer {
            refreshLayout.isRefreshing = false
            adapter.updateData(it)
        })

        newsViewModel.getNews()

        refreshLayout.setOnRefreshListener {
            newsViewModel.getNews()
        }

    }

    override fun onNewClick(position: Int, data: NewData) {
        tryNavigate(NewsFragmentDirections.actionNewsFragmentToNewFragment(data))
    }

}