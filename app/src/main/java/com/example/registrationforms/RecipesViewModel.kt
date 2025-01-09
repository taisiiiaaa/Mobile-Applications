package com.example.registrationforms

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class RecipesViewModel: ViewModel() {
    private val recipesFlow = MutableStateFlow<List<Recipe>>(emptyList())
    private val queryFlow = MutableStateFlow("")

    val filteredRecipes: StateFlow<List<Recipe>> = queryFlow
        .map { query ->
            if (query.length >= 3) {
                recipesFlow.value.filter {
                    it.recipeName.contains(query, ignoreCase = true) ||
                            it.recipeInfo.contains(query, ignoreCase = true)
                }
            } else {
                recipesFlow.value
            }
        }
        .stateIn(
            CoroutineScope(Dispatchers.Default), // CoroutineScope for collecting Flow
            kotlinx.coroutines.flow.SharingStarted.Lazily,
            emptyList() // Initial state
        )

    fun setQuery(query: String) {
        Log.d("QUERY", "New query value: $query")
        queryFlow.value = query
    }

    fun initializeRecipes(recipes: List<Recipe>) {
        recipesFlow.value = recipes
    }
}

