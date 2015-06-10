var assert = require('assert');
var _ = require('lodash');
var LyricDataLoader = require('../../lib/lyrics/LyricDataLoader');

describe('LyricDataLoader', function () {
    describe('#loadDataFile()', function () {
        it('should ignore comment lines', function () {
            var lyricDataLoader = new LyricDataLoader();
            var fixturesDir = './test/resources';
            var testFile = fixturesDir + '/lyricdata_sample.txt';

            var lines = lyricDataLoader.loadDataFile(testFile);
            var commentedLines = _.filter(lines, function(line) {
                return line.indexOf('#') === 0;
            });

            assert.equal(commentedLines.length, 0);
        });
    });
});
