package com.example.vshopapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.vshopapp.R
import com.example.vshopapp.model.Feature

class ViewPagerAdapter(var context: Context, var featuredlist: ArrayList<Feature>): PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position:
    Int): Any {
        val featured = featuredlist[position]
        val view: View = LayoutInflater.from(context).inflate(R.layout.featured_layout,container,false)

        val cover = view.findViewById<ImageView>(R.id.featuredimage)
        val url = featured.cover?.url

        if(url?.isNotEmpty()!!) {
            Glide.with(cover.context)
                .load(url)
                .into(cover)
        }

        val title = view.findViewById<TextView>(R.id.featuredtitle)
        title.text = featured.title

        val subtitle = view.findViewById<TextView>(R.id.featuredsubtitle)
        subtitle.text = featured.subtitle

        view.tag = featured
        container.addView(view, 0)
        return view
    }

    override fun getCount(): Int = featuredlist.size

    override fun isViewFromObject(view: View, obj: Any): Boolean= (view == obj)

    override fun destroyItem(container: ViewGroup, position: Int,
                             obj: Any) = container.removeView(obj as View)

    // Clean all elements of the recycler
    fun clear() {
        featuredlist.clear()
        notifyDataSetChanged()
    }

    // Add a list of items -- change to type used
    fun addAll(list: ArrayList<Feature>) {
        featuredlist.addAll(list)
        notifyDataSetChanged()
    }

}