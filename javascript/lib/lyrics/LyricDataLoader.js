var fs = require('fs');

var LyricDataLoader = function LyricDataLoader() {};

LyricDataLoader.prototype.loadDataFile = function(filename) {
    var lines = fs.readFileSync(filename, {encoding: 'utf8'}).split('\n');

    return lines.filter(function(line) {
        return line.indexOf('#') !== 0;
    });
};

module.exports = LyricDataLoader;