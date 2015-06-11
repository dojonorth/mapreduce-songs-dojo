var assert = require('assert');
var _ = require('lodash');
var LyricDataLoader = require('../../lib/lyrics/LyricDataLoader');

describe('LyricDataLoader', function () {
    var lyricDataLoader, fixturesDir, testFile;
    before(function () {
        lyricDataLoader = new LyricDataLoader();
        fixturesDir = './test/resources';
        testFile = fixturesDir + '/lyricdata_sample.txt';
    });

    describe('#loadDataFile()', function () {

        it('should ignore comment lines', function () {
            var lines = lyricDataLoader.loadDataFile(testFile);
            var commentedLines = _.filter(lines, function(line) {
                return line.indexOf('#') === 0;
            });

            assert.equal(commentedLines.length, 0);
        });

        it('should contain the wordlist in first element', function () {
            var lines = lyricDataLoader.loadDataFile(testFile);
            var firstElement = lines[0];

            assert.equal(0, firstElement.indexOf('%'));
            assert.notEqual(-1, firstElement.indexOf('the'));
            assert.notEqual(-1, firstElement.indexOf('love'));
        });

        it('should contain a track sample in second element', function () {
            var lines = lyricDataLoader.loadDataFile(testFile);
            var secondElement = lines[1];

            assert.equal(0, secondElement.indexOf('TRA'));
            assert.notEqual(-1, secondElement.indexOf('1548880'));
        });
    });

    describe('#parseWordsLine()', function () {
        var wordsLine = "%i,the,you,to,and,a,Me,it,not,in,my,is,of,your,";

        it('should produce words from the string', function () {
            var parsedLine = lyricDataLoader.parseWordsLine(wordsLine);

            assert.equal(-1, parsedLine.indexOf('%i'));
            assert.notEqual(-1, parsedLine.indexOf('i'));
            assert(parsedLine.indexOf('of') < parsedLine.indexOf('your'));
            assert.equal(14, parsedLine.length);
        });

        it('should produce lowercase only', function () {
            var parsedLine = lyricDataLoader.parseWordsLine(wordsLine);

            _.each(parsedLine, function(word) {
                assert.equal(word, word.toLowerCase());
            });

        });
    });

    describe('#parseTrackLine()', function () {
        it('should extract the track ID, the MusixMatch ID and a map of word ID to count', function () {
            var lines = lyricDataLoader.loadDataFile(testFile);
            var words = lyricDataLoader.parseWordsLine(lines[0]);

            var getWordForIndex = function (n) {
                return words[n-1];
            };

            var parsedTrack = lyricDataLoader.parseTrackLine(lines[1], words);

            assert.equal('TRAABRX12903CC4816', parsedTrack.trackId);
            assert.equal('1548880', parsedTrack.mxmId);
            assert.equal(19, parsedTrack.wordMap[getWordForIndex(2)]);
            assert.equal(11, parsedTrack.wordMap[getWordForIndex(30)]);
        });
    });

    describe('#load', function () {
        it('should produce 2 songs from the test file', function () {
            var dummySong = {
                trackId: 'TRAAMYTEST',
                mxmId: 1000,
                wordMap: {
                    'the': 19,
                    'to': 7,
                    'and': 6
                }
            };

            var songs = lyricDataLoader.load(testFile).songs

            assert.equal(2, songs.length);
            assert.deepEqual(dummySong, songs[1]);
        });
    });
});
