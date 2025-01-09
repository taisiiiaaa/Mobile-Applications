package com.example.registrationforms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Button
import android.widget.Toast
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels

data class Recipe(
    val imageId: Int,
    val recipeName: String,
    val recipeInfo: String
)

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: RecipesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageId = intArrayOf(
            R.drawable.dish_1,
            R.drawable.dish_2,
            R.drawable.dish_3,
            R.drawable.dish_4,
            R.drawable.dish_5,
            R.drawable.dish_6,
            R.drawable.dish_7
        )

        val recipeName = arrayOf(
            getString(R.string.recipe_black_karaage),
            getString(R.string.recipe_seafood_udon),
            getString(R.string.recipe_tonkotsu_ramen),
            getString(R.string.recipe_takoyaki),
            getString(R.string.recipe_tempura),
            getString(R.string.recipe_yakitori_shrimp),
            getString(R.string.recipe_onigiri_bento)
        )

        val recipeInfo = arrayOf(
            getString(R.string.desc_black_karaage),
            getString(R.string.desc_seafood_udon),
            getString(R.string.desc_tonkotsu_ramen),
            getString(R.string.desc_takoyaki),
            getString(R.string.desc_tempura),
            getString(R.string.desc_yakitori_shrimp),
            getString(R.string.desc_onigiri_bento)
        )

        val recipes = List(7) { index ->
            Recipe(
                imageId[index],
                recipeName[index],
                recipeInfo[index]
            )
        }

        val recipesRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recipesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = RecipeAdapter(emptyList())
        recipesRecyclerView.adapter = adapter

        viewModel.initializeRecipes(recipes)

        val searchBar = view.findViewById<TextInputEditText>(R.id.search)

        searchBar.addTextChangedListener {
            val query = it.toString()
            viewModel.setQuery(query)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.filteredRecipes.collect { filteredRecipes ->
                    adapter.updateRecipes(filteredRecipes)
                }
            }
        }
    }

    class RecipeAdapter(private var recipes: List<Recipe>) :
        RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val recipeImage: ImageView = itemView.findViewById(R.id.dish_image)
            val recipeName: TextView = itemView.findViewById(R.id.recipe_name)
            val recipeInfo: TextView = itemView.findViewById(R.id.recipe_description)
            val shareButton: Button = itemView.findViewById(R.id.share_button)
            val likeButton: Button = itemView.findViewById(R.id.favorite_button)
        }

        fun updateRecipes(newRecipes: List<Recipe>) {
            recipes = newRecipes
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
            return ViewHolder(itemView)
        }

        override fun getItemCount(): Int = recipes.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val recipe = recipes[position]
            holder.recipeName.text = recipe.recipeName
            holder.recipeInfo.text = recipe.recipeInfo
            holder.recipeImage.setImageResource(recipe.imageId)

            var isLiked = false

            holder.itemView.setOnClickListener {
                Toast.makeText(
                    holder.itemView.context,
                    "Clicked: ${recipe.recipeName}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            holder.shareButton.setOnClickListener {
                Toast.makeText(
                    holder.itemView.context,
                    "Share with: ${recipe.recipeName}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            holder.likeButton.setOnClickListener {
                isLiked = !isLiked
                if (isLiked) {
                    holder.likeButton.setBackgroundResource(R.drawable.ic_favorite_red) // Change to red heart
                    Toast.makeText(
                        holder.itemView.context,
                        "Added to Favorites: ${recipe.recipeName}",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    holder.likeButton.setBackgroundResource(R.drawable.ic_favorite) // Change back to default heart
                    Toast.makeText(
                        holder.itemView.context,
                        "Removed from Favorites: ${recipe.recipeName}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }
}
