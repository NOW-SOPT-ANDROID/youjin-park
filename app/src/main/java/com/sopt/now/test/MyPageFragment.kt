package com.sopt.now.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sopt.now.databinding.FragmentMypageBinding
import com.sopt.now.test.data.UserData

class MyPageFragment: Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding: FragmentMypageBinding
        get() = requireNotNull(_binding) { "바인딩 객체 좀 생성해주세요 제발!!" }

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

        setupUserData()
    }

    // 받아온 UserData 적용
    private fun setupUserData() {
        arguments?.getParcelable<UserData>("userData")?.let { userData ->
            with(binding) {
                tvMyName.text = userData.userName
                tvMyDescription.text = userData.selfDescription
                tvMyId.text = userData.userId
                tvMyPw.text = userData.userPw
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}