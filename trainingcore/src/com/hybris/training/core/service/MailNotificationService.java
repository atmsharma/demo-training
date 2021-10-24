package com.hybris.training.core.service;

import de.hybris.platform.core.model.order.CartModel;

public interface MailNotificationService {

    /**
     * Send  mail to customer.
     * @param cart
     * @param email
     */
    public void sendMailToCustomer(final CartModel cart, final String email);

}
