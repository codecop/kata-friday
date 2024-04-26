using NUnit.Framework;
 
[TestFixture]
public class GolTest
{
    [Test]
    public void new_grid_has_no_cells()
    {
        var grid = new Grid();
        var cell = grid.getCell(0,0);
        Assert.That(cell, Is.InstanceOf<EmptyClass>());
    }
    
    [Test]
    public void new_grid_with_seed_has_one_cell()
    {
        var grid = new Grid();
        grid.bringAlive(0,0);
        var cell = grid.getCell(0,0);
        Assert.That(cell, Is.InstanceOf<AliveCell>());
    }
      
}

public class Grid {

    public EmptyClass getCell(int x, int y) {
        return new EmptyClass();
    }
    
}

public class EmptyClass {
}