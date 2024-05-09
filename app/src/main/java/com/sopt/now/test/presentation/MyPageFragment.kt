package com.sopt.now.test.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sopt.now.databinding.FragmentMypageBinding
import com.sopt.now.test.data.UserData
import com.sopt.now.test.data.UserPreference

class MyPageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding: FragmentMypageBinding
        get() = requireNotNull(_binding) { "바인딩 객체 좀 생성해주세요 제발!!" }

    private val viewModel by viewModels<UserInfoViewModel>()
    private lateinit var userPreference: UserPreference

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
        userPreference = UserPreference(requireContext())
        initObserver()
    }

    private fun initObserver() {
        viewModel.userInfoLiveData.observe(requireActivity()) { userData ->
            Log.d("userPreference before", "${userPreference.getUserData()}")
            userData?.let {
                val userData = UserData(
                    userId = userData.data.authenticationId,
                    userName = userData.data.nickname,
                    userPhone = userData.data.phone
                )
                userPreference.saveUserData(userData)
                setupUserData()
                Log.d("userPreference after", "${userPreference.getUserData()}")
            }
        }
    }

    // 받아온 UserData 적용
    private fun setupUserData() {
        val userData = userPreference.getUserData()
        if (userData != null) {
            with(binding) {
                tvMyId.text = userData.userId
                tvMyName.text = userData.userName
                tvMyPhone.text = userData.userPhone
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}