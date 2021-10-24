package com.hybris.training.core.dao.impl;

import com.hybris.training.core.dao.NotificationRequestDao;
import com.hybris.training.core.model.NotificationRequestModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Required;

import java.util.HashMap;
import java.util.Map;

public class DefaultNotificationRequestDao implements NotificationRequestDao {

    protected FlexibleSearchService flexibleSearchService;


    @Override
    public List<NotificationRequestModel> getNotificationRequests() {

        final String query = "Select {pk} from {NotificationRequest as N } where {N:notified}?=notifiedVal";
        FlexibleSearchQuery fQuery = new FlexibleSearchQuery(query);
        final Map params = new HashMap();
        params.put("notifiedVal", Boolean.FALSE);

        fQuery.addQueryParameters(queryParams);

        final SearchResult<NotificationRequestModel> result = getFlexibleSearchService().search(fQuery);

        return result.getResult();
    }

    @Required
    public void setFlexibleSearchService(FlexibleSearchService flexibleSearch) {
        this.flexibleSearchService = flexibleSearch;
    }

    protected FlexibleSearchService getFlexibleSearchService() {
        return this.flexibleSearchService;
    }


}
