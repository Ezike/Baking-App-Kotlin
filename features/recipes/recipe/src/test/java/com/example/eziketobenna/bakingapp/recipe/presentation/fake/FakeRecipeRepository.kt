package com.example.eziketobenna.bakingapp.recipe.presentation.fake

import com.example.eziketobenna.bakingapp.domain.model.Recipe
import com.example.eziketobenna.bakingapp.domain.repository.RecipeRepository
import com.example.eziketobenna.bakingapp.recipe.presentation.data.DummyData
import java.net.SocketTimeoutException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class FakeRecipeRepository : RecipeRepository {

    override fun fetchRecipes(): Flow<List<Recipe>> {
        return flowOf(
            listOf(
                DummyData.recipe,
                DummyData.recipe.copy(id = 2, steps = listOf(), image = "")
            )
        )
    }
}

class FakeRecipeRepositoryEmpty : RecipeRepository {

    override fun fetchRecipes(): Flow<List<Recipe>> {
        return flowOf(listOf())
    }
}

class FakeRecipeRepositoryError : RecipeRepository {

    companion object {
        const val ERROR_MSG: String = "No data"
    }

    override fun fetchRecipes(): Flow<List<Recipe>> {
        return flow { throw SocketTimeoutException(ERROR_MSG) }
    }
}

fun makeFakeRecipeRepository(type: RepoType): RecipeRepository {
    return when (type) {
        RepoType.DATA -> FakeRecipeRepository()
        RepoType.EMPTY -> FakeRecipeRepositoryEmpty()
        RepoType.ERROR -> FakeRecipeRepositoryError()
    }
}

enum class RepoType {
    DATA,
    EMPTY,
    ERROR
}
