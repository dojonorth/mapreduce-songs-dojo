var assert = require("assert");
var exercise1Implementation = require('../lib/warmUp');

describe('exercise1Implementation', function () {
    describe('#sumOfArray()', function () {
        it('should return 0 for empty array', function () {
            assert.equal(0, exercise1Implementation.sumOfArray([]));
        });

        it('should return 10 for [1,2,3,4]', function () {
            assert.equal(10, exercise1Implementation.sumOfArray([1, 2, 3, 4]));
        });
    });

    describe('#sumOfSquaresOfArray()', function () {
        it('should return 14 for [1,2,3]', function () {
            assert.equal(14, exercise1Implementation.sumOfSquaresOfArray([1, 2, 3]));
        });
    });

    describe('#productOfDoubleOfSquaresOfList()', function () {
        it('should return 288 for [1,2,3]', function () {
            assert.equal(288, exercise1Implementation.productOfDoubleOfSquaresOfList([1, 2, 3]));
        });

        it('should work with an empty array', function () {
            assert.equal(0, exercise1Implementation.productOfDoubleOfSquaresOfList([]));
        });
    });

    describe('#mapReduce()', function () {
        it('take a simple map and reduce function and apply them to a list of integers correctly', function() {
            var testMapFn = function(x) { return x * 2; };
            var testReduceFn = function(x, y) { return (x * 2) + y; };
            var testArray = [1, 2, 3, 4];

            assert.equal(52, exercise1Implementation.mapReduce(testMapFn, testReduceFn, testArray));
        })
    });
});
