package com.hybris.training.core.cronjob;

import com.hybris.training.core.service.MailNotificationService;
import com.hybris.training.core.service.NotificationRequestService;
import com.hybris.training.core.model.NotificationRequestModel;
import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.commerceservices.order.dao.CommerceCartDao;
import de.hybris.platform.commerceservices.stock.strategies.impl.CommerceStockLevelStatusStrategy;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.time.TimeService;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;


/**
 * A Cron Job to send Back in Stock email Notification to the user.
 *
 */
public class TrainingBackInStockEMailNotificationJob extends AbstractJobPerformable<CronJobModel> {
    private static final Logger LOG = Logger.getLogger(com.hybris.training.core.cronjob.TrainingBackInStockEMailNotificationJob.class);

    private CommerceCartDao commerceCartDao;
    private TimeService timeService;
    private UserService userService;
    private MailNotificationService mailNotificationService;
    private NotificationRequestService notificationRequestService;
    private ProductService productService;
    private CommerceStockLevelStatusStrategy stockLevelStrategy;



    private ModelService modelService;


    @Override
    public PerformResult perform(final CronJobModel job) {
        try {

            final List<NotificationRequestModel> notificationRequests = getNotificationRequestService().getNotificationRequests();

            for (final NotificationRequestModel notificationRequest : notificationRequests) {

                final ProductModel product = getProductService().getProductForCode(notificationRequest.getProductCode());
                final StockLevelStatus stockLevelStatus = stockLevelStrategy.checkStatus(product.getStockLevels());

                if (StockLevelStatus.INSTOCK.equals(product.getStock().getStockLevelStatus())) {
                    // Sent email process just like we did in use case #1
                    // or we can use notificationService.notifyCustomer(...) method call
                    // getMailNotificationService().sendMailToCustomer(product, notificationRequest.getEMail());
                    notificationRequest.setNotified(Boolean.TRUE);
                    modelService.save(notificationRequest);
                }

            }

            return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        } catch (final Exception e) {
            LOG.error("Exception occurred while sending Product Bak in Stock email notification", e);
            return new PerformResult(CronJobResult.ERROR, CronJobStatus.ABORTED);
        }
    }


    protected TimeService getTimeService() {
        return timeService;
    }

    @Required
    public void setTimeService(final TimeService timeService) {
        this.timeService = timeService;
    }

    protected ModelService getModelService() {
        return modelService;
    }

    protected UserService getUserService() {
        return userService;
    }

    @Required
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

    protected CommerceCartDao getCommerceCartDao() {
        return commerceCartDao;
    }

    @Required
    public void setCommerceCartDao(final CommerceCartDao commerceCartDao) {
        this.commerceCartDao = commerceCartDao;
    }

    protected MailNotificationService getMailNotificationService() {
        return mailNotificationService;
    }

    @Required
    public void setMailNotificationService(MailNotificationService mailNotificationService) {
        this.mailNotificationService = mailNotificationService;
    }

    protected NotificationRequestService getNotificationRequestService() {
        return notificationRequestService;
    }

    @Required
    public void setNotificationRequestService(NotificationRequestService notificationRequestService) {
        this.notificationRequestService = notificationRequestService;
    }

    protected ProductService getProductService() {
        return productService;
    }

    @Required
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    protected CommerceStockLevelStatusStrategy getStockLevelStrategy() {
        return stockLevelStrategy;
    }

    @Required
    public void setStockLevelStrategy(CommerceStockLevelStatusStrategy stockLevelStrategy) {
        this.stockLevelStrategy = stockLevelStrategy;
    }

    @Override
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

}