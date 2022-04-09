package com.example.desaa.ui.user.village_introduction_submission

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VillageIntroductionSubmissionViewModel: ViewModel() {
    var typeLetter = MutableLiveData<String>()
    var nameNik = MutableLiveData<String>()
    var reason = MutableLiveData<String>()
    var image = MutableLiveData<String>()
}