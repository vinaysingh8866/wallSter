package com.jav1001.vinaysingh.wallster.ui

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jav1001.vinaysingh.wallster.R
import com.jav1001.vinaysingh.wallster.WallPaperApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream


class ListActivity : AppCompatActivity() {

    var name:String=""
    var anime:Boolean=false
    var genral:Boolean=false
    var people:Boolean=false
    fun loadData(adapter:WallpaperListAdapter){
        lifecycleScope.launch{
            val apiService = (application as WallPaperApplication).serviceLocator.apiService
            val response = try {
                var cat1 = if (anime)  "1" else "0"
                var cat2 = if (people)  "1" else "0"
                var cat3 = if (genral)  "1" else "0"
                apiService.getWallpaper(queryKey = name, categories = cat3+cat1+cat2)

            }catch (e: HttpException) {
                null
            } catch (e: IOException) {
                null
            } catch (e: Exception) {
                null
            }
            response?.let {
                adapter.wallpapers = it.results
                adapter.notifyDataSetChanged()
            }

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var url:String

        setContentView(R.layout.activity_list)
        val adapter = WallpaperListAdapter(OnWallpaperClickListner {
            val intent = Intent(this,  DetailsActivity::class.java).apply {

            }
            intent.putExtra("url", it.path);
            intent.putExtra("res", it.resolution);
            intent.putExtra("type",it.file_type);
            intent.putExtra("source",it.source)

            startActivity(intent)
        })





        val animeCheckBox = findViewById<CheckBox>(R.id.animeCheck)
        animeCheckBox.setOnClickListener{
            anime = animeCheckBox.isChecked
            loadData(adapter)
            Log.d("",anime.toString())
        }
        val peopleCheckBox = findViewById<CheckBox>(R.id.peopleCheck)
        peopleCheckBox.setOnClickListener{
            people = peopleCheckBox.isChecked
            loadData(adapter)
        }
        val genralCheckBox = findViewById<CheckBox>(R.id.genralCheck)
        genralCheckBox.setOnClickListener{
            people = genralCheckBox.isChecked
            loadData(adapter)
        }


        findViewById<RecyclerView>(R.id.recyclerViewWallpapers).also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }


        findViewById<Button>(R.id.buttonSearch).text = "Search"
        findViewById<Button>(R.id.buttonSearch).setOnClickListener {

            name = findViewById<EditText>(R.id.searchText).text.toString()
            loadData(adapter)

        }

        name = intent.getStringExtra("name").toString()
        if (name!=null){
        loadData(adapter)
        }
    }
}