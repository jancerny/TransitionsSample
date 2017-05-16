package com.strv.transitionssample.ui.detail

import android.databinding.ObservableArrayList
import android.databinding.ObservableInt
import android.databinding.ObservableList
import com.strv.transitionssample.BR
import com.strv.transitionssample.R
import com.strv.transitionssample.data.cache
import com.strv.transitionssample.data.model.Photo
import cz.kinst.jakub.viewmodelbinding.ViewModel
import me.tatarka.bindingcollectionadapter2.BindingCollectionAdapter
import me.tatarka.bindingcollectionadapter2.ItemBinding

class DetailViewModel : ViewModel() {

    val items: ObservableList<Photo> = ObservableArrayList()
    val itemBinding: ItemBinding<Photo> = ItemBinding.of<Photo>(BR.item, R.layout.activity_detail_item)
            .bindExtra(BR.viewModel, this)
    val index: ObservableInt = ObservableInt()

    var photo: Photo? = null

    override fun onViewModelCreated() {
        super.onViewModelCreated()

        itemBinding.bindExtra(BR.activity, activity)
        loadData()
    }

    fun loadData() {
        items.clear()
        items.addAll(cache.photosList)
    }

    fun setupViewPagerIndex() {
        val photoId = activity.intent.getLongExtra(DetailActivity.EXTRA_PHOTO_ID, -1)
        photo = cache.photos[photoId]
        val i = cache.photosList.indexOf(photo)
        index.set(i)
    }
}