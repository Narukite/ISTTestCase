package com.unknowncompany.isttestcase.ui.main

import android.os.Bundle
import android.view.View.*
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.unknowncompany.isttestcase.app.model.MainMovie
import com.unknowncompany.isttestcase.databinding.ActivityMainBinding
import com.unknowncompany.isttestcase.ui.main.adapter.MainAdapter
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope

class MainActivity : AppCompatActivity(), MainContract.View, AndroidScopeComponent {

    private lateinit var binding: ActivityMainBinding
    override val scope: Scope by activityScope()
    private val router: MainContract.Router by inject { parametersOf(this) }
    private val presenter: MainContract.Presenter by inject { parametersOf(router) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        presenter.bindView(this)
        presenter.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbindView()
    }

    override fun showLoadingForNowPLaying() {
        binding.rvNowPlaying.visibility = INVISIBLE
        binding.cpiNowPlaying.visibility = VISIBLE
    }

    override fun hideLoadingForNowPlaying() {
        binding.rvNowPlaying.visibility = VISIBLE
        binding.cpiNowPlaying.visibility = GONE
    }

    override fun showLoadingForUpcoming() {
        binding.rvUpcoming.visibility = INVISIBLE
        binding.cpiUpcoming.visibility = VISIBLE
    }

    override fun hideLoadingForUpcoming() {
        binding.rvUpcoming.visibility = VISIBLE
        binding.cpiUpcoming.visibility = GONE
    }

    override fun publishDataForNowPlaying(data: List<MainMovie>) {
        binding.rvNowPlaying.adapter = MainAdapter(data, object : MainAdapter.MovieListener {
            override fun onItemClick(movieId: Int) {
                presenter.onItemClicked(movieId)
            }
        })
    }

    override fun publishDataForUpcoming(data: List<MainMovie>) {
        binding.rvUpcoming.adapter = MainAdapter(data, object : MainAdapter.MovieListener {
            override fun onItemClick(movieId: Int) {
                presenter.onItemClicked(movieId)
            }
        })
    }

    override fun showMessage(msg: String) {
        Toast.makeText(this, msg, LENGTH_SHORT).show()
    }

    private fun initView() {
        with(binding.rvNowPlaying) {
            val manager =
                LinearLayoutManager(this@MainActivity).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
            setHasFixedSize(true)
            layoutManager = manager
        }
        with(binding.rvUpcoming) {
            val manager =
                LinearLayoutManager(this@MainActivity).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
            setHasFixedSize(true)
            layoutManager = manager
        }
    }
}