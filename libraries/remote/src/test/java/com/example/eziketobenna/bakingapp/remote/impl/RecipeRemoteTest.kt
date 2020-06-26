package com.example.eziketobenna.bakingapp.remote.impl

import com.example.eziketobenna.bakingapp.data.contract.RecipeRemote
import com.example.eziketobenna.bakingapp.data.model.RecipeEntity
import com.example.eziketobenna.bakingapp.remote.mapper.IngredientRemoteMapper
import com.example.eziketobenna.bakingapp.remote.mapper.RecipeRemoteMapper
import com.example.eziketobenna.bakingapp.remote.mapper.StepRemoteMapper
import com.example.eziketobenna.bakingapp.remote.utils.REQUEST_PATH
import com.example.eziketobenna.bakingapp.remote.utils.RecipeRequestDispatcher
import com.example.eziketobenna.bakingapp.remote.utils.makeTestApiService
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class RecipeRemoteTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var recipeRemote: RecipeRemote
    private val recipeMapper = RecipeRemoteMapper(IngredientRemoteMapper(), StepRemoteMapper())

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = RecipeRequestDispatcher()
        mockWebServer.start()
        recipeRemote = RecipeRemoteImpl(makeTestApiService(mockWebServer), recipeMapper)
    }

    @Test
    fun `check that calling fetchRecipes returns recipe list`() = runBlocking {
        val recipes: List<RecipeEntity> = recipeRemote.fetchRecipes()
        assertThat(recipes).isNotEmpty()
    }

    @Test
    fun `check that calling fetchRecipes makes request to given path`() = runBlocking {
        recipeRemote.fetchRecipes()
        assertThat(REQUEST_PATH).isEqualTo(mockWebServer.takeRequest().path)
    }

    @Test
    fun `check that calling fetchRecipes makes a GET request`() = runBlocking {
        recipeRemote.fetchRecipes()
        assertThat("GET $REQUEST_PATH HTTP/1.1").isEqualTo(mockWebServer.takeRequest().requestLine)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
