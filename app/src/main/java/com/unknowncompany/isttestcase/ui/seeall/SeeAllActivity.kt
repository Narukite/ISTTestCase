package com.unknowncompany.isttestcase.ui.seeall

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.unknowncompany.isttestcase.app.model.Movie
import com.unknowncompany.isttestcase.databinding.ActivitySeeAllBinding
import com.unknowncompany.isttestcase.ui.seeall.adapter.SeeAllAdapter
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope

class SeeAllActivity : AppCompatActivity(), SeeAllContract.View, AndroidScopeComponent {

    companion object {
        private const val MOVIES_TYPE = "moviesType"
        const val NOW_PLAYING = "nowPlaying"
        const val UPCOMING = "upcoming"

        fun launch(context: Context, moviesType: String) {
            val intent = Intent(context, SeeAllActivity::class.java)
            intent.putExtra(MOVIES_TYPE, moviesType)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivitySeeAllBinding
    private lateinit var seeAllAdapter: SeeAllAdapter
    override val scope: Scope by activityScope()
    private val router: SeeAllContract.Router by inject { parametersOf(this) }
    private val presenter: SeeAllContract.Presenter by inject { parametersOf(router) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeeAllBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        presenter.bindView(this)
        presenter.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbindView()
    }

    override fun getMoviesTypeFromIntent(): String {
        val moviesType = intent?.getStringExtra(MOVIES_TYPE) ?: ""
        if (moviesType.isBlank()) presenter.onBackClicked()
        return moviesType
    }

    override fun showLoading() {
        binding.cpi.visibility = View.VISIBLE
    }

    override fun showLoadingAndHideList() {
        binding.rv.visibility = View.INVISIBLE
        binding.cpi.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.cpi.visibility = View.INVISIBLE
    }

    override fun hideLoadingAndShowList() {
        binding.rv.visibility = View.VISIBLE
        binding.cpi.visibility = View.INVISIBLE
    }

    override fun publishData(data: List<Movie>) {
        seeAllAdapter.addData(data)
    }

    override fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun initView() {
        binding.appBar.toolbar.topAppBar.setNavigationOnClickListener { presenter.onBackClicked() }
        with(binding.rv) {
            val manager = GridLayoutManager(this@SeeAllActivity, 2)
            setHasFixedSize(true)
            layoutManager = manager
            seeAllAdapter = SeeAllAdapter(object : SeeAllAdapter.MovieListener {
                override fun onItemClick(movieId: Int) {
                    presenter.onItemClicked(movieId)
                }

                override fun onLastItemReach() {
                    presenter.onLastItemReached()
                }
            })
            adapter = seeAllAdapter
        }
    }
}