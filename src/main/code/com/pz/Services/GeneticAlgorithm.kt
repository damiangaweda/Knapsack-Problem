package com.pz.Services

import com.pz.Models.Knapsack
import com.pz.Models.Population

public class GeneticAlgorithm {


    fun getAnswer(population: Population, samePercent: Int): Knapsack
    {
        //setting population to random
        population.setRandom()
        //finding best speciment
        var best = population.findBest()
        var currentBest: Knapsack

        var notChangingCounter = 0

        while (true){

            //population.printPopulation()
            /*
                Reassembling population
                    - rating
                    - crossover
                    - mutation
             */
            population.reassemblePopulation()

            //finding current best


            currentBest = population.findBest()
            /*
            if(currentBest.adaptationScore > best.adaptationScore && currentBest.totalWeight <= currentBest.maxCapacity) {

                best.set(currentBest)
                notChangingCounter = 0
            }
            else {
                notChangingCounter++
            }

            if(notChangingCounter <= 20)
                continue
            else {
                return best
            }
            */

            if(currentBest.adaptationScore > best.adaptationScore && currentBest.totalWeight <= currentBest.maxCapacity) {
                best.set(currentBest)
            }

            if(population.identicalPercentage() >= samePercent)
                return best

        }

    }



}