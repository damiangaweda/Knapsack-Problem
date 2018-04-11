package com.pz.Services

import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.pz.Models.Item
import com.pz.Models.ItemsPool
import java.io.File
import java.io.FileReader
import java.io.BufferedReader



class ItemsPoolService {

    fun saveToFile(itemsPool: ItemsPool) {
        val gsonObj = Gson().toJson(itemsPool.getPool())

        File("itemsPool.json").printWriter().use { out -> out.println(gsonObj) }

        /** TEST  */
        println(gsonObj)
    }

    fun readFromFile(itemsPool: ItemsPool){
        val path = "itemsPool.json"
        val bufferedReader = BufferedReader(FileReader(path))

        val gson = Gson()
        val json: MutableList<Item> = gson.fromJson(bufferedReader)

        itemsPool.setPool(json)

        /** TEST  */
        itemsPool.printPool()
    }
}