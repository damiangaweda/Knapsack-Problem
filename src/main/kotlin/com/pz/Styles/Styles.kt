package com.pz.Styles

import tornadofx.*

class Styles : Stylesheet()
{
    companion object {
        val mainScreen by cssclass()
    }

    init  {
        select(mainScreen) {
            padding = box(15.px)
            vgap = 7.px
            hgap = 10.px
        }
    }
}