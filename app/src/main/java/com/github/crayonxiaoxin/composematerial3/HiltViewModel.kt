package com.github.crayonxiaoxin.composematerial3

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel 注入
 *
 * 标记 ViewModel 注入 @HiltViewModel
 *
 * 注入参数 @Inject（并且在 @Module 中定义 @provide 为其提供实例方法）
 */
@HiltViewModel
class HiltViewModel @Inject constructor(
    val repository: HiltRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    @Inject
    lateinit var secondRepository: HiltRepository
}