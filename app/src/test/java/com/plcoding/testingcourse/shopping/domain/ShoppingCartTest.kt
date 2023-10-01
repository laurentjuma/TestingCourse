package com.plcoding.testingcourse.shopping.domain

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ShoppingCartTest {

    private lateinit var shoppingCart: ShoppingCart
    @BeforeEach
    fun setUp() {
        shoppingCart = ShoppingCart()
    }

    @Test
    fun `Add multiple products, ensure total price is correct`() {
        // Given
        val product1 = Product(0, "PS5", 300.0)
        shoppingCart.addProduct(product1, 4)

        //Action
        val totalPrice = shoppingCart.getTotalCost()

        //Assert
        assertThat(totalPrice).isEqualTo(1200.0)
    }

    @Test
    fun `Negative quantity of products throws Exception`() {
        //Given
        val product1 = Product(0, "PS5", 300.0)

        //Action and Assert
        assertFailure { shoppingCart.addProduct(product1, -1) }
    }


}