package com.link.fan.app.main.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.link.fan.R
import com.link.fan.data.InjectorUtils
import com.link.fan.databinding.FragmentCommunityListBinding

/**
 * copyright:TS
 * author:wujia
 * create:2019-11-21-20:09
 * email:wujia0916@thundersoft.com
 * description:
 */
class CommunityListFragment : Fragment() {

    private var mCommunityType = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mCommunityType = it.getInt(COMMUNITY_TYPE)
        }
    }

    private val viewModel: CommunityViewModel by viewModels {
        InjectorUtils.communityViewModelFactory()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val bind = DataBindingUtil.inflate<FragmentCommunityListBinding>(inflater, R.layout.fragment_community_list, container, false)
                .apply {
                    lifecycleOwner = this@CommunityListFragment.viewLifecycleOwner
                    val adapter = CommunityListAdapter()
                    subscribeUi(adapter)
                    list.adapter = adapter
                }

        return bind.root
    }

    private fun subscribeUi(adapter: CommunityListAdapter) {
        viewModel.communityLiveData.observe(this) {
            it?.run {
                adapter.submitList(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.requestData(mCommunityType)
    }


    companion object {

        private const val COMMUNITY_TYPE = "community_type"

        @JvmStatic
        fun newInstance(communityType: Int) =
                CommunityListFragment().apply {
                    arguments = Bundle().apply {
                        putInt(COMMUNITY_TYPE, communityType)
                    }
                }
    }
}
