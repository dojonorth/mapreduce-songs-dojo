# Scala source code

The only dependency for compiling and running this project is the Scala Build Tool (sbt). 
On a Mac, you can install with Homebrew as follows:

```
brew install sbt
```

### Warmup exercise - running tests
To run the tests and ensure you've implemented the functions correctly, you can run the following in the command line:

```
sbt "test-only bbc.dojonorth.mapreduce.exercise1.*"
```

## Lyrics data - reading in a dataset file

To read one of the dataset text files, you can use the ReadTestData app:

```
sbt "run-main bbc.dojonorth.lyrics.ReadTestData path/to/mxm_dataset_test.txt"
```


### Running all tests

To run the full set of tests, execute the following:

```
sbt test
```
