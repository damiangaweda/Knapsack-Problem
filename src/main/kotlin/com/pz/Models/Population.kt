package com.pz.Models

import java.util.*

class Population constructor(private var maxSize: Int, var knapsackMaxWeight: Double, val mutationChance: Int , val itemsPool: ItemsPool ){

    private var speciments = mutableListOf<Knapsack>()

    fun add(knapsack: Knapsack){
        if(speciments.size < maxSize)
        {
            speciments.add(knapsack)
        }
        else
        {
            println("no more space :)")
        }
    }

    fun remove(knapsack: Knapsack){
        if(speciments.contains(knapsack))
        {
            speciments.remove(knapsack)
        }
        else
        {
            println("no such speciment")
        }
    }

    fun ClosedRange<Int>.random() =
            Random().nextInt(endInclusive - start) +  start

    fun setRandom(){
        for(i in 0 until maxSize){
            add(Knapsack(knapsackMaxWeight, itemsPool))
        }
    }

    fun printPopulation(){
        println("Population: $this")
        speciments.forEach { it -> println(it.toString()) }
    }

    fun getize(): Int
    {
        return speciments.size
    }

    fun sort(){
        speciments.sortBy { e -> e.adaptationScore }
    }

    fun sumScore(): Double{
        return speciments.sumByDouble { e -> e.adaptationScore }
    }

    fun scalePointsTo100(){
        val totalScore = sumScore()
        speciments.forEach { e -> e.adaptationScore = (e.adaptationScore/totalScore * 100).toInt().toDouble()}

    }

    fun crossover(A: Knapsack, B: Knapsack): Knapsack
    {
        val middleIndexA: Int = A.items.size/2
        val middleIndexB: Int = B.items.size/2

        val C = Knapsack(A.maxCapacity)

        for (i in 0 until middleIndexA)
            C.addItem(A.items[i])

        for (i in middleIndexB until B.items.size)
            C.addItem(B.items[i])

        return  C
    }

    fun findBest(): Knapsack
    {
        var best = speciments.first()
        for(i in 0 until speciments.size){
            if(speciments[i].adaptationScore > best.adaptationScore)
                best = speciments[i]
        }

        return best
    }

    fun mutate(subject: Knapsack): Knapsack
    {
        if((0..100).random() <= mutationChance){
            subject.items[(0..subject.items.size).random()] = itemsPool.getItemAt((0..itemsPool.getPoolSize()).random())
        }

        return subject
    }

    fun reassemblePopulation(){
        scalePointsTo100()
        val specimentsPool = mutableListOf<Knapsack>()

        for (i in 0 until speciments.size){
            if(speciments[i].adaptationScore != 0.0)
                for (j in 0 until speciments[i].adaptationScore.toInt()){
                    specimentsPool.add(speciments[i])
                }
        }

        speciments.clear()


        for (i in 0 until maxSize){
            var subject = crossover(specimentsPool[(0..specimentsPool.size).random()],specimentsPool[(0..specimentsPool.size).random()])
            subject = mutate(subject)
            speciments.add(subject)
            speciments[i].rate()
            println("Score tst: ${speciments[i]}")
        }
    }

}