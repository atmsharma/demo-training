
package com.hybris.training.storefront.controllers.pages;

import com.hybris.training.facades.backInStock.TrainingBackInStockProductFacade;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;



@Controller
@Scope("tenant")
@RequestMapping("/my-account/my-stocknotification")
public class TrainingStockNotificationPageController extends AbstractPageController
{
	private static final String PRODUCT_CODE_PATH_VARIABLE_PATTERN = "{productCode:.*}";

	@Resource(name = "trainingBackInStockProductFacade")
	private TrainingBackInStockProductFacade trainingBackInStockProductFacade;


	@RequestMapping(value = "/add/" + PRODUCT_CODE_PATH_VARIABLE_PATTERN)
	@RequireHardLogIn
	public @ResponseBody String addStockNotification(@PathVariable final String productCode,
			@ModelAttribute("stockNotificationForm") final String email,
			final HttpServletResponse response, final Model model)
	{
		trainingBackInStockProductFacade.addNotificationRequest(productCode, email);
		return "success";
	}



}
