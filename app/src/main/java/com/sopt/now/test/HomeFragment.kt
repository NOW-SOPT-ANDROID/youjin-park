package com.sopt.now.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.R
import com.sopt.now.databinding.FragmentHomeBinding
import com.sopt.now.test.friend.Friend
import com.sopt.now.test.friend.FriendAdapter

class HomeFragment: Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "바인딩 객체 좀 생성해주세요 제발!!" }

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

        val friendAdapter = FriendAdapter(mockFriendList)
        binding.rvFriends.run {
            layoutManager = LinearLayoutManager(requireContext())
            setAdapter(friendAdapter)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val mockFriendList = listOf<Friend>(
        Friend(
            profileImage = R.drawable.iv_user_profile,
            name = "박유진",
            selfDescription = "안녕하세요! YB 박유진입니다.",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "AAA",
            selfDescription = "AAA님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "BBB",
            selfDescription = "BBB님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "CCC",
            selfDescription = "CCC님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "DDD",
            selfDescription = "DDD님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "EEE",
            selfDescription = "EEE님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "FFF",
            selfDescription = "FFF님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "GGG",
            selfDescription = "GGG님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "HHH",
            selfDescription = "HHH님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "III",
            selfDescription = "III님의 한 줄 소개",
        ),
        Friend(
            profileImage = R.drawable.iv_friend_profile,
            name = "JJJ",
            selfDescription = "JJJ님의 한 줄 소개",
        )
    )
}