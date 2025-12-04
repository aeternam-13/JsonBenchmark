package com.aeternam.jsonbenchmark.domain.model.dummyclasses


/**
 * The value to optimize value will be "target"
 * */
data class DummyClass(
    val foo1: String,
    val foo2: String,
    val foo3: String,
    val foo4: String,
    val foo5: String,
    val foo6: String,
    //This is var to allow reassign
    var target: String,
)