package com.pz.Services

import com.pz.Models.Knapsack
import com.pz.Models.Population

class GeneticAlgorithm {

    val history = mutableListOf<Knapsack>()
    val historySize = 10

    fun getAnswer(population: Population): Knapsack
    {
        //setting population to random
        population.setRandom()
        //finding best speciment
        var bestSubject = population.findBest()

        while (true){
            printHistory()

            /*
                Reassembling population
                    - rating
                    - crossover
                    - mutation
             */
            population.reassemblePopulation()

            //finding current best
            val currentBest = population.findBest()

            if(currentBest.adaptationScore > bestSubject.adaptationScore) {
                bestSubject = currentBest
            }

            addToHistory(bestSubject)

            if (!isChanging() && bestSubject.totalWeight <= bestSubject.maxCapacity) {
                return bestSubject
            }

        }

    }

    fun isChanging(): Boolean
    {

        if (history.size == 10) {
            val first = history.first()

            for (i in 0 until history.size) {
                if (history[i].equals(first))
                    continue
                else
                    return true
            }

            return false

        }
        return true
    }

    //TODO addToHistory nie utrzymuje 10 elementów w historii - solveed
    //TODO znaleźć przyczynę złego wyliczania score
    //TODO addToHistory nie przepisuje indeksów

    fun addToHistory(knapsack: Knapsack)
    {

        if(history.size < historySize)
        {
            history.add(knapsack)
        }
        else
        {
            val tmpHistory = mutableListOf<Knapsack>()
            for(i in history.size-1 downTo  1){
                tmpHistory.add(history[i-1])
                //println(" ups: ${history[i-1]} = ${history[i]}")
            }

            history.clear()
            history.addAll(tmpHistory)
            history.add(knapsack)
        }
    }

    fun printHistory()
    {
        println("history: \nsize: ${history.size}")
        history.forEach { e -> println(e) }
    }

}