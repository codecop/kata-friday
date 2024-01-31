<?php
namespace Tests;

use PHPUnit\Framework\TestCase;

class PHP6Test extends TestCase {

    /** @test */
    function shouldAssertPlain() {
        $this->assertEquals(2, 1 + 1);
    }

}
