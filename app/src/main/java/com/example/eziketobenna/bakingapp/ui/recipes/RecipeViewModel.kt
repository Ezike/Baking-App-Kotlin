package com.example.eziketobenna.bakingapp.ui.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eziketobenna.bakingapp.data.RecipeRepository
import com.example.eziketobenna.bakingapp.data.model.Recipe
import javax.inject.Inject

class RecipeViewModel @Inject constructor(
    private val mRecipeRepository: RecipeRepository
) : ViewModel() {

    val _allRecipes: MutableLiveData<List<Recipe>> = MutableLiveData()
    val allRecipes: LiveData<List<Recipe>> by this::_allRecipes

    fun retryFetch() {
        mRecipeRepository.retryFetch()
    }
}
