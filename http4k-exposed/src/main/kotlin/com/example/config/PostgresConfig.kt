package com.example.config

import com.example.models.data.Offerings
import com.example.models.data.Rating
import com.example.models.data.Ratings
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

const val HIKARI_CONFIG_KEY = "ktor.hikariconfig"

fun initDB() {
//        val configPath = environment.config.property(HIKARI_CONFIG_KEY).getString()
    val dbConfig = HikariConfig("src/main/resources/dbconfig.properties")
    val dataSource = HikariDataSource(dbConfig)
    Database.connect(dataSource)
    createTables()
}

private fun createTables() = transaction {
    SchemaUtils.create(
        Offerings,
        Ratings
    )
}