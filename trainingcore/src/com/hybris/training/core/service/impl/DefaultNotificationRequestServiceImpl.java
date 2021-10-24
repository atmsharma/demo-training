package com.hybris.training.core.service.impl;

import com.hybris.training.core.dao.NotificationRequestDao;
import com.hybris.training.core.service.NotificationRequestService;
import com.hybris.training.core.model.NotificationRequestModel;

public class DefaultNotificationRequestServiceImpl  implements NotificationRequestService {

    private NotificationRequestDao notificationRequestDao;

    @Override
    public List<NotificationRequestModel> getNotificationRequests() {
        return getNotificationRequestDao().getNotificationRequests();
    }


    protected NotificationRequestDao getNotificationRequestDao() {
        return notificationRequestDao;
    }

    public void setNotificationRequestDao(NotificationRequestDao notificationRequestDao) {
        this.notificationRequestDao = notificationRequestDao;
    }


}
