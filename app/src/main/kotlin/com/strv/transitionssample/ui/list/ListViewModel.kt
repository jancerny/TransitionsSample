package com.strv.transitionssample.ui.list

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.databinding.ObservableList
import android.transition.TransitionManager
import android.view.View
import com.strv.transitionssample.BR
import com.strv.transitionssample.R
import com.strv.transitionssample.data.addPhoto
import com.strv.transitionssample.data.api
import com.strv.transitionssample.data.cache
import com.strv.transitionssample.data.model.*
import com.strv.transitionssample.ui.detail.startDetailActivity
import com.strv.transitionssample.util.extensions.inTransition
import cz.kinst.jakub.viewmodelbinding.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import me.tatarka.bindingcollectionadapter2.ItemBinding

class ListViewModel : ViewModel() {

    val photos: ObservableList<Photo> = ObservableArrayList<Photo>()
    val itemBinding: ItemBinding<Photo> = ItemBinding.of<Photo>(BR.item, R.layout.activity_list_item)
            .bindExtra(BR.viewModel, this)
    val features = Feature.values().toList()
    val categories = Category.values().toList()
    val sortOptions = Sort.values().toList()
    val sortDirections = SortDirection.values().toList()
    val selectedFeature = ObservableField<Feature>(Feature.EDITORS)
    val selectedCategory = ObservableField<Category>(Category.ANIMALS)
    val selectedSortOption = ObservableField<Sort>(Sort.DEFAULT)
    val selectedSortDirection = ObservableField<SortDirection>(SortDirection.DEFAULT)


    override fun onViewModelCreated() {
        super.onViewModelCreated()

        itemBinding.bindExtra(BR.activity, activity as ListActivity)
        loadData()
    }

    fun clearData() {
        photos.clear()
        cache.photos.clear()
        cache.photosList.clear()
    }

    fun loadData(page: Int = 0) {
        api.getPhotos(only = selectedCategory.get(),
                feature = selectedFeature.get(),
                sort = selectedSortOption.get(),
                sortDirection = selectedSortDirection.get(),
                page = page + 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    response ->
                    if (page == 0) {
                        photos.clear()
                    }

                    photos.addAll(response.photos)
                    photos.forEach { cache.addPhoto(it) }
                }
    }
}