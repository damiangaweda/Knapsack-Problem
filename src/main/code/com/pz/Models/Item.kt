package com.pz.Models

/**
 * <p> Represents a single feature of a specimen </p>
 * <p> Each Item consists of it's own name, weight and value </p>
 * @property name name of item
 * @property weight weight of item
 * @property value value of item
 */
class Item constructor(var name: String, var weight: Double, var value: Double)
{
    override fun toString() : String
    {
        return "Name: $name Weight: $weight Value: $value"
    }
}