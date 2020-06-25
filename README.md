# CSX42: Assignment 2
**Name:** Ashmeet kaur Chhabra

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [channelpopularity/src](./channelpopularity/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile channelpopularity/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile channelpopularity/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile channelpopularity/src/build.xml run -Dinput="input.txt" -Doutput="output.txt" -Dmetrics="metrics.txt"
```
Note: Arguments accept the absolute path of the files.


## Description:

Classes: 
1. Driver.java
2. ChannelContext.java: Cintext Classes
3. States: Unpopular.java, Mildypopular.java, Highlypopular.java, Ultrapopular.java
4. SimpleStateFactory.java
5. FileProcessor.java
6. LineHandler.java
7. Results.java

Interfaces:
1. ContextI.java
2. StateI.java
3. SimpleStateFactoryI.java
4. FileDisplayInterface.java
5. StdoutDisplayInterface.java

AbstractClass:
1. AbstractState.java

Enums:
1. StateName.java
2. Operation.java
3. OperationArgs.java


1. In this assignment I have implemented a YouTube Channel using State and Simple Factory Pattern. 
2. There are four states: Unpopular, mildlypopular, highly popular and ultra popular.
3. The channel is maintained by the following steps:
	a. First the video is added in the channel
	b. Then the popularity score is calculated of the video according to the given metrics.
	c. Metrics of the video contains numOfViews, numOfLikes, numOfDislikes.
	d. Formula for calculating Popularity score is: (#Views + 2 * (#Likes - #Dislikes))/numOfVideos
	e. After Calculating the Average Popularity score the decimal places are truncated before printing   them to a file and to the console.
	f. The catergorization of states according to the popularity score is as follows:
		i. UNPOPULAR - range [0, 1000]
		ii. MILDLY_POPULAR -range (1000, 10000]
		iii. HIGHLY_POPULAR -range (10000, 100000]
		iv. ULTRA_POPULAR -range (100000, INT_MAX]
	g. UNPOPULAR is the initial state.
	h. Then there will be addRequest with the length of the advertisement.
	i. The Advertisement is approved or Rejected according to the states and the length of Add.
	j. Categorization is as follows:
		i. state=UNPOPULAR, advertisements of length in range (1,10]
		ii. state=MILDLY_POPULAR, advertisements of length in range (1, 20]
		iii. state=HIGHLY_POPULAR, advertisements of length in range (1, 30]
		iv. state=ULTRA_POPULAR, advertisements of length in range (1, 40]
	k. At last the video is removed from the Channel.
	

## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 24 June 2020


