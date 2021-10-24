package com.hybris.training.core.cronjob;

import com.hybris.training.core.service.MailNotificationService;
import de.hybris.platform.acceleratorservices.model.CartRemovalCronJobModel;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.order.dao.CommerceCartDao;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.time.TimeService;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Required;



/**
 * A Cron Job to send Abandoned Cart Notification email to the user.
 */
public class AbandonedCartMailNotificationJob extends AbstractJobPerformable<CartRemovalCronJobModel>
{
    private static final Logger LOG = Logger.getLogger(com.hybris.training.core.cronjob.AbandonedCartMailNotificationJob.class);

    private CommerceCartDao commerceCartDao;
    private TimeService timeService;
    private UserService userService;



    private MailNotificationService mailNotificationService;

    private static final int DEFAULT_CART_MAX_AGE = 86400;
    private static final int DEFAULT_ANONYMOUS_CART_MAX_AGE = 86400;

    @Override
    public PerformResult perform(final CartRemovalCronJobModel job)
    {
        try
        {
            for (final BaseSiteModel site : job.getSites())
            {
                int age = DEFAULT_CART_MAX_AGE;

                if (site.getCartRemovalAge() != null)
                {
                    age = site.getCartRemovalAge().intValue();
                }
                for (final CartModel oldCart : getCommerceCartDao().getCartsForRemovalForSiteAndUser(
                        new DateTime(getTimeService().getCurrentTime()).minusSeconds(age).toDate(), site, null))
                {
                    final  String userEmail= oldCart.getUser().getUid();
                    //sentEmail
                    getMailNotificationService().sendMailToCustomer(oldCart, userEmail);
                }

                age = DEFAULT_ANONYMOUS_CART_MAX_AGE;

                if (site.getAnonymousCartRemovalAge() != null)
                {
                    age = site.getAnonymousCartRemovalAge().intValue();
                }

                for (final CartModel oldCart : getCommerceCartDao().getCartsForRemovalForSiteAndUser(
                        new DateTime(getTimeService().getCurrentTime()).minusSeconds(age).toDate(), site,
                        getUserService().getAnonymousUser()))
                {
                    final  String userEmail= getUserService().getAnonymousUser().getUid();
                    //sentEmail
                    getMailNotificationService().sendMailToCustomer(oldCart, userEmail);

                }

            }

            return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        }
        catch (final Exception e)
        {
            LOG.error("Exception occurred while sending cart email notification", e);
            return new PerformResult(CronJobResult.ERROR, CronJobStatus.ABORTED);
        }
    }


    protected TimeService getTimeService()
    {
        return timeService;
    }

    @Required
    public void setTimeService(final TimeService timeService)
    {
        this.timeService = timeService;
    }

    protected ModelService getModelService()
    {
        return modelService;
    }

    protected UserService getUserService()
    {
        return userService;
    }

    @Required
    public void setUserService(final UserService userService)
    {
        this.userService = userService;
    }

    protected CommerceCartDao getCommerceCartDao()
    {
        return commerceCartDao;
    }

    @Required
    public void setCommerceCartDao(final CommerceCartDao commerceCartDao)
    {
        this.commerceCartDao = commerceCartDao;
    }

    protected MailNotificationService getMailNotificationService() {
        return mailNotificationService;
    }

    @Required
    public void setMailNotificationService(MailNotificationService mailNotificationService) {
        this.mailNotificationService = mailNotificationService;
    }
}
