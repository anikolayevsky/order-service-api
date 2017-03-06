# order-service-api
[B]lck.ly ordering services

#Setup
To setup, you will need to install a postgres instance with a new schema called "lv_order" with owner to a user blckly.  By default (in application.properties) a connection will be made to:

spring.datasource.url=jdbc:postgresql://localhost:5432/blckly

spring.datasource.username=blckly

spring.datasource.password=blcklypw


#Usages
[B]lck.ly provides 4 endpoints for kit management.

#Order
An order is the wrapper around order line items.  This is used to generate id's for payment, and to store overall statuses (can have partial delivery)

##Order Statuses
---
    INCOMPLETE,
    PROCESSING,
    PARTIALLY_DELIVERED,
    DELIVERED,
    CANCELLED
---

###GET
Method used for finding orders.  Must pass in a requestor.  Optional orderStatus can be passed in.

/order?requestorId={requestor}&status={orderStatus}

Returns Order[]

###GET
Method used for fetching an order resource.

/order/{id}

Returns Order

###POST
Method used for creation of a new order.

/order

Returns Order

###UPDATE
Method used to update an order.

/order/{id}

Returns Order

#Order Lines

Order Lines contain delivery information for a customer and kits that are to be ordered.

##OrderLine Statuses
---
    PENDING,
    BUILDING,
    IN_PROCESS,
    DELIVERED,
    CANCELLED
---

###GET
Method used for finding orders.  Must pass at least one parameter.  Parameters are orderId, customerId, status

/order/orderLine?orderId={orderId}&customerId={customerId}&status={orderLineStatus}

Returns OrderLines[]

###GET
Method used for fetching an orderLine resource.

/order/orderLine/{id}

Returns OrderLine

###POST
Method used for creation of multiple order lines.

/order/orderLine

Returns OrderLines[]

###UPDATE
Method used to update an orderLine.

/order/orderLine/{id}

Returns OrderLine
