package com.pz.Models

class Item constructor(var name: String, var weight: Double, var value: Double)
{
    override fun toString() : String
    {
        return "Name: $name Weight: $weight Value: $value"
    }
}