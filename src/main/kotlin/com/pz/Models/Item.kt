package com.pz.Models

class Item constructor(var weight: Double, var value: Double)
{
    override fun toString() : String
    {
        return "Weight: $weight Value: $value"
    }
}