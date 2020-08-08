package com.wong.odd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {
    private var mRV: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    /**
     * 初始化
     */
    private fun init(){
        mRV = findViewById(R.id.rv)
        // 设置RecyclerView布局
        mRV!!.layoutManager = LinearLayoutManager(this)
        // 设置RecyclerView分割线
        val dividerItemDecoration: DividerItemDecoration = DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider))
        // 设置RecyclerView适配器
        val adapter = RvAdapter()
        mRV!!.adapter = adapter
        // 填充数据
        val list: MutableList<DataBean> = ArrayList()
        for (i in 0..29) {
            list.add(DataBean("name$i"))
        }
        adapter.list = list
        // 通知RecyclerView更新视图
        adapter.notifyDataSetChanged()
    }
}