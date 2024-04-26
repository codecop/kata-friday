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
      
}

public class Grid() {
    
    public EmptyClass getCell(int x, int y) {
        return new EmptyClass();
    }
    
}

public class EmptyClass() {
}