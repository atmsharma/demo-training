# demo-training
 This repo is specific for Abandoned cart Email, Boost Products with stock in PLP, Back in Stock email features for project demo/discussion purpose only.
 
 * **Abandoned cart Email:**
    * AbandonedCartMailNotificationJob(https://github.com/atmsharma/demo-training/blob/main/trainingcore/src/com/hybris/training/core/cronjob/AbandonedCartMailNotificationJob.java) is created to sent email to the customer.
    
* **Boost Products with stock in PLP:**
    * We will use Boost rule , Boost type "Multiplicative" , "inStockFlag" as index Property and boost should be less than 1(e.g 0.02) and It make the product magic score very less so product will come in the last of search result list.
    * Please find attached screenshot https://github.com/atmsharma/demo-training/blob/main/Boost%20Products%20with%20stock%20in%20PLP.png

* **Back in Stock email:**
    * **Approch 1:** TrainingBackInStockEMailNotificationJob (https://github.com/atmsharma/demo-training/blob/main/trainingcore/src/com/hybris/training/core/cronjob/TrainingBackInStockEMailNotificationJob.java), NotificationRequest as new Item with product code, email, notified attibute.
     we have OOTB "stock out of stock" setup in storefront, we should create a form on click of out of stock item button on PDP/PLP , it should have one text box to fill the email and through TrainingStockNotificationPageController we can get that data and save into **NotificationRequestModel**. after that we can run cron job **TrainingBackInStockEMailNotificationJob** every hour or every day and sent notification and after sending email mark NotificationRequest as notified with true.So that It won't send duplicate notification.
    * **Approch 2:**  The stock notification feature requires the following extensions to work stocknotificationservices extension, Stocknotificationaddon AddOn, customerinterestoccaddon AddOn addon install on storefront and OOB existing **StockLevelStatusJob** 


