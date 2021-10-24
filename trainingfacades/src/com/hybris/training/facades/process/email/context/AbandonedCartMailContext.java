package com.hybris.training.facades.process.email.context;

import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import com.hybris.training.core.model.AbandonedCartMailProcessModel;
import de.hybris.platform.core.model.order.CartModel;

public class AbandonedCartMailContext extends CustomerEmailContext {
    private String name;
    private CartModel cart;

    @Override
    public void init(final StoreFrontCustomerProcessModel processModel, final EmailPageModel emailPageModel)
    {
        super.init(processModel, emailPageModel);
        if (processModel instanceof AbandonedCartMailProcessModel)
        {
            setName(((AbandonedCartMailProcessModel) processModel).getName());
            setCart(((AbandonedCartMailProcessModel) processModel).getCart());
        }
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CartModel getCart() {
        return cart;
    }

    public void setCart(CartModel cart) {
        this.cart = cart;
    }



}
