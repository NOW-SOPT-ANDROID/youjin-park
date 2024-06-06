package com.sopt.now.test.presentation.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sopt.now.R
import com.sopt.now.databinding.FragmentMypageBinding
import com.sopt.now.test.core.util.fragment.showToast
import com.sopt.now.test.core.view.UiState
import com.sopt.now.test.data.dto.response.ResponseUserInfoDto
import com.sopt.now.test.presentation.home.UserProfileViewModel
import timber.log.Timber

class MyPageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding: FragmentMypageBinding
        get() = requireNotNull(_binding) { "바인딩 객체 좀 생성해주세요 제발!!" }

    private val userProfileViewModel by viewModels<UserProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGetUserObserver()
    }

    // 사용자 프로필 설정
    private fun initGetUserObserver() {
        userProfileViewModel.getUserInfoProfileLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Success -> initMyProfile(it.data)
                is UiState.Failure -> showToast(it.errorMessage)
                is UiState.Loading -> Timber.d(getString(R.string.message_loading))
            }
        }
    }

    // 받아온 UserData 적용
    private fun initMyProfile(userData: ResponseUserInfoDto) {
        with(binding) {
            tvMyId.text = userData.data.authenticationId
            tvMyName.text = userData.data.nickname
            tvMyPhone.text = userData.data.phone
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}