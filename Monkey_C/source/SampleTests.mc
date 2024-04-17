import Toybox.Lang;
using Toybox.Test;

// see https://developer.garmin.com/connect-iq/core-topics/unit-testing/#unittesting
// see https://github.com/search?q=%22Toybox.Test%22&type=code

function plus(x as Number, y as Number) as Number {
   return x + y;
}

(:debug)
class SampleTests {

   (:test)
   function aDebugTest(logger as Test.Logger) as Boolean {
      logger.debug("This is a debug message.");
      return true; // false is failing
   }

   (:test)
   function anAssertTest(logger as Test.Logger) as Boolean {
      Test.assert(true);
      Test.assertEqual(3, plus(1, 2));
      Test.assertEqualMessage(3, plus(1, 2), "Plus");
      return true;
   }

}

function assertFloatMessage(actual as Lang.Float, expected as Lang.Float, message as Lang.String) as Void {
    var floatdeviation = 0.001;
    Toybox.Test.assertMessage(actual >= expected - floatdeviation && actual <= expected + floatdeviation, message);
}
