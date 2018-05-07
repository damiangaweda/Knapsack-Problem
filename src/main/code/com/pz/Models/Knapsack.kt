package com.pz.Models

import java.util.*

class Knapsack constructor(val maxCapacity: Double)
{

    var totalWeight = 0.0
    var totalValue = 0.0

    var adaptationScore: Double = 0.0

    var items = mutableListOf<Item>()

    fun ClosedRange<Int>.random() =
            Random().nextInt(endInclusive - start) +  start

    constructor(maxCapacity: Double, itemsPool: ItemsPool):this(maxCapacity){
        while (true){
            val item = itemsPool.getItemAt((0..itemsPool.getPoolSize()).random())
            if (item.weight < (maxCapacity - totalWeight))
            {
                addItem(item)
            }
            else
            {
                /* This part might need a change
                 * e.g find item closest to maxCapicity - weight and put it in*/
                break
            }
        }

        items.sortBy { item: Item -> item.weight }


        //println("new one")
        //items.forEach { e -> println(e) }
        //Thread.sleep(10000)


        rate()
    }

    fun addItem(item: Item) {
        items.add(item)
        totalValue += item.value
        totalWeight += item.weight
    }

    fun removeItem(item: Item) {
        if(items.contains(item))
        {
            items.remove(item)
            totalValue -= item.value
            totalWeight -= item.weight
        }
        else
        {
            println("no such item")
        }
    }

    fun rate(){
        adaptationScore = totalValue/maxCapacity
        val difference = maxCapacity - totalWeight
        if(difference < 0)
            adaptationScore += difference*2
        if(difference == 0.0)
            adaptationScore += totalValue/10
    }

    override fun toString(): String {
        return "TotalWeight: $totalWeight TotalValue: $totalValue AdaptationScore: $adaptationScore"
    }

    override fun equals(other: Any?): Boolean {
        other as Knapsack
        return this.totalWeight == other.totalWeight && this.totalValue == other.totalValue
    }

    fun print() {
        items.forEach { e -> println(e) }
    }

    fun getItemsList(): MutableList<Item> {
        return items;
    }

    fun set(other: Knapsack) {
        this.adaptationScore = other.adaptationScore
        this.items = other.items
        this.totalValue = other.totalValue
        this.totalWeight = other.totalWeight
    }

}