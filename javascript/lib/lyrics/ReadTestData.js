#!/usr/bin/env node

/**
 * Just to make sure the test data loads properly...
 * This main class takes the path to your mxm_dataset_test or mxm_dataset_train file
 */

var _ = require('lodash');
var LyricDataLoader = require('./LyricDataLoader');

var loader = new LyricDataLoader();
var args = process.argv;

if (args.length != 3) {
    console.log('Usage: ./ReadTestData.js <path to an MXM file>');
} else {
    console.time('load');
    songData = loader.load(args[2]);
    console.timeEnd('load');

    console.log();
    console.log('First five track IDs:');


    console.time('process songs');
    _.each(songData.songs.slice(0, 5), function (song) {console.log(song.trackId)});
    console.timeEnd('process songs');

    console.log();
    console.log('First five words in words list:');

    console.time('process words');
    _.each(songData.words.slice(0, 5), console.log);
    console.timeEnd('process words');
}
