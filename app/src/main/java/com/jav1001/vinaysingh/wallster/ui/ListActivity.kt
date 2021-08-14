package com.jav1001.vinaysingh.wallster.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jav1001.vinaysingh.wallster.R
import com.jav1001.vinaysingh.wallster.WallPaperApplication
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val adapter = WallpaperListAdapter(OnWallpaperClickListner {
        })


        findViewById<RecyclerView>(R.id.recyclerViewWallpapers).also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }

        lifecycleScope.launch{
            val apiService = (application as WallPaperApplication).serviceLocator.apiService
            val response = try {
                apiService.getWallpaper(queryKey = "Mountain")

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