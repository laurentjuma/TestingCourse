package com.plcoding.testingcourse.shopping.domain

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

internal class ShoppingCartTest {

    private lateinit var shoppingCart: ShoppingCart
    @BeforeEach
    fun setUp() {
        shoppingCart = ShoppingCart()
    }

    @ParameterizedTest
    @CsvSource(
        "1, 300.0",
        "2, 600.0",
        "3, 900.0",
        "4, 1200.0",
        "5, 1500.0",
        "6, 1800.0",
        "7, 2100.0",
        "8, 2400.0",
        "9, 2700.0",
        "10, 3000.0",
    )
    fun `Add multiple products, ensure total price is correct`(quantity: Int, expectedPrice: Double) {
        // Given
        val product1 = Product(0, "PS5", 300.0)
        shoppingCart.addProduct(product1, quantity)

        //Action
        val totalPrice = shoppingCart.getTotalCost()

        //Assert
        assertThat(totalPrice).isEqualTo(expectedPrice)
    }

    @RepeatedTest(10)
    fun `Negative quantity of products throws Exception`() {
        //Given
        val product1 = Product(0, "PS5", 300.0)

        //Action and Assert
        assertFailure { shoppingCart.addProduct(product1, -1) }
    }


}