package com.strv.transitionssample.data.model

/*
     {
      "id": 200617235,
      "user_id": 21347077,
      "name": "My wife in the river",
      "description": null,
      "camera": "unknown",
      "lens": null,
      "focal_length": "42",
      "iso": "400",
      "shutter_speed": "1/125",
      "aperture": "9",
      "times_viewed": 116,
      "rating": 81.5,
      "status": 1,
      "created_at": "2017-02-28T03:41:24-05:00",
      "category": 4,
      "location": null,
      "latitude": null,
      "longitude": null,
      "taken_at": "2010-07-28T03:10:16-04:00",
      "hi_res_uploaded": 0,
      "for_sale": false,
      "width": 1728,
      "height": 1152,
      "votes_count": 13,
      "favorites_count": 0,
      "comments_count": 0,
      "nsfw": true,
      "sales_count": 0,
      "for_sale_date": null,
      "highest_rating": 81.5,
      "highest_rating_date": "2017-02-28T03:56:07-05:00",
      "license_type": 0,
      "converted": 0,
      "collections_count": 2,
      "crop_version": 0,
      "privacy": false,
      "profile": true,
      "image_url": "https://drscdn.500px.org/photo/200617235/w%3D280_h%3D280/4b4ca32a22bc7ad1ec0492c03ca362f2?v=0",
      "images":  [
         {
          "size": 3,
          "url": "https://drscdn.500px.org/photo/200617235/w%3D280_h%3D280/4b4ca32a22bc7ad1ec0492c03ca362f2?v=0",
          "https_url": "https://drscdn.500px.org/photo/200617235/w%3D280_h%3D280/4b4ca32a22bc7ad1ec0492c03ca362f2?v=0",
          "format": "jpeg"
        }
      ],
      "url": "/photo/200617235/my-wife-in-the-river-by-nico-spike",
      "positive_votes_count": 13,
      "converted_bits": 0,
      "store_download": false,
      "store_print": false,
      "store_license": false,
      "request_to_buy_enabled": true,
      "license_requests_enabled": false,
      "watermark": false,
      "image_format": "jpeg",
      "user":  {
        "id": 21347077,
        "username": "NicolasMonchablon",
        "firstname": "Nico",
        "lastname": "Spike",
        "city": "France",
        "country": "Chourgnac",
        "usertype": 0,
        "fullname": "Nico Spike",
        "userpic_url": "https://pacdn.500px.org/21347077/919261e6abe72ff6414f9ff327bac50fa332d58a/1.jpg?1",
        "userpic_https_url": "https://pacdn.500px.org/21347077/919261e6abe72ff6414f9ff327bac50fa332d58a/1.jpg?1",
        "cover_url": null,
        "upgrade_status": 2,
        "store_on": false,
        "affection": 77,
        "avatars":  {
          "default":  {
            "https": "https://pacdn.500px.org/21347077/919261e6abe72ff6414f9ff327bac50fa332d58a/1.jpg?1"
          },
          "large":  {
            "https": "https://pacdn.500px.org/21347077/919261e6abe72ff6414f9ff327bac50fa332d58a/2.jpg?1"
          },
          "small":  {
            "https": "https://pacdn.500px.org/21347077/919261e6abe72ff6414f9ff327bac50fa332d58a/3.jpg?1"
          },
          "tiny":  {
            "https": "https://pacdn.500px.org/21347077/919261e6abe72ff6414f9ff327bac50fa332d58a/4.jpg?1"
          }
        }
      },
      "licensing_requested": false,
      "licensing_suggested": false,
      "is_free_photo": false
    }
 */

data class Photo(val id: Long,
                 val image_url: String,
                 val images: List<PhotoUrl>,
                 val user: User) {
}

data class PhotoUrl(val size: String,
                    val url: String)