import Toybox.Lang;
using Toybox.Test;

// see https://developer.garmin.com/connect-iq/core-topics/unit-testing/#unittesting
// see https://github.com/search?q=%22Toybox.Test%22&type=code

(:debug)
class ScoresTests {

   (:test)
   function teamScoresTouchDown(logger as Test.Logger) as Boolean {
      var scores = new Scores();
      
      scores.touchDownA();
      
      return scores.getPointsA() == 6;
   }

   (:test)
   function teamScoresOnePoint(logger as Test.Logger) as Boolean {
      var scores = new Scores();

      scores.teamAScoresOnePoint();
      
      return scores.getPointsA() == 1;
   }

   (:test)
   function teamScoresTwoPoints(logger as Test.Logger) as Boolean {
      var scores = new Scores();

      scores.teamAScoresTwoPoints();
      
      return scores.getPointsA() == 2;
   }
}

/* 
   Testlist
   - 2 Punkte ohne TD auch möglich (Safety, Return PAT)
   - ist ein Punkt möglich ohne TD?
*/