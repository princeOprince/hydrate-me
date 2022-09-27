package com.princeoprince.hydrateme.model

class IntakeEntry(quantity: Int) {
    val timeStamp: Long = System.currentTimeMillis()
    val intake = quantity
}
