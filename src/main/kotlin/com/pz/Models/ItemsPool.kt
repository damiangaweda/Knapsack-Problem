package com.pz.Models

class ItemsPool {

    private var pool = mutableListOf<Item>()

    fun addItem(item: Item)
    {
        if(!pool.contains(item))
        {
            pool.add(item)
        }
        else
        {
            println("item already in pool")
        }
    }

    fun removeItem(item: Item)
    {
        if(pool.contains(item))
        {
            pool.remove(item)
        }
        else
        {
            println("no such item in pool")
        }
    }

    fun getItemAt(index: Int): Item
    {
        return pool.get(index)
    }

    fun getPoolSize(): Int
    {
        return pool.size
    }

    fun printPool(){
        println("ItemsPool: $this")
        pool.forEach{it -> println("Weight: ${it.weight} Value: ${it.value}")}
    }
}