
# SQL Task 4

## Orders Query
Company X has a record of its customers and their orders. Find the customer(s) with the highest order price for orders placed within 10 years of the first order (earliest order_date) in the database. Print the customer name and order price. If multiple records are returned, they can be in any order.<br>

<b>Schema</b>
There are 2 tables: CUSTOMERS, ORDERS.<br>

<table>
<tbody>
<tr>
<td style="text-align: center; vertical-align: middle;">
<p><b>CUSTOMERS</b></p>
</td>
</tr>
<tr>
<td style="text-align: center; vertical-align: middle;">
<p>ID</p>
</td>
<td style="text-align: center; vertical-align: middle;">
<p>STRING</p>
</td>
<td style="text-align: center; vertical-align: middle;">
<p>ID of the customer. This is the primary key.</p>
</td>
</tr>
<tr>
<td style="text-align: center; vertical-align: middle;">
<p>NAME</p>
</td>
<td style="text-align: center; vertical-align: middle;">
<p>STRING</p>
</td>
<td style="text-align: center; vertical-align: middle;">
<p>Name of the customer.</p>
</td>
</tr>
<tr>
<td style="text-align: center; vertical-align: middle;">
<p>ORDER_ID</p>
</td>
<td style="text-align: center; vertical-align: middle;">
<p>STRING</p>
</td>
<td style="text-align: center; vertical-align: middle;">
<p>ID of the customer's order.</p>
</td>
</tr>
</tbody>
</table>

<table>
<tbody>
<tr>
<td style="text-align: center; vertical-align: middle;">
<p><b>ORDERS</b></p>
</td>
</tr>
<tr>
<td style="text-align: center; vertical-align: middle;">
<p>ID</p>
</td>
<td style="text-align: center; vertical-align: middle;">
<p>STRING</p>
</td>
<td style="text-align: center; vertical-align: middle;">
<p>ID of the order.</p>
</td>
</tr>
<tr>
<td style="text-align: center; vertical-align: middle;">
<p>PRICE</p>
</td>
<td style="text-align: center; vertical-align: middle;">
<p>INTEGER</p>
</td>
<td style="text-align: center; vertical-align: middle;">
<p>Price of the order.</p>
</td>
</tr>
<tr>
<td style="text-align: center; vertical-align: middle;">
<p>ORDER_DATE</p>
</td>
<td style="text-align: center; vertical-align: middle;">
<p>DATE</p>
</td>
<td style="text-align: center; vertical-align: middle;">
<p>Date of the order.</p>
</td>
</tr>
</tbody>
</table>

## Sample Data Tables
<b>Sample Input</b>

<table>
<tbody>
<tr>
<td style="text-align: center; vertical-align: middle;">
<p><b>CUSTOMERS</b></p>
</td>
</tr>
<tr>
<td style="text-align: center; vertical-align: middle;">
<p>0a66e2bba8d5401b8d707ad9fc35394a</p>
</td>
<td style="text-align: center; vertical-align: middle;">
<p>Jennifer Palmer</p>
</td>
<td style="text-align: center; vertical-align: middle;">
<p>3223c7dfda384470aed77b1db46fe29b</p>
</td>
</tr>
<tr>
<td style="text-align: center; vertical-align: middle;">
<p>57bc0e2554ea4ddI910blIeel131f4cb</p>
</td>
<td style="text-align: center; vertical-align: middle;">
<p>Susan Gonzalez</p>
</td>
<td style="text-align: center; vertical-align: middle;">
<p>eb173d2bc0214cfc8727c00fe62ac96d</p>
</td>
</tr>
<tr>
<td style="text-align: center; vertical-align: middle;">
<p>d880a48bf4844c99a3bafac53612119e</p>
</td>
<td style="text-align: center; vertical-align: middle;">
<p>Judith Smith</p>
</td>
<td style="text-align: center; vertical-align: middle;">
<p>65b36940385a40fcaa13ecb9c8394150</p>
</td>
</tr>
</tbody>
</table>

<table>
<tbody>
<tr>
<td style="text-align: center; vertical-align: middle;">
<p><b>ORDERS</b></p>
</td>
</tr>
<tr>
<td style="text-align: center; vertical-align: middle;">
<p>3223c7dfda384470aed77b1db46fe29b</p>
</td>
<td style="text-align: center; vertical-align: middle;">
<p>100</p>
</td>
<td style="text-align: center; vertical-align: middle;">
<p>1987-10-02</p>
</td>
</tr>
<tr>
<td style="text-align: center; vertical-align: middle;">
<p>65b36940385a40fcaa13ecb9c8394150</p>
</td>
<td style="text-align: center; vertical-align: middle;">
<p>5</p>
</td>
<td style="text-align: center; vertical-align: middle;">
<p>1987-08-01</p>
</td>
</tr>
<tr>
<td style="text-align: center; vertical-align: middle;">
<p>eb173d2bc0214cfc8727c00fe62ac96d</p>
</td>
<td style="text-align: center; vertical-align: middle;">
<p>500</p>
</td>
<td style="text-align: center; vertical-align: middle;">
<p>1998-08-01</p>
</td>
</tr>
</tbody>
</table>


<b>Sample Output</b><br>
```
Jennifer Palmer 100
```

