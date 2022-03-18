package com.unknowncompany.isttestcase.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.unknowncompany.isttestcase.R
import com.unknowncompany.isttestcase.app.model.DetailMovie
import com.unknowncompany.isttestcase.databinding.ActivityDetailBinding
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope

class DetailActivity : AppCompatActivity(), DetailContract.View, AndroidScopeComponent {

    companion object {
        private const val MOVIE_ID = "movieId"

        fun launch(context: Context, movieId: Int) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(MOVIE_ID, movieId)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityDetailBinding
    override val scope: Scope by activityScope()
    private val router: DetailContract.Router by inject { parametersOf(this) }
    private val presenter: DetailContract.Presenter by inject { parametersOf(router) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        presenter.bindView(this)
        presenter.onViewCreated()
    }

    override fun getMovieIdFromIntent(): Int {
        val movieId = intent?.getIntExtra(MOVIE_ID, -1) as Int
        if (movieId == -1) presenter.onBackClicked()
        return movieId
    }

    override fun showLoading() {
        binding.cl.visibility = INVISIBLE
        binding.cpi.visibility = VISIBLE
    }

    override fun hideLoading() {
        binding.cl.visibility = VISIBLE
        binding.cpi.visibility = INVISIBLE
    }

    override fun populateView(data: DetailMovie) {
        with(binding) {
            Glide.with(this@DetailActivity).load(data.posterPath).into(ivMoviePoster)
            tvMovieTitle.text = data.originalTitle
            tvMovieReleaseDate.text = data.releaseDate
            tvMoviePopularity.text = getString(R.string.template_viewers, data.popularity)
            tvMovieGenre.text = data.genre
            tvMovieVoteAverage.text = data.voteAverage
            tvMovieOverview.text = data.overview
        }
    }

    override fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun initView() {
        binding.appBar.toolbar.topAppBar.setNavigationOnClickListener { presenter.onBackClicked() }
    }
}