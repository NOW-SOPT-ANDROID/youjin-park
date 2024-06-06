package com.sopt.now.test.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sopt.now.R
import com.sopt.now.databinding.FragmentHomeBinding
import com.sopt.now.test.core.util.fragment.showToast
import com.sopt.now.test.core.view.UiState
import com.sopt.now.test.data.Profile
import timber.log.Timber

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "바인딩 객체 좀 생성해주세요 제발!!" }

    private val userProfileViewModel by viewModels<UserProfileViewModel>()
    private val userList = mutableListOf<Profile>()

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
        initGetUserObserver()
        initGetFriendObserver()
    }

    // 사용자 프로필 설정
    private fun initGetUserObserver() {
        userProfileViewModel.getUserInfoProfileLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Success -> {
                    val userProfile = Profile(
                        userImage = R.drawable.iv_user_profile.toString(),
                        userName = it.data.data.nickname,
                        userInfo = it.data.data.phone,
                    )
                    userList.add(0, userProfile)
                    setRecyclerView()
                }

                is UiState.Failure -> showToast(it.errorMessage)
                is UiState.Loading -> Timber.d(getString(R.string.message_loading))
            }
        }
    }

    // 친구 프로필 설정
    private fun initGetFriendObserver() {
        userProfileViewModel.getFriendProfileLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Success -> {
                    val friendProfiles = it.data.data.map { friend ->
                        Profile(
                            userImage = friend.avatar,
                            userName = friend.firstName,
                            userInfo = friend.email
                        )
                    }
                    // 2번째부터 넣음
                    userList.addAll(1, friendProfiles)
                    setRecyclerView()
                }

                is UiState.Failure -> showToast(it.errorMessage)
                is UiState.Loading -> Timber.d(getString(R.string.message_loading))
            }
        }
    }

    // UserProfileAdapter 연결
    private fun setRecyclerView() {
        val friendAdapter = UserProfileAdapter(userList)
        binding.rvFriends.adapter = friendAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}