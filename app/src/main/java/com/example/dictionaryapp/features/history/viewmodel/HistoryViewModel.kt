package com.example.dictionaryapp.features.history.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.quangnh.core.base.viewmodel.BaseViewModel
import com.quangnh.core.utils.TimeUtil
import com.quangnh.domain.model.WordInfo
import com.quangnh.domain.model.recyclerview.BaseModelHistoryWord
import com.quangnh.domain.model.recyclerview.DateTimeObject
import com.quangnh.domain.usecase.GetAllWordLocal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.LinkedHashMap
import java.util.Objects
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getAllWordLocal: GetAllWordLocal
) : BaseViewModel() {

    private val _listWordLocal: MutableLiveData<List<BaseModelHistoryWord>> by lazy { MutableLiveData() }

    val listWordLocal: LiveData<List<BaseModelHistoryWord>> get() = _listWordLocal

    override fun onDidBindViewModel() {
        getListWordFromLocal()
    }

    /**
     * Get all word from local database
     */
    private fun getListWordFromLocal() {
        viewModelScope.launch {
            val listWord = getAllWordLocal()

            val listBaseWord = async(Dispatchers.IO) { groupData(listWord) }
            _listWordLocal.value = listBaseWord.await()
        }
    }

    private fun groupData(listWord: List<WordInfo>): List<BaseModelHistoryWord> {
        var list: MutableSet<WordInfo>
        val groupHashMap = LinkedHashMap<String, MutableSet<WordInfo>>()

        for (word in listWord) {
            if (word.timeStamp != null) {
                val hasMapKey = TimeUtil.long2String(word.timeStamp, "dd/MM/yyyy")

                if (groupHashMap.containsKey(hasMapKey)) {
                    groupHashMap[hasMapKey]?.add(word)
                } else {
                    list = LinkedHashSet()
                    list.add(word)
                    groupHashMap[hasMapKey] = list
                }
            }
        }

        val listBaseWord = ArrayList<BaseModelHistoryWord>()

        for (date in groupHashMap.keys) {
            val dateItem = DateTimeObject(date)

            listBaseWord.add(dateItem)
            listBaseWord.addAll(
                Objects.requireNonNull<Set<WordInfo>>(
                    groupHashMap[date]
                )
            )
            groupHashMap[date]?.clear()
        }

        return listBaseWord
    }
}