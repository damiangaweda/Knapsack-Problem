package com.pz.App

import com.pz.Controllers.MainController
import com.pz.Models.Item
import com.pz.Models.ItemsPool
import com.pz.Models.Population
import com.pz.Services.GeneticAlgorithm
import com.pz.Services.ItemsPoolService
import com.pz.Styles.Styles
import com.pz.Views.MainScreen
import tornadofx.*
import javafx.stage.Stage

class KnapsackApp : App(MainScreen::class, Styles::class)
{

    val mainController: MainController by inject()

    override fun start(stage: Stage) {
        super.start(stage)
        mainController.init()
    }
}

fun main(args: Array<String>) {
    val itemsPool = ItemsPool()
    itemsPool.addItem(Item(5.0,11.0))
    itemsPool.addItem(Item(6.0,12.0))
    itemsPool.addItem(Item(7.0,14.0))
    itemsPool.addItem(Item(8.0,15.0))
    itemsPool.addItem(Item(9.0,17.0))
    itemsPool.addItem(Item(10.0,18.0))
    itemsPool.addItem(Item(5.0,25.0))
    itemsPool.addItem(Item(1.0,3.05))

    val itemsService = ItemsPoolService()
    itemsService.saveToFile(itemsPool)
    itemsService.readFromFile(itemsPool)

    val population = Population(50,20.0,1,itemsPool)

    val answer = GeneticAlgorithm().getAnswer(population)
    println("Result: $answer")

    /*
     --- UI ---
     */
    // launch<KnapsackApp>()
}

