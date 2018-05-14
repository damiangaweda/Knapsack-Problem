package com.pz.Services

import com.pz.Models.Knapsack
import com.pz.Models.Population

public class GeneticAlgorithm {


    /**
     *  <p> Main loop of algorithm </p>
     *  <p> Sets random population and loops until we reach stop criterion </p>
     *  @param population Population object
     *  @param samePercent target identical percentage (stop criterion)
     *  @return best found Knapsack
     */
    fun getAnswer(population: Population, samePercent: Int): Knapsack
    {
        population.setRandom()
        val best = population.findBest()
        var currentBest: Knapsack

        while (true){

            population.reassemblePopulation()

            currentBest = population.findBest()


            if(currentBest.adaptationScore > best.adaptationScore && currentBest.totalWeight <= currentBest.maxCapacity) {
                best.set(currentBest)
            }

            if(population.identicalPercentage() >= samePercent)
                return best

        }

    }



}