import Toybox.Lang;

class Scores {

    private var pointsA as Integer = 0;

    function initialize() {
    }

    function touchDownA() as Void {
        self.teamAScores(6);
    }

    function teamAScoresOnePoint() as Void {
        self.teamAScores(1);
    }

    function teamAScoresTwoPoints() as Void {
        self.teamAScores(2);
    }

    private function teamAScores(by as Integer) as Void {
        self.pointsA += by;
    }

    function getPointsA() as Integer {
        return self.pointsA;
    }
    function teamASetScore(hundreds as Integer, tens as Integer, ones as Integer) as Void {
        self.pointsA = 17;
    }
}