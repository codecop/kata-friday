import Toybox.Application;
import Toybox.Lang;
import Toybox.WatchUi;

class Scores {

    private var pointsA = 0;
    function initialize() {
    }

    function touchDownA() as Void {
       self.pointsA += 6;
    }

    function getPointsA() as Integer {
        return self.pointsA;
    }
}