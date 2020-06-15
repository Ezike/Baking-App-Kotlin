package com.example.eziketobenna.bakingapp.domain.usecase

import com.example.eziketobenna.bakingapp.domain.executor.PostExecutionThread
import com.example.eziketobenna.bakingapp.domain.model.Recipe
import com.example.eziketobenna.bakingapp.domain.repository.RecipeRepository
import com.example.eziketobenna.bakingapp.domain.usecase.base.FlowUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class FetchRecipes @Inject constructor(
    private val recipeRepository: RecipeRepository,
    postExecutionThread: PostExecutionThread
) : FlowUseCase<Unit, List<Recipe>>(postExecutionThread) {

    override fun execute(params: Unit?): Flow<List<Recipe>> {
        return recipeRepository.fetchRecipe()
    }
}
