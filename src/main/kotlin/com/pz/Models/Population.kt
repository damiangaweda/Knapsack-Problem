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
        println("\n Population: $this")
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


    /**
     * TODO Losowanie pivotu
     *
     * Problem --> 1 plecak 11 indeksów; 2 plecak 7 indexów
     *
     * Rozwiązanie -> losowanie pivotu z 1, losowanie pivotu z 2
     *                od pivotu 1, dodawać itemy z 2 aż do skończenia miejsca
     *
     *                Analogicznie dla drugiego
     *
     *
     * Dodać dodawanie najlżejszych przedmiotów na samym końcu
     */

    fun crossover(A: Knapsack, B: Knapsack): Knapsack
    {
        val middleIndexA: Int = (0..A.items.size).random()
        var middleIndexB: Int = (0..B.items.size).random()

        val C = Knapsack(A.maxCapacity)

        for(i in 0 until middleIndexA)
            C.addItem(A.items[i])

        //while ( middleIndexB < B.items.size && (C.totalWeight + B.items[middleIndexB].weight) <= C.maxCapacity ){
        //   C.addItem(B.items[middleIndexB++])
        //}

        while ( middleIndexB < B.items.size && C.totalWeight <= C.maxCapacity ){
            C.addItem(B.items[middleIndexB++])
        }

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
        }
    }

    fun identicalPercentage(): Int
    {
        val best = findBest()
        return 100*Collections.frequency(speciments,best)/maxSize
    }

}