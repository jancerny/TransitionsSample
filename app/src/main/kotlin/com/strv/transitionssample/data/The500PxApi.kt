package com.strv.transitionssample.data

import com.strv.transitionssample.data.model.Category
import com.strv.transitionssample.data.model.Feature
import com.strv.transitionssample.data.model.Sort
import com.strv.transitionssample.data.model.SortDirection
import com.strv.transitionssample.data.response.PhotosResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface The500PxApi {

    companion object {
        const val FEATURE = "feature"
        const val ONLY = "only"
        const val IMAGE_SIZE = "image_size"
        const val PAGE = "page"
        const val SORT = "sort"
        const val SORT_DIRECTION = "sort_direction"
        const val USER_ID = "user_id"
        const val RPP = "rpp"
        const val CONSUMER_KEY = "consumer_key"

        const val FEATURE_FRESH_WEEK = "fresh_week"
        const val FEATURE_USER = "user"
    }

    @GET("photos")
    fun getPhotos(@Query(FEATURE) feature: Feature = Feature.EDITORS,
                  @Query(ONLY) only: Category = Category.ALL,
                  @Query(SORT) sort: Sort = Sort.DEFAULT,
                  @Query(SORT_DIRECTION) sortDirection: SortDirection = SortDirection.DEFAULT,
                  @Query(IMAGE_SIZE) imageSize: String = "1080",
                  @Query(USER_ID) userId: Long? = null,
                  @Query(PAGE) page: Int = 1,
                  @Query(RPP) photosPerPage: Int = 100) : Single<PhotosResponse>
}