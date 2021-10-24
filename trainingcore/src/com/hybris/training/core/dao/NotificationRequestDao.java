package com.hybris.training.core.dao;


import com.hybris.training.core.model.NotificationRequestModel;

import java.util.List;

public interface NotificationRequestDao {

 

        /**
         * This method will return list of  NotificationRequest.
         * @return  list of NotificationRequest
         * 
         */
        public List<NotificationRequestModel> getNotificationRequests();

    }
