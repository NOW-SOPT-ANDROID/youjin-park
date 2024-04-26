package com.sopt.now.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.databinding.FragmentHomeBinding
import com.sopt.now.test.data.mockFriendList
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
}