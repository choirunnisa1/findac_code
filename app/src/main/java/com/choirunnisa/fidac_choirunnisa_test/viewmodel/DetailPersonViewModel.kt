package com.choirunnisa.fidac_choirunnisa_test.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.choirunnisa.fidac_choirunnisa_test.interactor.FindacInteractor
import com.choirunnisa.fidac_choirunnisa_test.model.Name
import com.choirunnisa.fidac_choirunnisa_test.model.Pendidikan
import com.choirunnisa.fidac_choirunnisa_test.model.UsersData
import com.choirunnisa.fidac_choirunnisa_test.network.FindacService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailPersonViewModel @Inject constructor(findacService: FindacService):ViewModel() {
    private val findacInteractor = FindacInteractor(findacService)

    val title1 = MutableLiveData<String>()
    val title2 = MutableLiveData<String>()
    val dataDiri = MutableLiveData<List<Name>>()
    val studies = MutableLiveData<List<Pendidikan>>()

    suspend fun getTitle1(){
        val result = GlobalScope.async(Dispatchers.IO){
            findacInteractor.getUsers()
        }.await()
        GlobalScope.launch(Dispatchers.Main){
            title1.value = result.data_diri
        }
    }

    suspend fun getTitle2(){
        val result = GlobalScope.async(Dispatchers.IO){
            findacInteractor.getUsers()
        }.await()
        GlobalScope.launch(Dispatchers.Main){
            title2.value = result.riwayat_pendidikan
        }
    }

    suspend fun getListDataDiri(){
        val result = GlobalScope.async(Dispatchers.IO){
            findacInteractor.getUsers()
        }.await()
        GlobalScope.launch(Dispatchers.Main){
            dataDiri.value = result.subtitle1
        }
    }

    suspend fun getListStudies(){
        val result = GlobalScope.async(Dispatchers.IO){
            findacInteractor.getUsers()
        }.await()
        GlobalScope.launch(Dispatchers.Main){
            studies.value = result.subtitle2
        }
    }

}