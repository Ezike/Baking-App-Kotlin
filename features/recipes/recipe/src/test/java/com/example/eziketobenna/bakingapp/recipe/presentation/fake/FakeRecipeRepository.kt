package com.example.eziketobenna.bakingapp.recipe.presentation.fake

import com.example.eziketobenna.bakingapp.domain.model.Recipe
import com.example.eziketobenna.bakingapp.domain.repository.RecipeRepository
import com.example.eziketobenna.bakingapp.recipe.presentation.data.DummyData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.net.SocketTimeoutException

internal class FakeRecipeRepository : RecipeRepository {

    companion object {
        const val ERROR_MSG: String = "No network"
    }

    private var recipesFlow: Flow<List<Recipe>> = flowOf(listOf(DummyData.recipe))

    override fun fetchRecipes(): Flow<List<Recipe>> {
        return recipesFlow
    }

    var responseType: ResponseType = ResponseType.DATA
        set(value) {
            field = value
            recipesFlow = makeResponse(value)
        }

    private fun makeResponse(type: ResponseType): Flow<List<Recipe>> {
        return when (type) {
            ResponseType.DATA -> flowOf(listOf(DummyData.recipe))
            ResponseType.EMPTY -> flowOf(listOf())
            ResponseType.ERROR -> flow { throw SocketTimeoutException(ERROR_MSG) }
        }
    }
}

internal enum class ResponseType {
    DATA,
    EMPTY,
    ERROR
}
