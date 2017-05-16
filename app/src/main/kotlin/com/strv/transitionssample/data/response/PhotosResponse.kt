package com.strv.transitionssample.data.response

import com.strv.transitionssample.data.model.Photo

data class PhotosResponse(val current_page: Int,
                          val total_pages: Int,
                          val total_items: Int,
                          val photos: List<Photo>) {
}