import Toybox.Application;
import Toybox.Lang;
import Toybox.WatchUi;

class Team {

    private var points as Integer = 0;

    function initialize() {
    }

    function touchDown() as Void {
        self.scores(6);
    }

    function scoresOnePoint() as Void {
        self.scores(1);
    }

    function scoresTwoPoints() as Void {
        self.scores(2);
    }

    private function scores(by as Integer) as Void {
        self.points += by;
    }

    function setScore(hundreds as Integer, tens as Integer, ones as Integer) as Void {
        self.points = hundreds * 100 + tens * 10 + ones;
    }

    function getPoints() as Integer {
        return self.points;
    }

}
