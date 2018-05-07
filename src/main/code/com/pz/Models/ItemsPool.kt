package com.pz.Models

import javafx.collections.ObservableArray

class ItemsPool {

    private var pool = mutableListOf<Item>()

    fun addItem(item: Item)
    {
            pool.add(item)
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
        }
        else
        {
            println("no such item in pool")
        }
    }

    fun clearItems() {
        pool.clear()
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