
# Java Task 5

## Stock market prediction

In this prediction game, first player gives the second player some stock market data for some consecutive days The data contains a company's stock price on each day. The rules for the game are:<br>
- Player <i>1</i> will tell player <i>2</i> a day number
- Player <i>2</i> has to find the nearest day on which stock price is smaller than the given day
- If there are two results, then player <i>2</i> finds the day number is smaller
- If no such day exists, then the answer is -1. 


For example, the image below shows the stock market data for <i>10</i> consecutive days. The horizontal axis represents the day number, starting at 1, and the axis price on that day.<br>

![Stock Market Data](https://github.com/Daply/all-interview-tasks/blob/master/java-developer-interview-task/5%20Java%20Task/stock-market-data.png)<br>

<b>Example</b><br>
<i>n = 10</i><br>
<i>stockData = [5, 6, 8, 4, 9, 10, 8, 3, 6, 4]</i><br>
<i>queries = [6, 5, 4]</i><br>

On day 6, the stock price is 10. Both 9 and 8 are lower prices one day away. Choose 9 (day 5) because it is before day 6.<br>
On day 5, the stock price is 9. 4 is the closest price on day 4.<br>
On day 4, the stock price is 4. The only price is on day 8.<br>
The return array is [5, 4, 8].<br>


## Function Description
Complete the <i>predictAnswer</i> function.<br>

<i>predictAnswer</i> has 2 parameters:<br>
<i>int stockData[n]</i>: the value Of each <i>stockData[i]</i> is the stock price on the i+1<sup>th</sup> day (where <i>0 <= i < n</i>).<br>
<i>int queries[q]</i>: the value Of each element <i>queries[j]</i>. iS the day number given in the query (where <i>0 <= j < q</i>).<br>

<b>Return</b><br>
<i>int[q]</i>: the value at each index i is the answer to <i>queries[i]</i>

<b>Constraints</b><br>
- <i>1 <= n <= 10<sup>5</sup></i>
- <i>1 <= stockData[i] <= 10<sup>9</sup></i>
- <i>1 <= q <= 10<sup>5</sup></i>
- <i>1 <= queries[j] <= 10<sup>9</sup></i>

<b>Input Format For Custom Testing</b><br>
Locked stub code in the editor reads the follcming input from stdin and passes it to the function.<br>

The first line contains an integer, <i>n</i>, denoting the number of elements in stockData.<br>
Each line <i>i<sup>th</sup></i> of the <i>n</i> subsequent lines contains an integer, <i>stockData[i]</i>, the stock price on the <i>i+1<sup>th</sup></i> day.
Next line contains an integer, <i>q</i>, the number of elements in <i>queries</i>.<br>
Each line <i>j<sup>th</sup></i> of the <i>q</i> subsequent lines contains an integer, <i>queries[j]</i> the day number of the <i>j<sup>th</sup></i> query.<br>

<b>Sample Case 0</b><br>
<b>Sample Input 0</b><br>
STDIN    Function<br>
-----    --------<br>
10    => stockData[] size n = 10<br>
5     => stockData = [5, 6, 8, 4, 9, 10, 8, 3, 6, 4]<br>
6<br>
8<br>
4<br>
9<br>
10<br>
8<br>
3<br>
6<br>
4<br>
3     => queries[] size q = 3<br> 
3     => queries = [3, 1, 8]<br>
1<br>
8<br>

<b>Sample Output 0</b><br>
2<br>
4<br>
-1<br>

<b>Explanation</b><br>
- If the day number is <i>3</i>, both days 2 and 4 are smaller. Choose the earlier day, day 2.
- If the day number is <i>1</i>, day 4 is the closest day wth a smaller price.
- If the day number is <i>8</i>, there is no day where the price is less than 3. The answer is -1.
- The return array is [2, 4, -1].






























