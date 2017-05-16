package com.strv.transitionssample.data

import com.strv.transitionssample.data.model.Photo

class Cache {
    val photos: MutableMap<Long, Photo> by lazy { HashMap<Long, Photo>() }
    val photosList: MutableList<Photo> by lazy { ArrayList<Photo>() }
}

fun Cache.addPhoto(photo: Photo) {
    photos.put(photo.id, photo)
    photosList.add(photo)
}