package plugins

import com.example.models.api.SignInRequest
import com.example.models.api.SignUpRequest
import database.dao.category.daoCategory
import database.dao.product.daoProduct
import database.dao.user.daoUser
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import models.Category
import models.Product

fun Application.configureRouting() {

    routing {

        /* ----- Categories ----- */
        get("/category/{id}") {
            val id = call.parameters.getOrFail<Int>("id")
            call.respond(mapOf("category" to daoCategory.getCategory(id)))
        }

        get("/categories") {
            call.respond(mapOf("categories" to daoCategory.getAllCategories()))
        }

        post("/category") {
            val category = call.receive<Category>()
            val name = category.name
            val description = category.description
            val imgUrl = category.imgUrl
            val data = daoCategory.addCategory(name, description, imgUrl)
            call.respond("Category ${data.toString()} added successfully")
        }

        put("/category") {
            val category = call.receive<Category>()
            daoCategory.updateCategory(
                category.id,
                category.name,
                category.description,
                category.imgUrl
            )
            call.respond("Category $category updated successfully")

        }

        delete("/category/{id}") {
            val id = call.parameters.getOrFail<Int>("id")
            val category = daoCategory.getCategory(id)
            daoCategory.deleteCategory(id)
            call.respond("Category $category deleted successfully")
        }

        /* ----- Products ----- */
        get("/products") {
            call.respond(mapOf("products" to daoProduct.getAllProducts()))
        }

        get("/product/{id}") {
            val id = call.parameters.getOrFail<Int>("id")
            call.respond(mapOf("product" to daoProduct.getProduct(id)))
        }

        post("/product") {
            val product = call.receive<Product>()
            val name = product.name
            val description = product.description
            val price = product.price
            val categoryId = product.categoryId
            val data = daoProduct.addProduct(name, description, price, categoryId)
            call.respond("Product $data added successfully")
        }

        put("/product") {
            val product = call.receive<Product>()
            daoProduct.updateProduct(
                id = product.id,
                name = product.name,
                description = product.description,
                price = product.price,
                categoryId = product.categoryId
            )
            call.respond("Product $product updated successfully")

        }

        delete("/product/{id}") {
            val id = call.parameters.getOrFail<Int>("id")
            val product = daoProduct.getProduct(id)
            daoProduct.deleteProduct(id)
            call.respond("Product $product deleted successfully")
        }

        /* ----- Auth ----- */
        get("/users") {
            val users = daoUser.getAllUsers()
            call.respond(mapOf("users" to users))
        }

        post("/signIn") {
            val request = call.receive<SignInRequest>()
            val user = daoUser.signIn(request.login, request.password)

            if (user == null) {
                call.respondText(
                    text = "User not exists",
                    contentType = ContentType.Application.Json,
                    status = HttpStatusCode.Unauthorized
                )
                return@post
            }

            call.respond(mapOf("user" to user))
        }

        post("/signUp") {
            val request = call.receive<SignUpRequest>()
            println(request)
            val user = daoUser.getUserByLogin(request.login)
            if (user != null) {
                call.respondText(
                    text = "User already exists",
                    contentType = ContentType.Application.Json,
                    status = HttpStatusCode.NotAcceptable
                )
                return@post
            }

            val registeredUser = daoUser.signUp(
                request.login,
                request.password,
                request.token,
                request.tokenProvider,
            )

            if (registeredUser != null) {
                call.respond(mapOf("user" to registeredUser))
            } else {
                call.respondText(
                    text = "User not exists",
                    contentType = ContentType.Application.Json,
                    status = HttpStatusCode.BadRequest
                )
                return@post
            }
        }
    }
}

