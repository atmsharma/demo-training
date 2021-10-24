package com.hybris.training.facades.backInStock.impl;

import com.hybris.training.facades.backInStock.TrainingBackInStockProductFacade;
import de.hybris.platform.servicelayer.model.ModelService;
import com.hybris.training.core.model.NotificationRequestModel;

public class DefaultTrainingBackInStockProductFacadeImpl implements TrainingBackInStockProductFacade {

    private ModelService modelService;

    @Override
    public void addNotificationRequest(String productCode, String email) {
        final NotificationRequestModel notificationRequest = modelService.create(NotificationRequestModel.class);
        notificationRequest.setProdductCode(productCode);
        notificationRequest.setEmail(email);
        modelService.save(productCode);

    }
}
