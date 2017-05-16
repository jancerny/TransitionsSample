package com.strv.transitionssample.data.model


//'popular' — Return photos in Popular. Default sort: rating.
//'highest_rated' — Return photos that have been in Popular. Default sort: highest_rating.
//'upcoming' — Return photos in Upcoming. Default sort: time when Upcoming was reached.
//'editors' — Return photos in Editors' Choice. Default sort: time when selected by an editor.
//'fresh_today' — Return photos in Fresh Today. Default sort: time when reached fresh.
//'fresh_yesterday' — Return photos in Fresh Yesterday. Default sort: same as 'fresh_today'.
//'fresh_week' — Return photos in Fresh This Week. Default sort: same as 'fresh_today'.

enum class Feature {
    POPULAR,
    HIGHEST_RATED,
    UPCOMING,
    EDITORS,
    FRESH_TODAY,
    FRESH_YESTERDAY,
    FRESH_WEEK;

    override fun toString() = name.toLowerCase()
}