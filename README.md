# demo-training
 This repo is specific for Abandoned cart Email, Boost Products with stock in PLP, Back in Stock email feature for project demo purpose only
 
 * Abandoned cart Email:
    * AbandonedCartMailNotificationJob is created to sent email to the customer
* Boost Products with stock in PLP:
    * We will use Boost rule , Boost type "Multiplicative" , "inStockFlag" as index Property and boost should be less than 1(e.g 0.02) and It make the product magic score very less so product will come in the last of search result list. https://help.sap.com/viewer/9d346683b0084da2938be8a285c0c27a/1905/en-US/c3ae9c7c48834db3b7465e26291761b6.html
* Back in Stock email:
    * Approch 1: TrainingBackInStockEMailNotificationJob , NotificationRequest as new Item with product code, email, notified attibute.
     we have OOB stock out of stck setup in storefront, we should create a form on click of out of stock item , it hosuld have one text box to fill the email and through TrainingStockNotificationPageController we can get that data and save into NotificationRequestModel. after that we can run cron job TrainingBackInStockEMailNotificationJob evry hour or every day and sent notification and after sending email mark NotificationRequest as notified with true.So that It won't send duplicate notification.
    * Approch 2: we have stocknotificationservices extension and we can use Stocknotificationaddon addon and OOB existing StockLevelStatusJob


