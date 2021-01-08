
## Java Task 1

<b>Disk Space Analysis</b>
A company is performing an analysis on the computers at its main office, which are located in a single row. They perform the analysis in the following way:
1.	Choose a contiguous of a certain number of computers, starting from the beginning of the row.
2.	Analyze the available hard disk space on each of these computers.
3.	Determine the minimum available disk space within this segment.
After performing those steps for the first segment, they the repeat it for the next segment, continuing this procedure until the end of the row. (For example, if the segment size was 4, they would analyze computers 1 to 4, then 2 to 5, etc.) Given this analysis procedure, what is the maximum available disk space among all the minimums that are found during the analysis?

For example, let’s say there are <i>n = 3</i> computers, where space = <i>[8, 2, 4]</i>, and the length of analysis segments is <i>x = 2</i>. In this list of computers, the sublists of the size <i>2</i> would be <i>[8, 2]</i> and <i>[2, 4]</i>. Thus, the initial analysis would return <i>2</i> and <i>2</i> because those areithe minimums in the segments. Finally, the maximum of these values is <i>2</i>. Therefore, the answer is <i>2</i>.

<b>Function Description</b><br>
Complete the function <i>segment</i>.<br>
Segment has the following parameters:<br>
int <i>x</i>: the segment length for analyzing the row of computers<br>
List<Integer> <i>space</i>: a list of integers denoting the available hard disk space on each of the computers<br>
Returns:<br>
int: the maximum of the maximum values of available hard disk space found while analyzing the computers in segments of <i>numComps</i>

<b>Input format for Custom Testing</b><br>
The first line contains an integer, <i>x</i>.<br>
The second line contains an integer, <i>n</i>, denoting the size of the array space.<br>
Each line <i>i</i> of the <i>n</i> subsequent lines (where <i>0 <= i <= n</i>) contains an integer describing <i>space[i]</i>).<br>
<b>Simple Case 0</b><br>
<b>Sample Input For Custom Testing</b><br>
STDIN    Function<br>
-----    --------<br>
1     => x = 1<br>
5     => n = 5<br>
1     => space = [1, 2, 3, 1, 2]<br>
2<br>
3<br>
1<br>
2<br>
<b>Sample Output</b><br>
3<br>

<b>Explanation</b><br>
The sublists of size <i>x = 1</i> are <i>[1]</i>, <i>[2]</i>, <i>[3]</i>, <i>[1]</i>, and <i>[2]</i>. Because each sublist only contains <i>1</i> element, each value is minimal with respect to the sublist it’s in. The maximum of these values is <i>3</i>.<br>
Therefore, the answer is <i>3</i>.

<b>Sample Case 1</b><br>
<b>Sample Input For Custom Testing</b><br>
STDIN    Function<br>
-----    --------<br>
2     => x = 2<br>
3     => n = 3<br>
1     => space = [1, 1, 1]<br>
1<br>
1<br>
<b>Sample Output</b><br>
1<br>

<b>Explanation</b><br>
The sublists of size <i>x = 2</i> are <i>[1, 1]</i> and <i>[1, 1]</i>. The minimum value for both sublists is <i>1</i>. The maximum of these values is <i>1</i>. Therefore, the answer is <i>1</i>.

<b>Sample Case 2</b><br>
<b>Sample Input For Custom Testing</b><br>
STDIN    Function<br>
-----    --------<br>
3     => x = 3<br>
5     => n = 5<br>
2     => space = [2, 5, 4, 6, 8]<br>
5<br>
4<br>
6<br>
8<br>
<b>Sample Output</b><br>
4<br>

<b>Explanation</b><br>
The sublists of size <i>x = 3</i> are <i>[2, 5, 4]</i>, <i>[5, 4, 6]</i>, and <i>[4, 6, 8]</i>. The respective minimum values for the three sublists are <i>2</i>, <i>4</i> and <i>4</i>. The maximum of these values is <i>4</i>. Therefore, the answer is <i>4</i>.








