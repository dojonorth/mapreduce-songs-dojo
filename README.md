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

There are 3 text files containing the data we need.

If you have WGET installed, you can easily download the zip files with the
following commands:

```
wget http://labrosa.ee.columbia.edu/millionsong/sites/default/files/AdditionalFiles/mxm_dataset_train.txt.zip
wget http://labrosa.ee.columbia.edu/millionsong/sites/default/files/AdditionalFiles/mxm_dataset_test.txt.zip
wget http://labrosa.ee.columbia.edu/millionsong/sites/default/files/AdditionalFiles/mxm_779k_matches.txt.zip
```

Unzip these files somewhere in the project directory structure.

The first 2 files contain lists of (stemmed) words, followed by a list of track
information. This consists of a Million Song Database Track ID, a musiXmatch ID and a list of word
counts (words are indexed starting from 1).
The 3rd file maps Million Song Database track IDs to metadata from the
MusixMatch database. Specifically, we can get the artist and song titles - this
will be useful for displaying results.

Code is provided to load these files into Scala objects. You can also read them
into a SQLite database, which will fill up to around 2GB with indexes!

### Task 1: Compute the total number of words
With the LoadLyricData code, you have a method to get a list of tracks, each with a dictionary mapping words (strings) to their respective counts.

1. Write a map function which takes a track and produces the dictionary of word counts.

2. Write a reduce function which takes 2 dictionaries of word counts and merges them to a single dictionary.

3. Combine the map and reduce functions to get the total word count for the songs in the Test dataset. Try to find the top 10 words by count.

### Task 2: Grep
Your task now is to write a function which finds all songs containing a given word.

1. Write a map function which takes a track and returns the Track ID and Boolean reflecting whether the track contains the specified word.

2. Write a reduce function which takes a list of Track IDs and a single (Track ID, Boolean) pair. It should add the Track ID to the list if the Boolean is true.

3. Use the map and reduce functions to define a grep function.

### Task 3: Reverse dictionary
Write map and reduce functions which enable you to create a reverse dictionary of words to songs containing them.
Compare this to the Grep function you wrote before. Is it better to use the reverse dictionary for searching?

### Task 4 ???
