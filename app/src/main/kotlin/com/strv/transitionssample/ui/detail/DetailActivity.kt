package com.strv.transitionssample.ui.detail

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.strv.transitionssample.R
import com.strv.transitionssample.databinding.ActivityDetailBinding
import com.strv.transitionssample.ui.detail.DetailActivity.Companion.EXTRA_PHOTO_ID
import com.strv.transitionssample.ui.list.ListActivity
import com.strv.transitionssample.util.extensions.onPageChanged
import cz.kinst.jakub.viewmodelbinding.ViewModelActivity
import android.util.Pair
import com.strv.transitionssample.data.model.Photo

class DetailActivity : ViewModelActivity<ActivityDetailBinding, DetailViewModel>() {

    companion object {
        const val EXTRA_PHOTO_ID = "photo_id"
    }

    lateinit var sharedElementCallback: DetailSharedElementCallback
    lateinit var photosAdapter: DetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setupViewModel(R.layout.activity_detail, DetailViewModel::class.java)
        super.onCreate(savedInstanceState)
        binding.activity = this

        postponeEnterTransition()

        setupToolbar()

        sharedElementCallback = DetailSharedElementCallback(intent)
        photosAdapter = DetailAdapter(sharedElementCallback)
        setEnterSharedElementCallback(sharedElementCallback)
        binding.executePendingBindings()
        initViewPager()
    }

    override fun onBackPressed() {
        setActivityResult()
        super.onBackPressed()
    }


    override fun finishAfterTransition() {
        setActivityResult()
        super.finishAfterTransition()
    }

    fun onGlideFinished(item: Photo) {
        if(item == viewModel.photo) {
            startPostponedEnterTransition()
        }
    }

    private fun initViewPager() = binding.pager.apply {
        viewModel.setupViewPagerIndex()
    }

    private fun setActivityResult() {
        val intent = Intent()
        intent.putExtra(ListActivity.EXTRA_SELECTED_ITEM_POSITION, binding.pager.currentItem)
        setResult(Activity.RESULT_OK, intent)
    }

    private fun setupToolbar() {

        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            setDisplayShowTitleEnabled(false)

            binding.toolbar.setNavigationOnClickListener { finishAfterTransition() }
        }
    }
}

fun Activity.startDetailActivity(photoId: Long, transitionSource: View?) {
    val navigationBar = window.decorView.findViewById(android.R.id.navigationBarBackground)
    val statusBar = window.decorView.findViewById(android.R.id.statusBarBackground)

    val pairs = mutableListOf(Pair(transitionSource, getString(R.string.detail_photo_transition_name, photoId)))
    navigationBar?.apply { pairs.add(Pair(this, transitionName)) }
    statusBar?.apply { pairs.add(Pair(this, transitionName)) }

    val activityOptions = ActivityOptions.makeSceneTransitionAnimation(this, *pairs.toTypedArray())
    val intent: Intent = Intent(this, DetailActivity::class.java)
            .putExtra(EXTRA_PHOTO_ID, photoId)

    startActivity(intent, activityOptions.toBundle())
}