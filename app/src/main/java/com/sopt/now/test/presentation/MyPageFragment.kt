package com.sopt.now.test.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sopt.now.databinding.FragmentMypageBinding
import com.sopt.now.test.core.view.UiState
import com.sopt.now.test.data.dto.response.ResponseUserInfoDto
import timber.log.Timber

class MyPageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding: FragmentMypageBinding
        get() = requireNotNull(_binding) { "바인딩 객체 좀 생성해주세요 제발!!" }

    private val userViewModel by viewModels<UserInfoViewModel>()

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
        initGetUserInfoObserver()
    }

    // 사용자 프로필 설정
    private fun initGetUserInfoObserver() {
        userViewModel.getUserInfoLiveData.observe(requireActivity()) {
            when (it) {
                is UiState.Success -> initMyProfile(it.data)
                is UiState.Failure -> Timber.d("실패 : $it")
                is UiState.Loading -> Timber.d("로딩 중")
            }
        }
    }

    // 받아온 UserData 적용
    private fun initMyProfile(userData : ResponseUserInfoDto) {
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