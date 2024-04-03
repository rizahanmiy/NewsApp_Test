package com.rizahanmiy.newsapp.presentation.ui.splash

import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.rizahanmiy.newsapp.R
import com.rizahanmiy.newsapp.data.base.BaseActivity
import com.rizahanmiy.newsapp.data.entities.NewsArticlesApi
import com.rizahanmiy.newsapp.data.entities.NewsSourceApi
import com.rizahanmiy.newsapp.domain.common.ResultState
import com.rizahanmiy.newsapp.presentation.ui.main.MainActivity
import com.rizahanmiy.newsapp.presentation.viewmodel.NewsViewModel
import com.rizahanmiy.newsapp.presentation.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

class SplashScreen : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var handler: Handler

    override val layout: Int = R.layout.activity_splash

    override fun onPreparation() {
        AndroidInjection.inject(this)
        newsViewModel = ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]

        handler = Handler()
        handler.postDelayed({
            MainActivity.start(this)
            finish()
        }, 3000)
    }

    override fun onIntent() {

    }

    override fun onUi() {

    }

    override fun onAction() {

    }

    override fun onObserver() {
/*        userViewModel.fetchArticle(page = 1, pageSize = 20, country = "us")
        observe(userViewModel.fetchArticle(
            page = 1,
            pageSize = 10,
            country = "us"
        )){
            manageArticle(it)
        }*/

/*        observe(newsViewModel.fetchSource(
            page = 1,
            pageSize = 10,
            country = "us"
        )){
            manageSource(it)
        }*/
    }
    private fun manageArticle(result: ResultState<MutableList<NewsArticlesApi>>){
        when(result){
            is ResultState.Error -> {
                Toast.makeText(this, "Error ${result.throwable}", Toast.LENGTH_SHORT).show()
                Log.d("Articles", "Error ${result.throwable}")
            }
            is ResultState.Failed -> {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
            is ResultState.Loading -> {
                Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
            }
            is ResultState.NoAuthorization -> {
                Toast.makeText(this, "NoAuthorization", Toast.LENGTH_SHORT).show()
            }
            is ResultState.Success -> {
                val json = result.data
                val title = result.data
                Toast.makeText(this, "Success, Title is ; $title", Toast.LENGTH_SHORT).show()
                Log.d("Articles", "Success ${result.data}")
            }
        }
    }

    private fun manageSource(result: ResultState<MutableList<NewsSourceApi>>){
        when(result){
            is ResultState.Error -> {
                Toast.makeText(this, "Error ${result.throwable}", Toast.LENGTH_SHORT).show()
                Log.d("Articles", "Error ${result.throwable}")
            }
            is ResultState.Failed -> {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
            is ResultState.Loading -> {
                Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
            }
            is ResultState.NoAuthorization -> {
                Toast.makeText(this, "NoAuthorization", Toast.LENGTH_SHORT).show()
            }
            is ResultState.Success -> {
                val json = result.data
                val title = result.data
                Toast.makeText(this, "Success, Title is ; $title", Toast.LENGTH_SHORT).show()
                Log.d("Sources", "Success ${result.data}")


//                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

}