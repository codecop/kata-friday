using NUnit.Framework;
 
[TestFixture]
public class GolTest
{
    private Grid grid;
    
    [SetUp]
    public void InitializeGrid() {
        grid = new Grid();
    }
    
    [Test]
    public void new_grid_has_only_empty_space()
    {
        var cell = grid.GetCell(new Position());
        Assert.That(cell, Is.InstanceOf<EmptySpace>());
    }
    
    [Test]
    public void new_grid_with_seed_has_one_alive_cell()
    {
        grid.BringAlive(new Position());

        var cell = grid.GetCell(new Position());
        Assert.That(cell, Is.InstanceOf<AliveCell>());
    }

    [Test]
    public void living_cell_with_no_neighbours_dies()
    {
        grid.BringAlive(new Position());

        var newGrid = grid.Evolve();
        
        AssertCell<EmptySpace>(newGrid);
        var cell = newGrid.GetCell(new Position());
        Assert.That(cell, Is.InstanceOf<EmptySpace>());
    }
    
    public void AssertCell<T>(Grid grid) 
    {
        var cell = grid.GetCell(new Position());
        Assert.That(cell, Is.InstanceOf<T>());
    }
    
}

public class Grid {
    private Position cell;

    public void BringAlive(Position position) {
        this.cell = position;
    }
    public Grid Evolve() {
        return new Grid();
    }
    public GridSpace GetCell(Position position) {
        if (this.cell != null) {
            return new AliveCell();
        }
        return new EmptySpace();
    }
}

public interface GridSpace {}
public class EmptySpace : GridSpace {}
public class AliveCell : GridSpace {}



