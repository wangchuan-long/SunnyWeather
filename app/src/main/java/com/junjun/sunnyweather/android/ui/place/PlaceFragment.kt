package com.junjun.sunnyweather.android.ui.place

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.junjun.sunnyweather.android.R
import com.junjun.sunnyweather.android.ui.weather.WeatherActivity
import kotlinx.android.synthetic.main.fragment_place.*

/**
 * 搜索城市数据功能
 */
class PlaceFragment : Fragment() {

    // 使用lazy懒加载技术获取PlaceViewModel的实例
//    val viewModel by lazy { ViewModelProviders.of(this).get(PlaceViewModel::class.java) }
    val viewModel by lazy { ViewModelProvider(this)[PlaceViewModel::class.java]}

    private lateinit var adapter: PlaceAdapter

    // 加载fragment_place布局
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // 判断是否存储城市数据
        if (viewModel.isPlaceSaved()) {
            val place = viewModel.getSavePlace()
            val intent = Intent(context, WeatherActivity::class.java).apply {
                putExtra("location_lng", place.location.lng)
                putExtra("location_lat", place.location.lat)
                putExtra("place_name", place.name)
            }
            startActivity(intent)
            activity?.finish()
            return
        }
        // 设置LayoutManager和适配器
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        adapter = PlaceAdapter(this, viewModel.placeList)
        recyclerView.adapter = adapter
        // 监听搜索框内容的变化情况
        searchPlaceEdit.addTextChangedListener { editable ->
            val content = editable.toString()
            if (content.isNotEmpty()) {
                // 如果不为空：发起搜索城市数据的网络请求
                viewModel.searchPlaces(content)
            } else {
                // 如果为空：隐藏RecyclerView和显示背景图，清空placeList集合且刷新界面
                recyclerView.visibility = View.GONE
                bgImageView.visibility = View.VISIBLE
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }
        }
        // 获取服务器的响应
        viewModel.placeLiveData.observe(viewLifecycleOwner, Observer { result ->
            val places = result.getOrNull()
            if (places != null) {
                // 如果响应的结果不为空：显示RecyclerView和隐藏背景图，清空placeList集合，添加回调的数据到placeList集合且刷新界面
                recyclerView.visibility = View.VISIBLE
                bgImageView.visibility = View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)
                adapter.notifyDataSetChanged()
            } else {
                // 如果为空：说明发生异常，弹出Toast提示并打印具体的异常原因
                Toast.makeText(activity, "未能查询到任何地点", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }

}