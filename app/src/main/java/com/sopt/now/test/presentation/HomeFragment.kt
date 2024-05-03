package com.sopt.now.test.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.R
import com.sopt.now.databinding.FragmentHomeBinding
import com.sopt.now.test.data.Friend
import com.sopt.now.test.friend.FriendAdapter

class HomeFragment: Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "바인딩 객체 좀 생성해주세요 제발!!" }

    private val viewModel by viewModels<HomeViewModel>()
    private val userInfoViewModel by viewModels<UserInfoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setFriendList()
    }

    // 리스트에 사용자 정보 추가
    private fun setFriendList() {
        userInfoViewModel.userInfo()

        userInfoViewModel.userInfoLiveData.observe(requireActivity()) { userData ->
            userData?.let {
                val newFriend = Friend(
                    profileImage = R.drawable.iv_user_profile,
                    name = it.data.nickname,
                    phone = it.data.phone
                )
                viewModel.mockFriendList.add(0, newFriend)
                setRecyclerView()
            }
        }
    }

    // FriendAdapter 연결
    private fun setRecyclerView(){
        val friendAdapter = FriendAdapter(viewModel.mockFriendList)
        binding.rvFriends.run {
            layoutManager = LinearLayoutManager(requireContext())
            setAdapter(friendAdapter)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}