package com.strv.transitionssample.ui.list

import android.app.Activity
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.Transition
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.strv.transitionssample.R
import com.strv.transitionssample.data.cache
import com.strv.transitionssample.data.model.Photo
import com.strv.transitionssample.databinding.ActivityListBinding
import com.strv.transitionssample.ui.detail.DetailSharedElementCallback
import com.strv.transitionssample.ui.detail.startDetailActivity
import com.strv.transitionssample.util.extensions.inTransition
import com.strv.transitionssample.util.extensions.onPreDraw
import com.strv.transitionssample.util.extensions.onTransitionEnd
import com.strv.transitionssample.util.external.onLoadMore
import cz.kinst.jakub.viewmodelbinding.ViewModelActivity

class ListActivity : ViewModelActivity<ActivityListBinding, ListViewModel>() {
    var epicenter: Rect? = null
    lateinit var bottomSheetBehavior: BottomSheetBehavior<NestedScrollView>

    companion object {
        val EXTRA_SELECTED_ITEM_POSITION = "item_position"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setupViewModel(R.layout.activity_list, ListViewModel::class.java)
        super.onCreate(savedInstanceState)

        postponeEnterTransition()

        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)
        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.d("LLL", "slide")
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                Log.d("LLL", "statechanged")
            }

        })

        window.sharedElementExitTransition.onTransitionEnd {
            (this@ListActivity as Activity).setExitSharedElementCallback(null)
        }

        window.exitTransition.epicenterCallback = object : Transition.EpicenterCallback() {
            override fun onGetEpicenter(transition: Transition?): Rect? = epicenter
        }

        binding.executePendingBindings()
        setupLazyLoading()
    }

    override fun onResume() {
        super.onResume()

        binding.fab.inTransition {
            visibility = View.VISIBLE
        }

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

    override fun onActivityReenter(resultCode: Int, data: Intent?) {
        postponeEnterTransition()

        var setupCallbackViewSuccess = false

        if (data == null) {
            return
        }

        val itemPosition = data.getIntExtra(EXTRA_SELECTED_ITEM_POSITION, -1)
        val callback = DetailSharedElementCallback(data).apply {
            tName = getString(R.string.detail_photo_transition_name, viewModel.photos[itemPosition].id)
        }

        binding.recycler.onPreDraw {
            if (!setupCallbackViewSuccess) {
                callback.setupCallbackView(itemPosition)
            }

            resumeTransitionIfReady()
            true
        }

        binding.recycler.scrollToPosition(itemPosition)
        setupCallbackViewSuccess = callback.setupCallbackView(itemPosition)
        setExitSharedElementCallback(callback)
    }

    fun onItemClick(view: View, item: Photo) {
        epicenter = view.clipBounds

        startDetailActivity(item.id, view)
    }

    fun onFilterClick(view: View) {
        bottomSheetBehavior.state = when (bottomSheetBehavior.state) {
            BottomSheetBehavior.STATE_EXPANDED -> {
                viewModel.clearData()
                viewModel.loadData()
                BottomSheetBehavior.STATE_HIDDEN
            }
            else -> BottomSheetBehavior.STATE_EXPANDED
        }
    }


    private fun resumeTransitionIfReady() {
        startPostponedEnterTransition()
    }

    private fun setupLazyLoading() {
        binding.recycler.onLoadMore { page, _ -> viewModel.loadData(page) }
    }

    private fun DetailSharedElementCallback.setupCallbackView(itemPosition: Int): Boolean {
        val viewHolder: RecyclerView.ViewHolder? = binding.recycler.findViewHolderForAdapterPosition(itemPosition)
        val imageView = viewHolder?.itemView as? ImageView ?: return false
        photo = imageView

        return true
    }
}