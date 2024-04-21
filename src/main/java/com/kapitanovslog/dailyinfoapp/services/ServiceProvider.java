package com.kapitanovslog.dailyinfoapp.services;

import com.kapitanovslog.dailyinfoapp.model.RequestMessage;

public interface ServiceProvider {

    String execute(RequestMessage requestMessage);
}
