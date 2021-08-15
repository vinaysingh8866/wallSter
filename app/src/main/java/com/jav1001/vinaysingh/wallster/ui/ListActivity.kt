package com.jav1001.vinaysingh.wallster.ui

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jav1001.vinaysingh.wallster.R
import com.jav1001.vinaysingh.wallster.WallPaperApplication
import com.jav1001.vinaysingh.wallster.databinding.ActivityFavoriteBinding
import com.jav1001.vinaysingh.wallster.databinding.ActivityListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream


class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url:String
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






        findViewById<RecyclerView>(R.id.recyclerViewWallpapers).also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }


        findViewById<Button>(R.id.buttonSearch).setOnClickListener {

            lifecycleScope.launch{
                val apiService = (application as WallPaperApplication).serviceLocator.apiService
                val response = try {
                    apiService.getWallpaper(queryKey = findViewById<TextView>(R.id.searchText).text.toString())

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
        val name = intent.getStringExtra("name")
        if (name!=null){
        lifecycleScope.launch{
            val apiService = (application as WallPaperApplication).serviceLocator.apiService
            val response = try {
                apiService.getWallpaper(queryKey = intent.getStringExtra("name").toString())

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
    }
}