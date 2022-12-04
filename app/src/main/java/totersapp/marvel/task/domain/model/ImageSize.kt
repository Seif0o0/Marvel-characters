package totersapp.marvel.task.domain.model


enum class ImageSize(val value: String) {
    PORTRAITSMALL("portrait_small"), // 50x75px
    PORTRAITMEDIUM("portrait_medium"), // 100x150px
    PORTRAITXLARGE("portrait_xlarge"), // 150x225px
    PORTRAITFANTASTIC("portrait_fantastic"), // 168x252px
    PORTRAITUNCANNY("portrait_uncanny"), // 300x450px
    PORTRAITINCREDIBLE("portrait_incredible"), // 216x324px

    STANDARDSMALL("standard_small"), // 65x45px
    STANDARDMEDIUM("standard_medium"), // 100x100px
    STANDARDLARGE("standard_large"), // 140x140px
    STANDARDXLARGE("standard_xlarge"), // 200x200px
    STANDARDFANTASTIC("standard_fantastic"), // 250x250px
    STANDARDAMAZING("standard_amazing"), // 180x180px

    LANDSCAPESMALL("landscape_small"), // 120x90px
    LANDSCAPEMEDIUM("landscape_medium"), // 175x130px
    LANDSCAPELARGE("landscape_large"), // 190x140px
    LANDSCAPEXLARGE("landscape_xlarge"), // 270x200px
    LANDSCAPEFANTASTIC("landscape_amazing"), // 250x156px
    LANDSCAPEAMAZING("landscape_incredible"), // 464x261px

}