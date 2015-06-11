var _ = require('lodash');
var fs = require('fs');

var MusixMatchSongMatcher = function MusixMatchSongMatcher () {};

MusixMatchSongMatcher.prototype.loadDataFile = function (filename) {
    var lines = fs.readFileSync(filename, {encoding: 'utf8'}).split('\n');

    return lines.filter(function(line) {
        return line.indexOf('#') !== 0 && line.indexOf('TR') !== -1;
    });
};

MusixMatchSongMatcher.prototype.parseMsdTrackLine = function (line) {
    var splitLine = line.split('<SEP>');

    return {
        trackId: splitLine[0],
        mxmId: parseInt(splitLine[3], 10),
        artist: splitLine[1],
        title: splitLine[2]
    }
};

MusixMatchSongMatcher.prototype.loadSongMetaData = function (filename) {
    var trackLines = this.loadDataFile(filename);
    var parsedTracks = {};

    _.each(trackLines, function (line) {
        var track = this.parseMsdTrackLine(line);
        parsedTracks[track.trackId] = track;
    }.bind(this));

    return parsedTracks;
};

module.exports = MusixMatchSongMatcher;