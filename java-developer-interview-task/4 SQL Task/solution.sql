SELECT c.NAME, o.PRICE FROM CUSTOMERS c
                       JOIN ORDERS o ON c.ORDER_ID=o.ID
                       JOIN (SELECT o1.ID, MIN(o1.ORDER_DATE) AS oDATE 
                             FROM ORDERS o1
                             GROUP BY o1.ID) MAXDATE
                       ON MAXDATE.ID=o.ID
                       WHERE o.PRICE = (SELECT MAX(o1.PRICE) FROM ORDERS o1)
                       AND o.ORDER_DATE 
                            BETWEEN MAXDATE.oDATE
                        and
                            DATE_ADD(MAXDATE.oDATE, INTERVAL 10 YEAR);
							
SELECT c.NAME, o.PRICE FROM CUSTOMERS c
                       JOIN ORDERS o ON c.ORDER_ID=o.ID
                       JOIN (SELECT o1.ID, MIN(o1.ORDER_DATE) AS oDATE 
                             FROM ORDERS o1
                             GROUP BY o1.ID) MAXDATE
                       ON MAXDATE.ID=o.ID
                       WHERE o.PRICE = (SELECT MAX(o1.PRICE) FROM ORDERS o1)
                       AND o.ORDER_DATE >= MAXDATE.oDATE
                       AND o.ORDER_DATE <= DATE_ADD(MAXDATE.oDATE, INTERVAL 10 YEAR);
							
							
select c.name, o.price from customers c
       join orders o on c.order_id = o.id
       where o.price = (select MAX(o1.price)
       from orders o1
       where o1.order_date <= 
          (select 
              DATE_ADD(MIN(o2.order_date), INTERVAL 10 YEAR) as maxdate 
           from orders o2));
		   
		   
		   
		   
		   Chelsea Walter 911 
Kristin Potter 911 