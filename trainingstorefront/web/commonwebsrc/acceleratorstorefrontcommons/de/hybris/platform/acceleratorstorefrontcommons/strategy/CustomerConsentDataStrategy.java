/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.acceleratorstorefrontcommons.strategy;

/**
 * Strategy for Customer Consent Management Operations in Storefront.
 */
public interface CustomerConsentDataStrategy
{
	/**
	 * Injects the customer consents into the session as a Hashmap.
	 *
	 */
	void populateCustomerConsentDataInSession();
}
