package ru.netology

val commissionRate = 0.0075
val minimumCommission = 35
val maestroCommissionRate = 0.006
val startCommission = 75_000
val limitPerDay = 150_000
val limitPerMonth = 600_000
val limitVKPayPerAmount = 15_000
val limitVKPayPerMonth = 40_000

fun main() {
    commission("Visa", 400_000, 20_000)
}

fun commission(cardType: String = "VK Pay", previousTransfers: Int = 0, amount: Int) =
    when (cardType) {
        "Visa", "Мир", "Mastercard", "Maestro" ->
            when {
                amount > limitPerDay -> println("Превышен лимит переводов в сутки")
                ((amount + previousTransfers) > limitPerMonth) -> println("Превышен лимит переводов в этом месяце")
                else -> {
                    when (cardType) {
                        "Visa", "Мир" -> {
                            if ((amount * commissionRate).toInt() >= minimumCommission) {
                                val commission = (amount * commissionRate).toInt()
                                println("Комиссия $commission р.")
                            } else println("Комиссия $minimumCommission р.")
                        }

                        else -> {
                            if ((amount + previousTransfers) > startCommission) {
                                val commission = (amount * maestroCommissionRate).toInt() + 20
                                println("Комиссия $commission р.")
                            } else println("Комиссия 0 р.")
                        }
                    }
                }
            }

        else -> {
            when {
                amount > limitVKPayPerAmount -> println("Превышен лимит переводов в сутки")
                ((amount + previousTransfers) > limitVKPayPerMonth) -> println("Превышен лимит переводов в этом месяце")
                else -> println("Комиссия по карте VK Pay не взимается")
            }
        }
    }