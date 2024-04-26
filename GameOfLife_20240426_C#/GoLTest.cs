using NUnit.Framework;
using System.Collections.Generic;

[TestFixture]
public class GolTest
{
    private Grid grid;
    private Rules rules;
    
    [SetUp]
    public void InitializeGrid() {
        grid = new Grid();
        rules = new Rules();
    }
    
    [Test]
    public void new_grid_has_only_empty_space()
    {
        AssertCell<EmptySpace>(grid);
    }
    
    [Test]
    public void new_grid_with_seed_has_one_alive_cell()
    {
        grid.BringAlive(new Position());

        AssertCell<AliveCell>(grid);
    }
    
    // TODO would add a test for another position now...

    [Test]
    public void living_cell_with_no_neighbours_dies()
    {
        grid.BringAlive(new Position());

        var newGrid = rules.Evolve(grid);
        
        AssertCell<EmptySpace>(newGrid);
    }
    
    [Test]
    public void living_cell_with_three_neighbours_stays_alive()
    {
        grid.BringAlive(new Position());
        grid.BringAlive(new Position(1,0));
        grid.BringAlive(new Position(1,1));
        grid.BringAlive(new Position(0,1));

        var newGrid = rules.Evolve(grid);
        
        AssertCell<AliveCell>(newGrid);
    }
    
    public void AssertCell<T>(Grid grid) 
    {
        var cell = grid.GetCell(new Position());
        Assert.That(cell, Is.InstanceOf<T>());
    }
    
}

public class Rules {
 
    public Grid Evolve(Grid grid) {
        return grid;
    }
    
}

public class Grid {
    private List<Position> cells = new List<Position>();

    public void BringAlive(Position position) {
        this.cells.Add(position);
    }
    public Grid Evolve() {
        var newGrid = new Grid();
        int countNeighbours = 0;
        if (this.cells.Contains(new Position(0,1))) {
            countNeighbours++;
        }
        if (this.cells.Contains(new Position(1,0))) {
            countNeighbours++;
        }
        if (this.cells.Contains(new Position(1,1))) {
            countNeighbours++;
        }
        if (countNeighbours == 3) {
            newGrid.BringAlive(new Position(0,0));
        }
        return newGrid;
    }
    public GridSpace GetCell(Position position) {
        if (this.cells.Contains(position)) {
            return new AliveCell();
        }
        return new EmptySpace();
    }
}

public interface GridSpace {}
public class EmptySpace : GridSpace {}
public class AliveCell : GridSpace {}



