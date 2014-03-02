##Restaurant Ordering System##

####This project consists of two modules:####
1. OrderClient
2. OrderServer


OrderClient is a client side module. It's main purpose is to mange the orders. Users can create new order, add products and save changes. It communicates over the network with OrderServer and uses MySQL database to store orders.

OrderServer is a server side module. It's main purpose is to listen to upcomming tasks and display them. It allows to set already prepared tasks. It communicates over the network with OrderClient.

cafeteria.sql is an SQL script to import a mysql database.
