# mapreduce-songs-dojo

## Warmup exercise

This exercise is an introduction to the general idea of decomposing a problem
into map and reduce stages - if you are familiar with higher order functions
and functional programming it will probably be very easy!

Implement a solution to the following problems using an iterative solution:
- Given a list of integers, return the sum
- Given a list of integers, return the sum of their squares
- Given a list of integers, return the product of double their squares

*Note: there are tests and stub implementations in the bbc.dojonorth.mapreduce package*

These problems can be all be broken into 2 distinct parts: First, the input elements can be transformed. Second, the transformed elements are combined into a result.

Try to reimplement your 3 solutions using the Scala List class's ```map``` and ```reduce```
functions for these 2 stages. 

Can you implement the higher order function ```mapReduce``` for all 3
problems? (N.B. a higher order function is one which takes functions as arguments)


## Getting into the swing of it
In these exercises you will apply the same principles as before with a bigger
set of data - in this case a sample of data from the million song database.


### Getting the songs data

The song lyrics data is provided by Columbia University at:
http://labrosa.ee.columbia.edu/millionsong/musixmatch

There are 2 text files containing the data we need.

If you have WGET installed, you can easily download the zip files with the
following commands:

```
wget http://labrosa.ee.columbia.edu/millionsong/sites/default/files/AdditionalFiles/mxm_dataset_train.txt.zip
wget http://labrosa.ee.columbia.edu/millionsong/sites/default/files/AdditionalFiles/mxm_dataset_test.txt.zip
```

Unzip these files somewhere in the project directory structure.

Each file contains a list of (stemmed) words, followed by a list of track
information. This consists of a Track ID, a musiXmatch ID and a list of word
counts (words are indexed starting from 1).

Code is provided to load these files into Scala streams. You can also read them
into a SQLite database, which will fill up to around 2GB with indexes!

### Task 1: Compute the total number of words
TODO: write exercise and backend API
