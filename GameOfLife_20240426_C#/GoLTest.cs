using NUnit.Framework;
 
[TestFixture]
public class GolTest
{
    private Grid grid = new Grid();
    
    [Test]
    public void new_grid_has_only_empty_space()
    {
        var cell = grid.getCell(0,0);
        Assert.That(cell, Is.InstanceOf<EmptySpace>());
    }
    
    [Test]
    public void new_grid_with_seed_has_one_alive_cell()
    {
        var grid = new Grid();
        grid.bringAlive(0,0);
        var cell = grid.getCell(0,0);
        Assert.That(cell, Is.InstanceOf<AliveCell>());
    }
      
}

public class Grid {
    int x = -1;
    public void bringAlive(int x, int y) {
        this.x = x;
    }
    public GridSpace getCell(int x, int y) {
        if (this.x == x) {
            return new AliveCell();
        }
        return new EmptySpace();
    }
}

public interface GridSpace {}
public class EmptySpace : GridSpace {}
public class AliveCell : GridSpace {}
