package com.pz.Models

/**
 * <p> Class containing a MutableList with all items available to use with genetic algorithm </p>
 * <p> Items from pool are added to a Knapsack and are representing a set of all available features </p>
 * <p> Each pool has it's own weight, value and count </p>
 */
class ItemsPool {

    var weight: Double = 0.0
    var value: Double = 0.0
    var count: Int = 0

    private var pool = mutableListOf<Item>()

    /**
     * Adds single item to pool and increments object fields with given data
     * @param item item to add
     */
    fun addItem(item: Item)
    {
        pool.add(item)
        weight += item.weight
        value += item.value
        count++
    }

    /**
     * Adds multiple items to pool
     */
    fun addItems(items: MutableList<Item>)
    {
        items.forEach { e -> addItem(e) }
    }

    /**
     * Removes item and decrements object fields
     * @param item item to remove
     */
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

    /**
     * Clears pool and sets all fields to starting value
     */
    fun clearItems() {
        pool.clear()
        weight = 0.0
        value = 0.0
        count = 0
    }

    /**
     * Returns item
     * @param index
     */
    fun getItemAt(index: Int): Item
    {
        return pool.get(index)
    }

    /**
     * Sets item in pool at specific index
     * @param index pool index
     * @param item item to be set at given index
     */
    fun setItemAt(index: Int, item: Item) {
        pool[index] = item
    }

    /**
     * Returns size of pool
     * @return size of pool
     */
    fun getPoolSize(): Int
    {
        return pool.size
    }

    fun printPool(){
        println("ItemsPool: $this")
        pool.forEach{it -> println("Weight: ${it.weight} Value: ${it.value}")}
    }

    /**
     * Sets this pool list to given item list
     * @param itemlist list of items to be set
     */
    fun setPool(itemlist: MutableList<Item>){
        this.pool = itemlist
    }

    /**
     * Returns this pool of items
     * @return item list
     */
    fun getPool(): MutableList<Item>{
        return this.pool
    }
}