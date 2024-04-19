import Toybox.Lang;

class Scores {

    private var pointsA as Integer = 0;

    function initialize() {
    }

    function touchDownA() as Void {
        self.pointsA += 6;
    }

    function teamAGoesForPointAfterTouchDown(targetPoints as Integer) as Void {
        self.pointsA += targetPoints;
    }

    function getPointsA() as Integer {
        return self.pointsA;
    }
}