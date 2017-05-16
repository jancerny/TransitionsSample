package com.strv.transitionssample.data.model

enum class Sort {
    DEFAULT {
        override fun toString() = ""
    },
    CREATED_AT,
    RATING,
    HIGHEST_RATING,
    TIMES_VIEWED,
    VOTES_COUNT,
    COMMENTS_COUNT,
    TAKEN_AT;

    override fun toString() = name.toLowerCase()
}

enum class SortDirection {
    DEFAULT {
        override fun toString() = ""
    },
    ASC,
    DESC;

    override fun toString() = name.toLowerCase()
}
