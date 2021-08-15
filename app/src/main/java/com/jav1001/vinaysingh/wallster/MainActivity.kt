package com.jav1001.vinaysingh.wallster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.*;
import androidx.activity.*;
import com.jav1001.vinaysingh.wallster.Main.MainViewModel
import com.jav1001.vinaysingh.wallster.Main.MainViewModelFactory

import com.jav1001.vinaysingh.wallster.data.MainRepository
import com.jav1001.vinaysingh.wallster.data.database.WallpaperDatabase
import com.jav1001.vinaysingh.wallster.databinding.ActivityMainBinding
import com.jav1001.vinaysingh.wallster.ui.FavoriteActivity
import com.jav1001.vinaysingh.wallster.ui.ListActivity

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        val apiService = (application as WallPaperApplication).serviceLocator.apiService
        val database =(application as WallPaperApplication).serviceLocator.WallpaperDatabase
        val repository = MainRepository(apiService,database)
        MainViewModelFactory(repository)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.wallPapers.observe(this){

            Log.d("test12",viewModel.wallPapers.value.toString())
        }

        binding.buttonPenguin.setOnClickListener {
            val intent = Intent(this,  ListActivity::class.java).apply {

            }
            intent.putExtra("name", "Penguin");
            startActivity(intent)
        }


        binding.buttonCats.setOnClickListener {
            val intent = Intent(this,  ListActivity::class.java).apply {

            }
            intent.putExtra("name", "bird");
            startActivity(intent)
        }

        binding.buttonDog.setOnClickListener {
            val intent = Intent(this,  ListActivity::class.java).apply {

            }
            intent.putExtra("name", "Dog");
            startActivity(intent)
        }
        binding.buttonAnime.setOnClickListener {
            val intent = Intent(this,  ListActivity::class.java).apply {

            }
            intent.putExtra("name", "Anime");
            startActivity(intent)
        }

        binding.buttonBlack.setOnClickListener {
            val intent = Intent(this,  ListActivity::class.java).apply {

            }
            intent.putExtra("name", "black");
            startActivity(intent)
        }

        binding.buttonBlue.setOnClickListener {
            val intent = Intent(this,  ListActivity::class.java).apply {

            }
            intent.putExtra("name", "blue");
            startActivity(intent)
        }
        binding.buttonRed.setOnClickListener {
            val intent = Intent(this,  ListActivity::class.java).apply {

            }
            intent.putExtra("name", "red");
            startActivity(intent)
        }
        binding.buttonYellow.setOnClickListener {
            val intent = Intent(this,  ListActivity::class.java).apply {

            }
            intent.putExtra("name", "yellow");
            startActivity(intent)
        }
        binding.buttonNature.setOnClickListener {
            val intent = Intent(this,  ListActivity::class.java).apply {

            }
            intent.putExtra("name", "nature");
            startActivity(intent)
        }

        binding.buttonPurple.setOnClickListener {
            val intent = Intent(this,  ListActivity::class.java).apply {

            }
            intent.putExtra("name", "purple");
            startActivity(intent)
        }

        binding.buttonCars.setOnClickListener {
            val intent = Intent(this,  ListActivity::class.java).apply {

            }
            intent.putExtra("name", "cars");
            startActivity(intent)
        }

        binding.buttonCats.setOnClickListener {
            val intent = Intent(this,  ListActivity::class.java).apply {

            }
            intent.putExtra("name", "cats");
            startActivity(intent)
        }

        binding.flower.setOnClickListener {
            val intent = Intent(this,  ListActivity::class.java).apply {

            }
            intent.putExtra("name", "flower");
            startActivity(intent)
        }

        binding.buttonLions.setOnClickListener {
            val intent = Intent(this,  ListActivity::class.java).apply {

            }
            intent.putExtra("name", "Lion");
            startActivity(intent)
        }



        binding.favoriteButton.setOnClickListener {
            val intent = Intent(this,  FavoriteActivity::class.java).apply {

            }
            startActivity(intent)
        }






    }
}