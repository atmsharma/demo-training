package com.hybris.training.core.service.impl;

import com.hybris.training.core.service.MailNotificationService;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;
import com.hybris.training.core.model.AbandonedCartMailProcessModel;
import de.hybris.platform.store.BaseStoreModel;

public class DefaultMailNotificationService  implements MailNotificationService {


    private BusinessProcessService businessProcessService;
    private BaseSiteService baseSiteService;
    private ModelService modelService;




    @Override
    public void sendMailToCustomer(CartModel cart, String email) {

        final AbandonedCartMailProcessModel abandonedCartMailProcess = getBusinessProcessService()
                .createProcess("Cart-" + System.currentTimeMillis(), "abandonedCartMailProcess");
        final CMSSiteModel currentSite;
        if (null != getBaseSiteService().getCurrentBaseSite()) {
            currentSite = (CMSSiteModel) getBaseSiteService().getCurrentBaseSite();
        } else {
            currentSite = (CMSSiteModel) getBaseSiteService().getBaseSiteForUID("testSite");
        }
        final BaseStoreModel baseStoreModel = currentSite.getStores().get(0);
        abandonedCartMailProcess.setCart(cart);
        abandonedCartMailProcess.setSite(currentSite);
        abandonedCartMailProcess.setStore(baseStoreModel);
        abandonedCartMailProcess.setCurrency(baseStoreModel.getDefaultCurrency());
        abandonedCartMailProcess.setLanguage(baseStoreModel.getDefaultLanguage());
        abandonedCartMailProcess.setEmail(email);

        getModelService().save(abandonedCartMailProcess);
        getBusinessProcessService().startProcess(abandonedCartMailProcess);
    }



    public BusinessProcessService getBusinessProcessService() {
        return businessProcessService;
    }

    public void setBusinessProcessService(BusinessProcessService businessProcessService) {
        this.businessProcessService = businessProcessService;
    }

    public BaseSiteService getBaseSiteService() {
        return baseSiteService;
    }

    public void setBaseSiteService(BaseSiteService baseSiteService) {
        this.baseSiteService = baseSiteService;
    }

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }



    }



