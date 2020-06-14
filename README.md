# ECommerce-site-with-Servlet-JSP

## This is a mock ECommerce website for selling basic computers, parts, and services. It is a JavaEE application based on Servlet 3.0
It has the following features:

* MySQL database
* Apache DataSource Realms
* Security Groups

### It contains an Admin view with separate login controlled by a security role and constraint.
This allows the administrator to:

* Create, Read, Update, Delete products
* Create, Read, Update, Delete users
* Create, Read, Update invoices


### Customer features include:

* View all items or based on category
* Select an item to get more information
* Add, Delete, Change number of items in cart with auto updated pricing
* Make purchases with credit card 
  * However this is not the proper way to handle customer credit card information, for more 
    information regarding the acceptance of debit/credit card payments checkout [this](https://www.expertmarket.com/credit-card-processing/laws-and-regulations)
    website for laws pertaining to this subject. 
  * [Here](https://www.merchantmaverick.com/best-online-credit-card-processing-companies/) is a list of third party credit payment processing services that can be used in a website
