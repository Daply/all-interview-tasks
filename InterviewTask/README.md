# InterviewTask

## Implement simple tool for logs analysis

There is directory that contains multiple log files. The number of files can be big and the number of lines in one log can be big.

Each log record contains at least time, username and custom message. Exact format is up to you.
Input parameters:<br>
Filter parameters - at least one parameter should be specified.<br>
●	Username<br>
●	Time period<br>
●	Pattern for custom message<br>
Grouping parameters (Note: it is not filtering or sorting! Grouping parameters are used only for aggregate statistics. See “Output” for example) - at least one parameter should be specified<br>
●	Username<br>
●	Time unit (e.g. 1 hour, 1 day, 1 month)<br>
Other parameters<br>
●	Count of threads used to process files. Each file can be processed in separate thread. (default=1)<br>
●	Path or filename to output file<br>
Tool should scan directory for log files, read log files and filter log records that conform to user input and produce output given below.<br>
Output<br>
●	Single file with all filtered log records<br>
●	Print aggregate statistics - Count of records grouped by grouping input parameters.<br>
For example with aggregation by 1 day:

|      Day      | Count of records |
| ------------- | ---------------- |
|   2017/06/01  |        12        |
|   2017/06/02  |        42        |
|   2017/06/03  |       212        |

Additional requirements<br>
●	Put your project to github, or other git hosting<br>
●	Use maven or gradle for build<br>
●	If you have any examples of your code that hosted somewhere - please send link as well<br>

What is taken into account at task review<br>
●	Whether tool is working according to specification<br>
●	Code quality, including usage of patterns<br>
●	Usage of latest java se features
