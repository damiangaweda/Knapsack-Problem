package com.pz.Views

import com.pz.Controllers.MainController
import javafx.geometry.Pos
import tornadofx.*

class MainScreen : View("Main Window")
{
    val mainController: MainController by inject()

    override val root = borderpane {
        setPrefSize(800.0,600.0)


        center {
            vbox(spacing = 15) {
                alignment = Pos.CENTER

                button ( "Test" ){
                    setOnAction {
                        mainController.testButton()
                    }
                }
            }
        }
    }

}