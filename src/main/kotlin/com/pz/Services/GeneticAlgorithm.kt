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

        while (true){
            /*
                Reassembling population
                    - rating
                    - crossover
                    - mutation
             */
            population.reassemblePopulation()

            //finding current best
            val currentBest = population.findBest()

           if (population.identicalPercentage() > 70)
                return currentBest
        }

    }



}