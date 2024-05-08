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
import com.sopt.now.test.data.Profile
import com.sopt.now.test.friend.FriendAdapter

class HomeFragment: Fragment() {
    private var _binding: FragmentHomeBinding? = null

    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "바인딩 객체 좀 생성해주세요 제발!!" }

    private val friendViewModel by viewModels<HomeViewModel>()
    private val userViewModel by viewModels<UserInfoViewModel>()
    var userList = mutableListOf<Profile>()

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

        setFriendProfile()
        setUserProfile()
    }

    // 사용자 프로필 설정
    private fun setUserProfile() {
        userViewModel.userInfo()

        userViewModel.userInfoLiveData.observe(requireActivity()) { userInfo ->
            userInfo?.let {
                val userProfile = Profile(
                    userImage = R.drawable.iv_user_profile.toString(),
                    userName = userInfo.data.nickname,
                    userInfo = userInfo.data.phone,
                )
                userList.add(0, userProfile)
                setRecyclerView()
            }
        }
    }

    // 친구 프로필 설정
    private fun setFriendProfile() {
        friendViewModel.getFriendInfo(0)

        friendViewModel.friendInfoLiveData.observe(requireActivity()) { friendInfo ->
            friendInfo?.let {
                friendInfo.data.forEach { friendData ->
                    val friend = Profile(
                        userImage = friendData.avatar,
                        userName = friendData.firstName,
                        userInfo = friendData.email
                    )
                    userList.add(friend)
                }
                setRecyclerView()
            }
        }
    }

    // FriendAdapter 연결
    private fun setRecyclerView(){
        val friendAdapter = FriendAdapter(userList)
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