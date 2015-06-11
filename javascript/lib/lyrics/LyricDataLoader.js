var _ = require('lodash');
var fs = require('fs');

var LyricDataLoader = function LyricDataLoader () {};

LyricDataLoader.prototype.loadDataFile = function (filename) {
    var lines = fs.readFileSync(filename, {encoding: 'utf8'}).split('\n');

    return lines.filter(function(line) {
        return line.indexOf('#') !== 0;
    });
};

LyricDataLoader.prototype.parseWordsLine = function (wordsLine) {
    return wordsLine.substr(1).split(',').filter(function(n) {return n.length}).map(function(n) {return n.toLowerCase()})
};

LyricDataLoader.prototype.parseTrackLine = function (trackLine, words) {
    var splitLine = trackLine.split(',');
    var trackId = splitLine[0];
    var mxmId = parseInt(splitLine[1], 10);
    var wordMap = {};

    _.each(_.drop(splitLine, 2), function(wc) {
        var idAndCount = wc.split(':');
        wordMap[words[parseInt(idAndCount[0], 10) - 1]] = parseInt(idAndCount[1], 10);
    });

    return {
        trackId: trackId,
        mxmId: mxmId,
        wordMap: wordMap
    }
};

LyricDataLoader.prototype.parseLinesToSongs = function (lines, words) {
    return _.map(lines, function(line) {
        return this.parseTrackLine(line, words);
    }.bind(this))
};

LyricDataLoader.prototype.load = function (filename) {
    var lines = this.loadDataFile(filename);

    var wordsLine = lines.filter(function (line) {return line.indexOf('%') === 0;})[0];
    var songLines = lines.filter(function (line) {return line.indexOf('%') !== 0;});

    var words = this.parseWordsLine(wordsLine);
    var songs = this.parseLinesToSongs(songLines, words);

    return {words: words, songs: songs};
};

module.exports = LyricDataLoader;