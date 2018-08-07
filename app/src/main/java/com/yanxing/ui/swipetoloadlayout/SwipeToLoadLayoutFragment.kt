package com.yanxing.ui.swipetoloadlayout

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import com.aspsine.swipetoloadlayout.OnLoadMoreListener
import com.aspsine.swipetoloadlayout.OnRefreshListener
import com.yanxing.adapterlibrary.RecyclerViewAdapter
import com.yanxing.base.BaseFragment
import com.yanxing.ui.R

import java.util.ArrayList

import kotlinx.android.synthetic.main.fragment_swipe_to_load_layout.*

/**
 * SwipeToLoadLayout
 * Created by 李双祥 on 2017/4/9.
 */
class SwipeToLoadLayoutFragment : BaseFragment(), OnRefreshListener, OnLoadMoreListener {



    private val mList = ArrayList<Int>()
    private var mIntegerRecyclerViewAdapter: RecyclerViewAdapter<Int>? = null
    private var mIndex = 4

    override fun getLayoutResID(): Int {
        return R.layout.fragment_swipe_to_load_layout
    }

    override fun afterInstanceView() {
        swipeToLoadLayout.setOnRefreshListener(this)
        swipeToLoadLayout.setOnLoadMoreListener(this)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        swipeToLoadLayout.post { swipeToLoadLayout.isRefreshing = true }
    }

    override fun onLoadMore() {
        for (i in mIndex until mIndex + 4) {
            mList.add(i)
        }
        mIndex = mIndex + 4
        mIntegerRecyclerViewAdapter!!.update(mList)
    }

    override fun onRefresh() {
        mList.add(1)
        mList.add(2)
        mList.add(3)
        mIntegerRecyclerViewAdapter = object : RecyclerViewAdapter<Int>(mList, R.layout.adapter_recyclerview_load_more) {
            override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
                holder.setText(R.id.text, mList[position].toString())
            }
        }
        swipe_target.layoutManager = LinearLayoutManager(activity)
        swipe_target.adapter = mIntegerRecyclerViewAdapter
        swipeToLoadLayout.isRefreshing = false
    }
}