package com.strv.transitionssample.data.model

/*
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
      }
 */
data class User(val id: Long,
                val fullname: String,
                val city: String,
                val country: String) {
}