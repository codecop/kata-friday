import Toybox.Lang;

class Scores {

    private var pointsA as Integer = 0;

    function initialize() {
    }

    function touchDownA() as Void {
        self.pointsA += 6;
    }

    function teamAScoresOnePoint() as Void {
        self.pointsA +=1;
    }

    function teamAScoresTwoPoints() as Void {
        self.pointsA +=2;
    }

    function getPointsA() as Integer {
        return self.pointsA;
    }
}