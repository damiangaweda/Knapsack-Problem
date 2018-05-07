package com.pz.Models

import javafx.collections.ObservableArray

class ItemsPool {

    var weight: Double = 0.0
    var value: Double = 0.0
    var count: Int = 0

    private var pool = mutableListOf<Item>()

    fun addItem(item: Item)
    {
        pool.add(item)
        weight += item.weight
        value += item.value
        count++
    }

    fun addItems(items: MutableList<Item>)
    {
        items.forEach { e -> addItem(e) }
    }

    fun removeItem(item: Item)
    {
        if(pool.contains(item))
        {
            pool.remove(item)
            weight -= item.weight
            value -= item.value
            count--
        }
        else
        {
            println("no such item in pool")
        }
    }

    fun clearItems() {
        pool.clear()
        weight = 0.0
        value = 0.0
        count = 0
    }

    fun getItemAt(index: Int): Item
    {
        return pool.get(index)
    }

    fun setItemAt(index: Int, item: Item) {
        pool[index] = item
    }

    fun getPoolSize(): Int
    {
        return pool.size
    }

    fun printPool(){
        println("ItemsPool: $this")
        pool.forEach{it -> println("Weight: ${it.weight} Value: ${it.value}")}
    }

    fun setPool(itemlist: MutableList<Item>){
        this.pool = itemlist
    }

    fun getPool(): MutableList<Item>{
        return this.pool
    }
}