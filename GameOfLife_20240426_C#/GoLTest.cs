using NUnit.Framework;
 
[TestFixture]
public class GolTest
{
    private Grid grid = new Grid();
    
    [Test]
    public void new_grid_has_only_empty_space()
    {
        var cell = grid.GetCell(0,0);
        Assert.That(cell, Is.InstanceOf<EmptySpace>());
    }
    
    [Test]
    public void new_grid_with_seed_has_one_alive_cell()
    {
        grid.BringAlive(0,0);

        var cell = grid.GetCell(0,0);
        Assert.That(cell, Is.InstanceOf<AliveCell>());
    }
      
}

public class Grid {
    int x = -1;
    public void BringAlive(int x, int y) {
        this.x = x;
    }
    public GridSpace GetCell(int x, int y) {
        if (this.x == x) {
            return new AliveCell();
        }
        return new EmptySpace();
    }
}

public interface GridSpace {}
public class EmptySpace : GridSpace {}
public class AliveCell : GridSpace {}
