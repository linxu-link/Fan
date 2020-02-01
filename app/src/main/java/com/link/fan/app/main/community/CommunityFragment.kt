package com.link.fan.app.main.community


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.link.fan.R
import com.link.fan.adapters.BannerImageLoader
import com.link.fan.app.community.list.CommunityListFragment
import com.link.fan.databinding.FragmentCommunityBinding
import com.link.fan.navigation.communityUrl
import com.link.libraryannotation.FragmentDestination
import com.link.librarymodule.base.adapter.FixPagerAdapter
import com.youth.banner.Banner
import kotlinx.android.synthetic.main.header_community.*
import java.util.*

/**
 *  copyright:TS
 *  author:wujia
 *  create:2019-10-26-10:55
 *  email:wujia0916@thundersoft.com
 *  description:
 */
@FragmentDestination(pageUrl = communityUrl, needLogin = false, asStarter = false)
class CommunityFragment : Fragment() {

    private val images = arrayListOf(
            "https://upload-images.jianshu.io/upload_images/3146091-d1e7d74fed9444cb.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240",
            "https://upload-images.jianshu.io/upload_images/3146091-d1e7d74fed9444cb.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240",
            "https://upload-images.jianshu.io/upload_images/3146091-d1e7d74fed9444cb.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentCommunityBinding>(LayoutInflater.from(requireContext()),
                R.layout.fragment_community, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        val bannerView = binding.root.findViewById<Banner>(R.id.banner)
        bannerView.setDelayTime(5000)
        bannerView.setImageLoader(BannerImageLoader())
        bannerView.setImages(images)
        bannerView.start()

        val titles = arrayOf("最新活动", "食物杂谈")
        val fragmentList = ArrayList<Fragment>(2)
        fragmentList.add(CommunityListFragment.newInstance(1))
        fragmentList.add(CommunityListFragment.newInstance(2))
        val bottomPagerAdapter = FixPagerAdapter(childFragmentManager)
        bottomPagerAdapter.setFragments(fragmentList)
        bottomPagerAdapter.setTitles(titles)
        binding.bottomViewPager.adapter = bottomPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.bottomViewPager)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        banner.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        banner.stopAutoPlay()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                CommunityFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
