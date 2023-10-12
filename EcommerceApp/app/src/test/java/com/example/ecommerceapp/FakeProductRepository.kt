package com.example.ecommerceapp

class FakeProductRepository : ProductRepositoryImplement() {

    private var fakeProducts: List<Product>? = null
    private var failure: Boolean = false

    fun setProducts(products: List<Product>?) {
        fakeProducts = products
    }

    fun setFailure(failure: Boolean) {
        this.failure =failure
    }

    override suspend fun fetchProducts(): Result<List<Product>> {
        return if (failure) {
            Result.failure(Exception("Simulated failure"))
        } else if (fakeProducts != null) {
            Result.success(fakeProducts!!)
        } else {
            Result.failure(Exception("Fake products not set"))
        }
    }

    override suspend fun fetchProduct(id: Int): Result<Product> {
        return if (failure) {
            Result.failure(Exception("Simulated failure"))
        } else if (fakeProducts != null) {
            val product = fakeProducts?.find { it.id == id }
            if (product != null) {
                Result.success(product)
            } else {
                Result.failure(Exception("Product not found with ID: $id"))
            }
        } else {
            Result.failure(Exception("Fake products not set"))
        }
    }
}
