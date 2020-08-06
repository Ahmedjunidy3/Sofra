package com.example.sofra.view.resturantCycle.viewmodel.userCycle;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.sofra.repository.ForgetPassRepository;


public class ForgetPassViewModel extends AndroidViewModel
        implements ForgetPassRepository.OnResetEmailResponseListener {
    private ForgetPassRepository forgetPassRepository;
    private MutableLiveData<String> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<String> getSuccessfulResetMsg() {
        return mutableLiveData;
    }

    public ForgetPassViewModel(@NonNull Application application) {
        super(application);
        forgetPassRepository = new ForgetPassRepository(application, this);
    }

    public void setRestResetPass(String email) {
        sendApiRestResetDetails(email);
    }

    private void sendApiRestResetDetails(String email) {
        forgetPassRepository.checkRestResetPassEmail(email);
    }

    public void setClientResetPass(String email) {
        sendApiClientResetDetails(email);
    }

    private void sendApiClientResetDetails(String email) {
        forgetPassRepository.checkClientResetPassEmail(email);
    }

    public void setClientNewPass(String pinCode, String newPass, String newPassConfirm) {
        sendApiClientNewPassDetails(pinCode, newPass, newPassConfirm);
    }

    private void sendApiClientNewPassDetails(String pinCode, String newPass, String newPassConfirm) {
        forgetPassRepository.checkClientNewPassDetails(pinCode, newPass, newPassConfirm);
    }

    public void setRestNewPass(String pinCode, String newPass, String newPassConfirm) {
        sendApiRestNewPassDetails(pinCode, newPass, newPassConfirm);
    }

    private void sendApiRestNewPassDetails(String pinCode, String newPass, String newPassConfirm) {
        forgetPassRepository.checkRestNewPassDetails(pinCode, newPass, newPassConfirm);
    }


    @Override
    public void onResponse(MutableLiveData<String> msg) {
        mutableLiveData.setValue(msg.getValue());
    }


}
