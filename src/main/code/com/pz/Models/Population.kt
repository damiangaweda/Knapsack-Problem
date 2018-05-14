package com.pz.Models

import java.util.*

/**
 * <p> Represents the population </p>
 * <p> Consists of MutableList of Knapsacks </p>
 * @property maxSize maximal size of the population / number of specimens
 * @property knapsackMaxWeight maximal weight of single Knapsack
 * @property mutationChance chance of mutation in %
 * @property itemsPool pool of available items
 */
class Population constructor(private var maxSize: Int, var knapsackMaxWeight: Double, val mutationChance: Int , val itemsPool: ItemsPool ){

    private var specimens = mutableListOf<Knapsack>()

    /**
     * Adds Knapsack to specimens list
     * @param knapsack knapsack to add
     */
    fun add(knapsack: Knapsack){
        if(specimens.size < maxSize)
        {
            specimens.add(knapsack)
        }
        else
        {
            println("no more space :)")
        }
    }

    /**
     * Removes Knapsack from specimens list
     * @param knapsack knapsack to remove
     */
    fun remove(knapsack: Knapsack){
        if(specimens.contains(knapsack))
        {
            specimens.remove(knapsack)
        }
        else
        {
            println("no such speciment")
        }
    }

    /**
     * Implements function to get random index in list with given bounds
     */
    fun ClosedRange<Int>.random() =
            Random().nextInt(endInclusive - start) +  start

    fun setRandom(){
        for(i in 0 until maxSize){
            add(Knapsack(knapsackMaxWeight, itemsPool))
        }
    }

    fun printPopulation(){
        println("\n Population: $this")
        specimens.forEach { it -> println(it.toString()) }
    }

    /**
     * Returns size of population
     * @return size of population
     */
    fun getSize(): Int
    {
        return specimens.size
    }

    /**
     * Sorts specimens list with ascending order
     */
    fun sort(){
        specimens.sortBy { e -> e.adaptationScore }
    }

    /**
     * Sums total score of this population
     */
    fun sumScore(): Double{
        return specimens.sumByDouble { e -> e.adaptationScore }
    }

    /**
     * <p> This function is used to scale total adaptationScore: Double to be represented as 100% </p>
     * <p> With this kind of approach it is easier to maintain <strong>Mutation</strong> </p>
     */
    fun scalePointsTo100(){
        val totalScore = sumScore()
        specimens.forEach { e -> e.adaptationScore = (e.adaptationScore/totalScore * 100).toInt().toDouble()}

    }

    /**
     * <p> Mixes features of two given Knapsacks </p>
     * <p> We draw pivot ('middle' index) for each Knapsack with given bounds starting from 0 to size.
     *      Next we create C: Knapsack and fill it with items from A: Knapsack until we reack it's pivot.
     *      Lastly C: Knapsack is filled with items from B: Knapsack for as long as it still has free space for items. </p>
     * @param A first Knapsack
     * @param B second Knapsack
     * @return Knapsack C with mixed features of A and B
     */
    fun crossover(A: Knapsack, B: Knapsack): Knapsack
    {
        val middleIndexA: Int = (0..A.items.size).random()
        var middleIndexB: Int = (0..B.items.size).random()

        val C = Knapsack(A.maxCapacity)

        for(i in 0 until middleIndexA)
            C.addItem(A.items[i])

        while ( middleIndexB < B.items.size && (C.totalWeight + B.items[middleIndexB].weight) <= C.maxCapacity ){
           C.addItem(B.items[middleIndexB++])
        }

        return  C
    }

    /**
     * Returns best specimen in population
     * @return best specimen
     */
    fun findBest(): Knapsack
    {
        var best = specimens.first()
        for(i in 0 until specimens.size){
            if(specimens[i].adaptationScore > best.adaptationScore)
                best = specimens[i]
        }

        return best
    }

    /**
     * Changes one feature of selected subject with item from this.itemPool at set chance
     * @param subject knapsack to mutate
     */
    fun mutate(subject: Knapsack): Knapsack
    {
        if((0..100).random() <= mutationChance){
            subject.items[(0..subject.items.size).random()] = itemsPool.getItemAt((0..itemsPool.getPoolSize()).random())
        }

        return subject
    }

    /**
     * <p> 1. Selects best specimens </p>
     * <p> 2. Invoke crossover method to create new specimens </p>
     * <p> 3. Invoke mutate method at new specimens </p>
     */
    fun reassemblePopulation(){
        scalePointsTo100()
        val specimentsPool = mutableListOf<Knapsack>()

        for (i in 0 until specimens.size){
            if(specimens[i].adaptationScore != 0.0)
                for (j in 0 until specimens[i].adaptationScore.toInt()){
                    specimentsPool.add(specimens[i])
                }
        }

        specimens.clear()


        for (i in 0 until maxSize){
            var subject = crossover(specimentsPool[(0..specimentsPool.size).random()],specimentsPool[(0..specimentsPool.size).random()])
            subject = mutate(subject)
            specimens.add(subject)
            specimens[i].rate()
        }
    }

    /**
     * Calculates percentage of identical specimens in population
     * @return percentage of identical speciments
     */
    fun identicalPercentage(): Int
    {
        val best = findBest()
        return 100*Collections.frequency(specimens,best)/maxSize
    }

}