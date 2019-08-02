package com.link.component_main.ui.find

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.link.component_main.R
import com.link.component_main.data.entity.MenuDetail

class FindAdapter(var context: Context) : BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    private var mData: List<MenuDetail>? = null

    fun setData(list: List<MenuDetail>) {
        mData = list
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: ViewHolder
        var layoutView = convertView

        if (layoutView == null) {
            layoutView = inflater.inflate(R.layout.main_item_find_minor, null)
            holder = ViewHolder()
            holder.content = layoutView!!.findViewById(R.id.content)
            holder.cover = layoutView.findViewById(R.id.cover)
            holder.name = layoutView.findViewById(R.id.name)
            holder.tags = layoutView.findViewById(R.id.tags)
            holder.read = layoutView.findViewById(R.id.read)
            layoutView.tag = holder
        } else {
            holder = layoutView.tag as ViewHolder
        }

        if (mData != null && !mData.isNullOrEmpty()) {
            Glide.with(context).load(mData!![position].albums[0]).into(holder.cover!!)
            holder.name!!.text = mData!![position].title
            holder.content!!.text = mData!![position].imtro
            holder.tags!!.text = mData!![position].tags
            holder.read!!.setOnClickListener {
                if (onItemClickListener != null) {
                    onItemClickListener!!.onItemClick(position)
                }
            }
        }

        return layoutView
    }

    override fun getItem(position: Int): Any? {
        if (mData != null && !mData.isNullOrEmpty()) {
            return mData!![position]
        }
        return null
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        if (mData != null && !mData.isNullOrEmpty()) {
            return mData!!.size
        }
        return 10
    }


    class ViewHolder {
        var cover: ImageView? = null
        var name: TextView? = null
        var content: TextView? = null
        var tags: TextView? = null
        var read: TextView? = null
    }

    var onItemClickListener: onBtnClickListener? = null

    interface onBtnClickListener {
        fun onItemClick(position: Int)
    }

}