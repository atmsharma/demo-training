package com.hybris.training.core.service;


import com.hybris.training.core.model.NotificationRequestModel;

public interface NotificationRequestService {



        /**
         * This method will return list of  NotificationRequest.
         * @return  list of NotificationRequest
         *
         */
        public List<NotificationRequestModel> getNotificationRequests();

    }
