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
   function teamScoresOneExtraPointAfterTouchdown(logger as Test.Logger) as Boolean {
      var scores = new Scores();
      scores.touchDownA();

      scores.teamAGoesForPointAfterTouchDown(1);
      
      return scores.getPointsA() == 7;
   }

   (:test)
   function teamScoresTwoExtraPointAfterTouchdown(logger as Test.Logger) as Boolean {
      var scores = new Scores();
      scores.touchDownA();

      scores.teamAGoesForPointAfterTouchDown(2);
      
      return scores.getPointsA() == 8;
   }
}
