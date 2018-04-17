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
    itemsPool.addItem(Item("Item1",5.0,11.0))
    itemsPool.addItem(Item("Item2",6.0,12.0))
    itemsPool.addItem(Item("Item3",7.0,14.0))
    itemsPool.addItem(Item("Item4",8.0,15.0))
    itemsPool.addItem(Item("Item5",9.0,17.0))
    itemsPool.addItem(Item("Item6",10.0,18.0))
    itemsPool.addItem(Item("Item7",5.0,20.0))
    itemsPool.addItem(Item("Item8",1.0,3.05))
    itemsPool.addItem(Item("Item9",2.5,17.0))

    //val itemsService = ItemsPoolService()
    //itemsService.saveToFile(itemsPool)
    //itemsService.readFromFile(itemsPool)

    val population = Population(100,20.0,1,itemsPool)

    val answer = GeneticAlgorithm().getAnswer(population)
    println("Result: $answer")
    answer.print()

    /*
     --- UI ---
     */
    // launch<KnapsackApp>()
}

