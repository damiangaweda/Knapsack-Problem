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
        var penaltyPoints = 0.0
        if(totalWeight > maxCapacity)
            penaltyPoints = Math.abs((totalWeight - maxCapacity)/maxCapacity)*2
        if(totalWeight < maxCapacity)
            penaltyPoints = Math.abs((totalWeight - maxCapacity)/maxCapacity)

        adaptationScore = if((totalWeight - maxCapacity) != 0.0)
            (totalValue/Math.abs(totalWeight - maxCapacity)) - penaltyPoints
        else {
            totalValue + 5
        }
    }

    override fun toString(): String {
        return "TotalWeight: $totalWeight TotalValue: $totalValue AdaptationScore: $adaptationScore"
    }

    fun equals(other: Knapsack): Boolean {
        return this.totalWeight == other.totalWeight && this.totalValue == other.totalValue && this.adaptationScore == other.adaptationScore
    }

}