package com.pz.Controllers

import com.pz.Views.MainScreen
import tornadofx.Controller

class MainController : Controller()
{
    val mainScreen : MainScreen by inject()

    fun init()
    {
        with(config){

            showMainScreen("Test")

        }
    }

    fun showMainScreen(message: String, shake: Boolean = false)
    {
        mainScreen.replaceWith(mainScreen, sizeToScene = true, centerOnScreen = true)

    }

    fun testButton()
    {
        println("it works")
    }
}