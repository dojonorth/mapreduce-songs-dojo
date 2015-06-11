var assert = require('assert');
var _ = require('lodash');
var MusixMatchSongMatcher = require('../../lib/lyrics/MusixMatchSongMatcher');

describe('MusixMatchSongMatcher', function () {
    var musixMatchSongMatcher, fixturesDir, testFile;
    before(function () {
        musixMatchSongMatcher = new MusixMatchSongMatcher();
        fixturesDir = './test/resources';
        testFile = fixturesDir + '/mxm_sample.txt';
    });

    describe('#loadDataFile()', function () {
        it('should ignore comment lines', function () {
            var lines = musixMatchSongMatcher.loadDataFile(testFile);
            var commentedLines = _.filter(lines, function(line) {
                return line.indexOf('#') === 0;
            });

            assert.equal(commentedLines.length, 0);
        });

        it('should give an iterator of strings containing <SEP>', function () {
            var lines = musixMatchSongMatcher.loadDataFile(testFile);
            _.each(lines, function (line) {
                assert.notEqual(-1, line.indexOf('<SEP>'));
            });
        });
    });

    var exampleTrack = {trackId: "TRMMMKD128F425225D", mxmId: 4418550, artist: "Karkkiautomaatti", title: "Tanssi vaan"};
    describe('#parseMsdTrackLine()', function () {

        it('should parse lines beginning with TR into song descriptors', function() {
            var exampleLine = 'TRMMMKD128F425225D<SEP>Karkkiautomaatti<SEP>Tanssi vaan<SEP>4418550<SEP>Karkkiautomaatti<SEP>Tanssi vaan';
            var parsed = musixMatchSongMatcher.parseMsdTrackLine(exampleLine);

            assert.deepEqual(exampleTrack, parsed);
        });
    });

    describe('#loadSongMetaData()', function () {
        it('should parse all lines into track data', function() {
            var metadata = musixMatchSongMatcher.loadSongMetaData(testFile);
            assert.equal(4, Object.keys(metadata).length);

            assert.deepEqual(exampleTrack, metadata[exampleTrack.trackId]);
        });
    });
});