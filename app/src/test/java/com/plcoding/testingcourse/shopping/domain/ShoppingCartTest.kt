package com.plcoding.testingcourse.shopping.domain

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class ShoppingCartTest {

    private lateinit var shoppingCart: ShoppingCart
    @BeforeEach
    fun setUp() {
        shoppingCart = ShoppingCart()
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5])
    fun `Add multiple products, ensure total price is correct`(quantity: Int) {
        // Given
        val product1 = Product(0, "PS5", 300.0)
        shoppingCart.addProduct(product1, quantity)

        //Action
        val totalPrice = shoppingCart.getTotalCost()

        //Assert
        assertThat(totalPrice).isEqualTo(quantity * product1.price)
    }

    @RepeatedTest(10)
    fun `Negative quantity of products throws Exception`() {
        //Given
        val product1 = Product(0, "PS5", 300.0)

        //Action and Assert
        assertFailure { shoppingCart.addProduct(product1, -1) }
    }


}