package com.wong.odd

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RvAdapter : RecyclerView.Adapter<RvAdapter.ItemViewHolder>() {

    /**
     * 设置要显示的数据
     * @param list
     */
    var list: List<DataBean>? = null
    /**
     * 返回选中的Item index
     * priavate set 可以让属性只能在本类中设置，即对外界来讲，它是只读的
     * @return
     */
    var selectedPosition = -1
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (list != null) {
            list!!.size
        } else 0
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.mTV.text = list!![position].name
        holder.mCB.isChecked = list!![position].isChecked
        // 要实现单选的话，就要选择点击事件，然后在点击事件中根据选择状态做变化，不要选用setOnCheckedChangeListener()
        holder.mCB.setOnClickListener {
            holder.mCB.post {
                if (selectedPosition != -1) {
                    list!![selectedPosition].isChecked = false
                    // notifyItemChanged必须在UI线程中执行，否则会报：
                    // java.lang.IllegalStateException: Cannot call this method
                    // while RecyclerView is computing a layout or scrolling
                    notifyItemChanged(selectedPosition)
                }
                list!![position].isChecked = holder.mCB.isChecked
                selectedPosition = if (holder.mCB.isChecked) {
                    position
                } else {
                    -1
                }
            }
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mCB: CheckBox = itemView.findViewById(R.id.checkBox)
        var mTV: TextView = itemView.findViewById(R.id.textView)

    }
}