package com.example.a20220401_dilipkumarnara_nycschools.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a20220401_dilipkumarnara_nycschools.R
import com.example.a20220401_dilipkumarnara_nycschools.data.SchoolDataItem

class SchoolsAdapter(
    context: Context,
    dataList: List<SchoolDataItem>,
    listener: SchoolsAdapterListener,
) :
    RecyclerView.Adapter<SchoolsAdapter.ItemViewHolder>() {
    private var context: Context = context
    private var dataList: List<SchoolDataItem> = dataList
    private var listener = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.school_list_item,parent, false))

    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        viewHolder.bindRequestACallItems(dataList[position])
    }

    override fun getItemCount() = dataList.size

    inner class ItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        var schoolName: TextView = v.findViewById(R.id.textView)

        fun bindRequestACallItems(school: SchoolDataItem) {
//            view.tv_app_links.text = sectionItems.linkLabel
            schoolName.text = school.school_name
            view.setOnClickListener {
                listener.onSchoolClicked(school)
            }
        }
    }

    interface SchoolsAdapterListener {
        fun onSchoolClicked(appLink: SchoolDataItem)
    }
}