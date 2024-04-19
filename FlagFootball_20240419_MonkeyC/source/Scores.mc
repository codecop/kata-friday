import Toybox.Application;
import Toybox.Lang;
import Toybox.WatchUi;

class Scores {

    private var pointsA = 0;
    private var isPointAfterTouchDownA = false;

    function initialize() {
    }

    function touchDownA() as Void {
        if (self.isPointAfterTouchDownA) {
           self.pointsA += 1;
        } else {
           self.pointsA += 6;
           self.isPointAfterTouchDownA = true;
        }
    }

    function getPointsA() as Integer {
        return self.pointsA;
    }
}