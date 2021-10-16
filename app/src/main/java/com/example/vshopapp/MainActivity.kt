package com.example.vshopapp

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.vshopapp.adapters.*
import com.example.vshopapp.model.*
import com.example.vshopapp.utils.OnSnapPositionChangeListener
import com.example.vshopapp.utils.SnapOnScrollListener
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    companion object {
        private val ACTION_VOICE_SEARCH = "com.google.android.gms.actions.SEARCH_ACTION"
    }

    private lateinit var viewPager: ViewPager
    private var featured = ArrayList<Feature>()
    private lateinit var dots: SpringDotsIndicator
    private lateinit var viewModel: com.example.vshopapp.AllDataViewModel
    private var viewpageradapter: ViewPagerAdapter? = null

    private var productsAdapter: ProductsAdapter? = null
    private var product = ArrayList<Product>()
    private var category = ArrayList<Category>()
    private var categoryAdapter: CategoryAdapter? = null
    private var collection = ArrayList<Collectioon>()
    private var editorshops = ArrayList<EditorShops>()
    private var editorAdapter: EditorAdapter? = null

    private var newshops = ArrayList<EditorShops>()
    private var nsAdapter: NewshopAdapter? = null

    private var searchView by Delegates.notNull<SearchView>()
    private val swipeContainer: SwipeRefreshLayout by lazy {
        findViewById(R.id.swiperefresh) as SwipeRefreshLayout
    }
    private val snapHelper = LinearSnapHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(maintoolbar)
        setupAdapters()
        setOnClickUI()

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )

        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener {
            linearlayout.visibility = GONE
            refreshAdapters()

        }

    }

    private fun setupAdapters() {
        viewPager = findViewById(R.id.viewPager)
        val coimage = findViewById<ImageView>(R.id.collections_image)
        val ecover = findViewById<ImageView>(R.id.editor_cover)
        val editorRecyclerview= findViewById<RecyclerView>(R.id.editorshop_recycler_view)

        viewModel = ViewModelProvider(this).get(com.example.vshopapp.AllDataViewModel::class.java)
        viewModel.getData()?.observe(this, Observer {
            featured = it[0].feature
            viewpageradapter =
                ViewPagerAdapter(
                    this,
                    featured
                )
            viewPager.adapter = viewpageradapter

            dots = findViewById(R.id.dot2)
            dots.setViewPager(viewPager)

            products_title.text = it[1].title
            product = it[1].product
            productsAdapter = ProductsAdapter(product)
            products_recycler_view.adapter = productsAdapter
            snapHelper.attachToRecyclerView(products_recycler_view)

            categories_title.text = it[2].title
            category = it[2].category
            categoryAdapter = CategoryAdapter(category)
            categories_recycler_view.adapter = categoryAdapter
            snapHelper.attachToRecyclerView(categories_recycler_view)

            collections_title.text = it[3].title
            collection = it[3].collection
            collections_subtitle.text = collection[0].title
            collections_definition.text = collection[0].definition

            val url = collection[0].cocover.courl
            if(url.isNotEmpty()) {
                Glide.with(coimage.context)
                    .load(url)
                    .into(coimage)
            }

            editorshop_title.text = it[4].title
            editorshops = it[4].editorshops
            editorAdapter = EditorAdapter(editorshops)
            editorRecyclerview.adapter = editorAdapter

            snapHelper.attachToRecyclerView(editorRecyclerview)

            val layoutManager = editorRecyclerview.layoutManager

            val behavior: SnapOnScrollListener.Behavior = SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL
            var onSnapPositionChangeListener: OnSnapPositionChangeListener? = null

            var scrollPosition = 0
            val efcurl = editorshops[scrollPosition].escover.esurl
            if(efcurl!!.isNotEmpty()) {
                Glide.with(ecover.context)
                    .load(efcurl)
                    .into(ecover)
            }

            /*scrollPosition = editorRecyclerview.attachSnapHelperWithListener(editorRecyclerview, snapHelper, behavior, onSnapPositionChangeListener)
            if(scrollPosition>= 0 && scrollPosition<10) {
                val ecurl = it[4].editorshops[scrollPosition].escover.url
                if(ecurl.isNotEmpty()) {
                    Glide.with(ecover.context)
                        .load(url)
                        .into(ecover)
                }
            }*/

            newshops_title.text = it[5].title
            newshops = it[5].editorshops
            nsAdapter = NewshopAdapter(newshops)
            ns_recycler_view.adapter = nsAdapter
            snapHelper.attachToRecyclerView(ns_recycler_view)

        })

    }

    private fun refreshAdapters() {
        viewPager = findViewById(R.id.viewPager)
        val coimage = findViewById<ImageView>(R.id.collections_image)
        val ecover = findViewById<ImageView>(R.id.editor_cover)
        val editorRecyclerview= findViewById<RecyclerView>(R.id.editorshop_recycler_view)

        snapHelper.attachToRecyclerView(editorRecyclerview)

        viewModel = ViewModelProvider(this).get(com.example.vshopapp.AllDataViewModel::class.java)
        viewModel.getData()?.observe(this, Observer {
            featured = it[0].feature
            viewpageradapter?.clear()
            viewpageradapter?.addAll(featured)

            products_title.text = it[1].title
            product = it[1].product
            productsAdapter?.clear()
            productsAdapter?.addAll(product)

            categories_title.text = it[2].title
            category = it[2].category
            categoryAdapter?.clear()
            categoryAdapter?.addAll(category)

            collections_title.text = it[3].title
            collection = it[3].collection
            collections_subtitle.text = collection[0].title
            collections_definition.text = collection[0].definition

            val url = collection[0].cocover.courl
            if(url.isNotEmpty()) {
                Glide.with(coimage.context)
                    .load(url)
                    .into(coimage)
            }

            editorshop_title.text = it[4].title
            editorshops = it[4].editorshops
            editorAdapter?.clear()
            editorAdapter?.addAll(editorshops)

            val layoutManager = editorRecyclerview.layoutManager

            val behavior: SnapOnScrollListener.Behavior = SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL
            var onSnapPositionChangeListener: OnSnapPositionChangeListener? = null

            var scrollPosition = 0
            val efcurl = editorshops[scrollPosition].escover.esurl
            if(efcurl!!.isNotEmpty()) {
                Glide.with(ecover.context)
                    .load(efcurl)
                    .into(ecover)
            }


            newshops_title.text = it[5].title
            newshops = it[5].editorshops
            nsAdapter?.clear()
            nsAdapter?.addAll(newshops)

            swipeContainer.isRefreshing = false
            linearlayout.visibility = VISIBLE
        })

    }

    private fun setOnClickUI() {
        val productsAll = findViewById<TextView>(R.id.products_all)
        productsAll.setOnClickListener {
            val intent = Intent(this, com.example.vshopapp.ProductsActivity::class.java);
            intent.putExtra("products", product)
            startActivity(intent)
        }

        val collectionsAll = findViewById<TextView>(R.id.collections_all)
        collectionsAll.setOnClickListener {
            val intent = Intent(this, com.example.vshopapp.CollectionsActivity::class.java);
            intent.putExtra("collections", collection)
            startActivity(intent)
        }

        val editorAll = findViewById<TextView>(R.id.editor_all)
        editorAll.setOnClickListener {
            val intent = Intent(this, com.example.vshopapp.EditorActivity::class.java);
            intent.putExtra("editor", editorshops)
            startActivity(intent)
        }

        val nshopsAll =  findViewById<TextView>(R.id.newshops_all)
        nshopsAll.setOnClickListener {
            val intent = Intent(this, com.example.vshopapp.NewShopsActivity::class.java);
            intent.putExtra("newshops", newshops)
            startActivity(intent)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleVoiceSearch(intent)
    }

    private fun handleVoiceSearch(intent: Intent) {
        if (com.example.vshopapp.MainActivity.Companion.ACTION_VOICE_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            setSearchViewVisible(true)
            searchView.setQuery(query, true)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        searchView = searchItem?.actionView as SearchView

        searchView.setOnSearchClickListener { setSearchViewVisible(true) }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("onQueryTextSubmit", query)
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("onQueryTextChange", newText)
                //adapter.getFilter().filter(newText)
                //recyclerview.visible()
                return false
            }
        })

        val searchManager: SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager;
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()))

        intent?.let { handleVoiceSearch(it) }

        return true
    }

    override fun onBackPressed() {
        if (!searchView.isIconified) {
            setSearchViewVisible(false)
        } else {
            super.onBackPressed()
        }
    }

    private fun setSearchViewVisible(visible: Boolean) {
        if (searchView.isIconified == visible) {
            searchView.isIconified = !visible
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(visible)
    }

    fun RecyclerView.attachSnapHelperWithListener(recyclerView: RecyclerView,
        snapHelper: SnapHelper,
        behavior: SnapOnScrollListener.Behavior = SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL,
        onSnapPositionChangeListener: OnSnapPositionChangeListener?): Int {

        snapHelper.attachToRecyclerView(recyclerView)
        val snapOnScrollListener = SnapOnScrollListener(snapHelper, behavior, onSnapPositionChangeListener)
        addOnScrollListener(snapOnScrollListener)

        return snapOnScrollListener.maybeNotifySnapPositionChange(recyclerView)
    }
}