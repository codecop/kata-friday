import Toybox.Lang;
using Toybox.Test;

// see https://developer.garmin.com/connect-iq/core-topics/unit-testing/#unittesting
// see https://github.com/search?q=%22Toybox.Test%22&type=code

(:debug)
class TeamTests {

   (:test)
   function scoresTouchDown(logger as Test.Logger) as Boolean {
      var team = new Team();
      
      team.touchDown();
      
      return team.getPoints() == 6;
   }

   (:test)
   function scoresOnePoint(logger as Test.Logger) as Boolean {
      var team = new Team();

      team.scoresOnePoint();
      
      return team.getPoints() == 1;
   }

   (:test)
   function scoresTwoPoints(logger as Test.Logger) as Boolean {
      var team = new Team();

      team.scoresTwoPoints();
      
      return team.getPoints() == 2;
   }

   (:test)
   function refereeChangesScoreOfTeam(logger as Test.Logger) as Boolean {
      var team = new Team();

      team.setScore(2,1,7);

      return team.getPoints() == 217;
   }
}

/* 

   TODO:

   Testlist
   - 2 Punkte ohne TD auch möglich (Safety, Return PAT)
   - ist ein Punkt möglich ohne TD?
*/