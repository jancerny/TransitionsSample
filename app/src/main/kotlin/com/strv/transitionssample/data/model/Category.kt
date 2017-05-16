package com.strv.transitionssample.data.model

/*
0	Uncategorized
10	Abstract
11	Animals
5	Black and White
1	Celebrities
9	City and Architecture
15	Commercial
16	Concert
20	Family
14	Fashion
2	Film
24	Fine Art
23	Food
3	Journalism
8	Landscapes
12	Macro
18	Nature
4	Nude
7	People
19	Performing Arts
17	Sport
6	Still Life
21	Street
26	Transportation New!
13	Travel
22	Underwater
27	Urban Exploration New!
25	Wedding
 */

enum class Category {
    ALL {
        override fun toString(): String = ""
    },
    UNCATEGORIZED,
    ABSTRACT,
    ANIMALS,
    BLACK_AND_WHITE,
    CELEBRITIES,
    CITY_AND_ARCHITECTURE,
    COMMERCIAL,
    CONCERT,
    FAMILY,
    FASHION,
    FILM,
    FINE_ART,
    FOOD,
    JOURNALISM,
    LANDSCAPES,
    MACRO,
    NATURE,
    NUDE,
    PEOPLE,
    PERFORMING_ARTS,
    SPORT,
    STILL_LIFE,
    STREET,
    TRANSPORTATION,
    TRAVEL,
    UNDERWATER,
    URBAN_EXPLORATION,
    WEDDING;

    override fun toString(): String {
        val original: String = name.toLowerCase()
        val result = original.split('_').map { if(it.isNotEmpty() && it != "and") it.capitalize() else it }.joinToString(" ")

        return result
    }
}
